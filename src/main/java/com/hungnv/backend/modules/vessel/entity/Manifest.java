package com.hungnv.backend.modules.vessel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "manifest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manifest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manifest_id")
    private Integer manifestId;

    @Column(name = "voyage_id")
    private Integer voyageId;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "note")
    private String note;
}

