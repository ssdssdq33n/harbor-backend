package com.hungnv.backend.modules.vessel.repository;

import com.hungnv.backend.modules.vessel.entity.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
    List<Voyage> findByVesselId(Integer vesselId);
}

