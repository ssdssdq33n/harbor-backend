package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "container_status_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerStatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;
}

