package com.hungnv.backend.modules.yard.repository;

import com.hungnv.backend.modules.yard.entity.Yard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YardRepository extends JpaRepository<Yard, Integer> {
}

