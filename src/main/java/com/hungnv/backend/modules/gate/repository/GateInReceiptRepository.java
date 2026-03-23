package com.hungnv.backend.modules.gate.repository;

import com.hungnv.backend.modules.gate.entity.GateInReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateInReceiptRepository extends JpaRepository<GateInReceipt, Integer> {
    List<GateInReceipt> findByContainerId(String containerId);
}

