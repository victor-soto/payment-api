package com.microservice.payment.controllers;

import com.microservice.payment.controllers.model.PaymentRequest;
import com.microservice.payment.controllers.model.PaymentResponse;
import com.microservice.payment.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

  private final IPaymentService service;

  @PostMapping("/generate")
  public List<PaymentResponse> generate(@RequestBody PaymentRequest request) {
    return service.generate(request);
  }
}
