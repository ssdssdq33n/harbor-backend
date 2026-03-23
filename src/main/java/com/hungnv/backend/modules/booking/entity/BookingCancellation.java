package com.hungnv.backend.modules.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "order_cancellation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCancellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_id")
    private Integer cancellationId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "created_at")
    private Instant createdAt;
}

