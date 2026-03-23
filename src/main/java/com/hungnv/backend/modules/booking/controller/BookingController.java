package com.hungnv.backend.modules.booking.controller;

import com.hungnv.backend.modules.booking.entity.Booking;
import com.hungnv.backend.modules.booking.entity.BookingCancellation;
import com.hungnv.backend.modules.booking.entity.OrderContainer;
import com.hungnv.backend.modules.booking.repository.BookingCancellationRepository;
import com.hungnv.backend.modules.booking.repository.BookingRepository;
import com.hungnv.backend.modules.booking.repository.OrderContainerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingRepository bookingRepository;
    private final BookingCancellationRepository cancellationRepository;
    private final OrderContainerRepository orderContainerRepository;

    public BookingController(BookingRepository bookingRepository, BookingCancellationRepository cancellationRepository, OrderContainerRepository orderContainerRepository) {
        this.bookingRepository = bookingRepository;
        this.cancellationRepository = cancellationRepository;
        this.orderContainerRepository = orderContainerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> list(
            @RequestParam(name = "customerId", required = false) Integer customerId,
            @RequestParam(name = "statusId", required = false) Integer statusId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to
    ) {
        if (customerId != null) return ResponseEntity.ok(bookingRepository.findByCustomerId(customerId));
        if (statusId != null) return ResponseEntity.ok(bookingRepository.findByStatusId(statusId));
        if (from != null && to != null) return ResponseEntity.ok(bookingRepository.findByCreatedAtBetween(from, to));
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> get(@PathVariable Integer id) {
        return ResponseEntity.of(bookingRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Booking> create(@Valid @RequestBody Booking booking) {
        booking.setOrderId(null);
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @PostMapping("/{id}/cancel")
    @Transactional
    public ResponseEntity<BookingCancellation> cancel(@PathVariable Integer id, @Valid @RequestBody CancelRequest request) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        Optional<BookingCancellation> existing = cancellationRepository.findByOrderId(id);
        if (existing.isPresent()) return ResponseEntity.ok(existing.get());
        BookingCancellation cancellation = BookingCancellation.builder()
                .orderId(booking.getOrderId())
                .reason(request.getReason())
                .build();
        return ResponseEntity.ok(cancellationRepository.save(cancellation));
    }

    @PostMapping("/{id}/status")
    @Transactional
    public ResponseEntity<Booking> updateStatus(@PathVariable Integer id, @Valid @RequestBody UpdateStatusRequest request) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatusId(request.getStatusId());
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @GetMapping("/{id}/containers")
    public ResponseEntity<List<OrderContainer>> containers(@PathVariable Integer id) {
        return ResponseEntity.ok(orderContainerRepository.findByIdOrderId(id));
    }

    @PostMapping("/{id}/containers")
    public ResponseEntity<OrderContainer> addContainer(@PathVariable Integer id, @Valid @RequestBody AddContainerRequest request) {
        OrderContainer oc = OrderContainer.builder()
                .id(new OrderContainer.OrderContainerId(id, request.getContainerId()))
                .build();
        return ResponseEntity.ok(orderContainerRepository.save(oc));
    }

    public static class CancelRequest {
        @NotNull
        @NotBlank
        private String reason;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    public static class UpdateStatusRequest {
        @NotNull
        private Integer statusId;

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }
    }

    public static class AddContainerRequest {
        @NotNull
        @NotBlank
        private String containerId;

        public String getContainerId() {
            return containerId;
        }

        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }
    }
}
