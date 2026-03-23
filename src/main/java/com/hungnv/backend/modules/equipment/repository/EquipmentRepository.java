package com.hungnv.backend.modules.equipment.repository;

import com.hungnv.backend.modules.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    List<Equipment> findByStatus(Short status);
    List<Equipment> findByCurrentZoneId(Integer currentZoneId);
}

