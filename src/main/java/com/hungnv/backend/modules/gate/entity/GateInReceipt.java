package com.hungnv.backend.modules.gate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "gate_in_receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GateInReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gate_in_id")
    private Integer gateInId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "voyage_id")
    private Integer voyageId;

    @Column(name = "gate_in_time")
    private Instant gateInTime;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "note")
    private String note;
}

