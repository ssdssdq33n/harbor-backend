package com.hungnv.backend.modules.container.repository;

import com.hungnv.backend.modules.container.entity.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoTypeRepository extends JpaRepository<CargoType, Integer> {
}

