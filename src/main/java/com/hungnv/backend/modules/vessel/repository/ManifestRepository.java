package com.hungnv.backend.modules.vessel.repository;

import com.hungnv.backend.modules.vessel.entity.Manifest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManifestRepository extends JpaRepository<Manifest, Integer> {
    List<Manifest> findByVoyageId(Integer voyageId);
}

