package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.BillOfLading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillOfLadingRepository extends JpaRepository<BillOfLading, Integer> {
    Optional<BillOfLading> findByBillNumber(String billNumber);
    List<BillOfLading> findByOrderId(Integer orderId);
}

