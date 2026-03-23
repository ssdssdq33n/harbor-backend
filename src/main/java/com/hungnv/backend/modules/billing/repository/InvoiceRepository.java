package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByOrderId(Integer orderId);
}

