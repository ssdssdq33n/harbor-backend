package com.hungnv.backend.modules.vessel.repository;

import com.hungnv.backend.modules.vessel.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VesselRepository extends JpaRepository<Vessel, Integer> {
}

