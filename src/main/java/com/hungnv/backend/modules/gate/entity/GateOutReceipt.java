package com.hungnv.backend.modules.gate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "gate_out_receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GateOutReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gate_out_id")
    private Integer gateOutId;

    @Column(name = "container_id")
    private String containerId;

    @Column(name = "gate_out_time")
    private Instant gateOutTime;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "note")
    private String note;
}

