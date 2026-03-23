package com.hungnv.backend.modules.booking.repository;

import com.hungnv.backend.modules.booking.entity.OrderContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderContainerRepository extends JpaRepository<OrderContainer, OrderContainer.OrderContainerId> {
    List<OrderContainer> findByIdOrderId(Integer orderId);
    List<OrderContainer> findByIdContainerId(String containerId);
}

