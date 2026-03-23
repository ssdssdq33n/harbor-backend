package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findByInvoiceId(Integer invoiceId);
}

