package com.hungnv.backend.modules.alert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "alert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alert_id")
    private Integer alertId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "level_id")
    private Integer levelId;

    @Column(name = "alert_type_id")
    private Integer alertTypeId;

    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "status")
    private Short status;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @Column(name = "created_by")
    private Integer createdBy;
}

