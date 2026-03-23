package com.hungnv.backend.modules.gate.repository;

import com.hungnv.backend.modules.gate.entity.GateOutReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GateOutReceiptRepository extends JpaRepository<GateOutReceipt, Integer> {
    List<GateOutReceipt> findByContainerId(String containerId);
}

