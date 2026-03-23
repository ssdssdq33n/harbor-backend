package com.hungnv.backend.modules.vessel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "voyages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voyage_id")
    private Integer voyageId;

    @Column(name = "vessel_id")
    private Integer vesselId;

    @Column(name = "voyage_no")
    private String voyageNo;

    @Column(name = "port_of_loading")
    private String portOfLoading;

    @Column(name = "port_of_discharge")
    private String portOfDischarge;

    @Column(name = "estimated_time_arrival")
    private Instant estimatedTimeArrival;

    @Column(name = "actual_time_arrival")
    private Instant actualTimeArrival;

    @Column(name = "created_at")
    private Instant createdAt;
}

