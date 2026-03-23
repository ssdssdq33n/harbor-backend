package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.YardStorage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface YardStorageRepository extends JpaRepository<YardStorage, Integer> {
    List<YardStorage> findByYardId(Integer yardId);
    Optional<YardStorage> findTopByContainerIdOrderByStorageStartDateDesc(String containerId);
}

