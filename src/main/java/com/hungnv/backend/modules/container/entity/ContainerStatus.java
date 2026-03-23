package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "container_statuses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    private String statusName;
}

