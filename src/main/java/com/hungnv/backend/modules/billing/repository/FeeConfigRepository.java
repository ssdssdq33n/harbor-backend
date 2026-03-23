package com.hungnv.backend.modules.billing.repository;

import com.hungnv.backend.modules.billing.entity.FeeConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeConfigRepository extends JpaRepository<FeeConfig, Integer> {
    Optional<FeeConfig> findByFeeCode(String feeCode);
}

