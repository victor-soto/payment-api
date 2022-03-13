package com.microservice.payment.service;

import com.microservice.payment.controllers.model.PaymentRequest;
import com.microservice.payment.controllers.model.PaymentResponse;

import java.util.List;

public interface IPaymentService {
  List<PaymentResponse> generate(PaymentRequest request);
}
