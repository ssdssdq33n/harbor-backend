package com.hungnv.backend.modules.alert.repository;

import com.hungnv.backend.modules.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
    List<Alert> findByStatus(Short status);
    List<Alert> findByZoneId(Integer zoneId);
}

