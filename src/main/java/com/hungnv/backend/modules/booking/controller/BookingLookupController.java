package com.hungnv.backend.modules.booking.controller;

import com.hungnv.backend.modules.booking.entity.OrderStatus;
import com.hungnv.backend.modules.booking.entity.OrderType;
import com.hungnv.backend.modules.booking.repository.OrderStatusRepository;
import com.hungnv.backend.modules.booking.repository.OrderTypeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-lookups")
public class BookingLookupController {
    private final OrderStatusRepository statusRepository;
    private final OrderTypeRepository typeRepository;

    public BookingLookupController(OrderStatusRepository statusRepository, OrderTypeRepository typeRepository) {
        this.statusRepository = statusRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<OrderStatus>> statuses() {
        return ResponseEntity.ok(statusRepository.findAll());
    }

    @PostMapping("/statuses")
    public ResponseEntity<OrderStatus> createStatus(@Valid @RequestBody OrderStatus status) {
        status.setStatusId(null);
        return ResponseEntity.ok(statusRepository.save(status));
    }

    @GetMapping("/types")
    public ResponseEntity<List<OrderType>> types() {
        return ResponseEntity.ok(typeRepository.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity<OrderType> createType(@Valid @RequestBody OrderType type) {
        type.setOrderTypeId(null);
        return ResponseEntity.ok(typeRepository.save(type));
    }
}

