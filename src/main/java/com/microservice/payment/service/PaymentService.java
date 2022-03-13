package com.microservice.payment.service;

import com.microservice.payment.controllers.model.PaymentRequest;
import com.microservice.payment.controllers.model.PaymentResponse;
import com.microservice.payment.repository.PaymentRequestRepository;
import com.microservice.payment.repository.entity.PaymentRequestEntity;
import com.microservice.payment.repository.entity.PaymentResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

  private final PaymentRequestRepository repository;

  public List<PaymentResponse> generate(PaymentRequest request) {
    PaymentRequestEntity requestEntity = PaymentRequestEntity.builder().amount(request.getAmount()).rate(request.getRate()).terms(request.getTerms()).build();
    // Documentation: https://www.calculatorsoup.com/calculators/financial/simple-interest-plus-principal-calculator.php
    double totalAmount = request.getAmount() * (1 + (request.getRate() / 100) * request.getTerms() / 52);
    LocalDate startDate = LocalDate.now().plusWeeks(1);
    BigDecimal monthlyAmount = new BigDecimal(totalAmount / request.getTerms()).setScale(2, RoundingMode.HALF_UP);
    List<PaymentResponse> response = IntStream.range(0, request.getTerms())
        .mapToObj(i -> PaymentResponse
            .builder()
            .amount(monthlyAmount.doubleValue())
            .paymentNumber(i + 1)
            .paymentDate(startDate.plusWeeks(i)).build()).collect(Collectors.toList());
    requestEntity.setPaymentResponses(response.stream().map(item -> PaymentResponseEntity
        .builder().paymentNumber(item.getPaymentNumber())
        .amount(item.getAmount())
        .paymentDate(item.getPaymentDate())
        .paymentRequest(requestEntity).build()).collect(Collectors.toList()));
    repository.save(requestEntity);
    return response;
  }
}
