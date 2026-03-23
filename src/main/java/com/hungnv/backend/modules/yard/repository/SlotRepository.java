package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Integer> {
    List<Slot> findByBlockId(Integer blockId);
}

