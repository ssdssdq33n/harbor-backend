package com.hungnv.backend.modules.booking.repository;

import com.hungnv.backend.modules.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByCustomerId(Integer customerId);
    List<Booking> findByStatusId(Integer statusId);
    List<Booking> findByCreatedAtBetween(Instant from, Instant to);
}

