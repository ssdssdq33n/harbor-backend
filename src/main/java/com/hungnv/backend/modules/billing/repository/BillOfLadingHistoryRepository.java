package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.BillOfLadingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillOfLadingHistoryRepository extends JpaRepository<BillOfLadingHistory, Integer> {
    List<BillOfLadingHistory> findByBillIdOrderByCreatedAtDesc(Integer billId);
}

