package com.hungnv.backend.modules.booking.repository;

import com.hungnv.backend.modules.booking.entity.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {
}

