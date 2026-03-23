package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.BillOfLadingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillOfLadingStatusRepository extends JpaRepository<BillOfLadingStatus, Integer> {
}

