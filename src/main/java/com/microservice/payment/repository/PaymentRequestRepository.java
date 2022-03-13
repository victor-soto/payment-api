package com.microservice.payment.repository;

import com.microservice.payment.repository.entity.PaymentRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequestEntity, Long> {
}
