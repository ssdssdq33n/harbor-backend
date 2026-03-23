package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "container_position_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerPositionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "tier")
    private Integer tier;

    @Column(name = "moved_at")
    private Instant movedAt;

    @Column(name = "moved_by")
    private Integer movedBy;
}

