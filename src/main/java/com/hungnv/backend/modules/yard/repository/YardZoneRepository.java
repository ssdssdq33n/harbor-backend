package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.YardZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YardZoneRepository extends JpaRepository<YardZone, Integer> {
    List<YardZone> findByYardId(Integer yardId);
}

