package com.microservice.payment.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_response")
public class PaymentResponseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private int paymentNumber;
  private double amount;
  private LocalDate paymentDate;

  @JoinColumn(name = "payment_request_id", updatable = false, nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private PaymentRequestEntity paymentRequest;
}
