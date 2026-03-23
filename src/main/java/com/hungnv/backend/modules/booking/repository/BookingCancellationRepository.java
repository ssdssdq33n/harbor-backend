package com.hungnv.backend.modules.booking.repository;

import com.hungnv.backend.modules.booking.entity.BookingCancellation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingCancellationRepository extends JpaRepository<BookingCancellation, Integer> {
    Optional<BookingCancellation> findByOrderId(Integer orderId);
}

