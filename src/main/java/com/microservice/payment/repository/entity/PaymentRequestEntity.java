package com.microservice.payment.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "payment_request")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private double amount;
  private int terms;
  private double rate;
  @OneToMany(mappedBy = "paymentRequest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<PaymentResponseEntity> paymentResponses = new ArrayList<>();

}
