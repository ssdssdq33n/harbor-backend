package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "yard_storage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YardStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Integer storageId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "yard_id")
    private Integer yardId;

    @Column(name = "storage_start_date")
    private LocalDate storageStartDate;

    @Column(name = "storage_end_date")
    private LocalDate storageEndDate;

    @Column(name = "note")
    private String note;
}

