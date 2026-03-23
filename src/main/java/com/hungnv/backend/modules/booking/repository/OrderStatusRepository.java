package com.hungnv.backend.modules.booking.repository;

import com.hungnv.backend.modules.booking.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}

