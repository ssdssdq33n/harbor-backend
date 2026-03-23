package com.hungnv.backend.modules.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "fee_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Integer feeId;

    @Column(name = "fee_code")
    private String feeCode;

    @Column(name = "description")
    private String description;

    @Column(name = "container_type_id")
    private Integer containerTypeId;

    @Column(name = "base_lift_on")
    private BigDecimal baseLiftOn;

    @Column(name = "base_lift_off")
    private BigDecimal baseLiftOff;

    @Column(name = "storage_fee_per_day")
    private BigDecimal storageFeePerDay;

    @Column(name = "reefer_fee_per_day")
    private BigDecimal reeferFeePerDay;

    @Column(name = "created_at")
    private Instant createdAt;
}

