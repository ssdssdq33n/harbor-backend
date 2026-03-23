package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "container_positions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Integer positionId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "tier")
    private Integer tier;

    @Column(name = "updated_at")
    private Instant updatedAt;
}

