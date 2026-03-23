package com.hungnv.backend.modules.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "bill_of_lading_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOfLadingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;
}

