package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "export_priority")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_id")
    private Integer priorityId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "priority_level")
    private Integer priorityLevel;

    @Column(name = "note")
    private String note;
}

