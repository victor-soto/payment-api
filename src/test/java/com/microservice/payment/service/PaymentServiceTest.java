package com.microservice.payment.service;

import com.microservice.payment.controllers.model.PaymentRequest;
import com.microservice.payment.controllers.model.PaymentResponse;
import com.microservice.payment.repository.PaymentRequestRepository;
import com.microservice.payment.repository.entity.PaymentRequestEntity;
import com.microservice.payment.repository.entity.PaymentResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

  @InjectMocks
  private PaymentService service;
  @Mock
  private PaymentRequestRepository repository;


  @BeforeEach
  void setUp() {
    service = new PaymentService(repository);
  }

  @Test
  void test_generate() {
    PaymentRequest request = PaymentRequest.builder().amount(1200).rate(3.5).terms(4).build();
    List<PaymentResponseEntity> paymentResponseEntities = Arrays.asList(
        PaymentResponseEntity
            .builder()
            .id(1)
            .paymentDate(LocalDate.now().plusWeeks(1))
            .paymentNumber(1)
            .amount(300.81)
            .build(),
        PaymentResponseEntity
            .builder()
            .id(2)
            .paymentDate(LocalDate.now().plusWeeks(2))
            .paymentNumber(2)
            .amount(300.81)
            .build(),
        PaymentResponseEntity
            .builder()
            .id(3)
            .paymentDate(LocalDate.now().plusWeeks(3))
            .paymentNumber(3)
            .amount(300.81)
            .build(),
        PaymentResponseEntity
            .builder()
            .id(4)
            .paymentDate(LocalDate.now().plusWeeks(4))
            .paymentNumber(4)
            .amount(300.81)
            .build());
    when(repository.save(any(PaymentRequestEntity.class))).thenReturn(PaymentRequestEntity
        .builder()
        .id(1)
        .amount(1200)
        .rate(3.5)
        .paymentResponses(paymentResponseEntities)
        .build());
    List<PaymentResponse> response = service.generate(request);
    IntStream.range(0, response.size()).forEach(index -> {
      assertThat(response.get(index).getPaymentNumber()).isEqualTo(index + 1);
      assertThat(response.get(index).getAmount()).isEqualTo(300.81);
      assertThat(response.get(index).getPaymentDate()).isEqualTo(LocalDate.now().plusWeeks(index + 1));
    });
    verify(repository, times(1)).save(any(PaymentRequestEntity.class));
  }
}