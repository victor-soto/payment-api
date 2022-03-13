package com.microservice.payment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.payment.controllers.model.PaymentRequest;
import com.microservice.payment.controllers.model.PaymentResponse;
import com.microservice.payment.service.PaymentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

  @MockBean
  PaymentService service;

  @Autowired
  MockMvc mockMvc;

  @Test
  void testGenerate() throws Exception {
    PaymentRequest request = PaymentRequest.builder().amount(1000).rate(7.5).terms(3).build();

    List<PaymentResponse> response = Arrays.asList(
        PaymentResponse.builder().paymentNumber(1).amount(10008.75).paymentDate(LocalDate.of(2022, 4, 1)).build(),
        PaymentResponse.builder().paymentNumber(2).amount(10008.75).paymentDate(LocalDate.of(2022, 5, 1)).build(),
        PaymentResponse.builder().paymentNumber(3).amount(10008.75).paymentDate(LocalDate.of(2022, 6, 1)).build());
    when(service.generate(request)).thenReturn(response);

    mockMvc.perform(post("/payment/generate").content(new ObjectMapper().writeValueAsString(request)).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(3)))
        .andExpect(jsonPath("$[0].paymentNumber", Matchers.is(1)))
        .andExpect(jsonPath("$[0].amount", Matchers.is(10008.75)))
        .andExpect(jsonPath("$[0].paymentDate", Matchers.is("2022-04-01")));
  }

}