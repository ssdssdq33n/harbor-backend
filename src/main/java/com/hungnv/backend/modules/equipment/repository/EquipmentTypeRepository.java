package com.hungnv.backend.modules.equipment.repository;

import com.hungnv.backend.modules.equipment.entity.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Integer> {
    Optional<EquipmentType> findByTypeCode(String typeCode);
}

