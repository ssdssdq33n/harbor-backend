package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Integer> {
    List<Block> findByZoneId(Integer zoneId);
}

