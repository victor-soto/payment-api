package com.microservice.payment.controllers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

  @Max(999999)
  @Min(1)
  private double amount;

  @Max(52)
  @Min(4)
  private int terms;

  @Max(100)
  @Min(1)
  private double rate;

}
