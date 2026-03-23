package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "container")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Container {
    @Id
    @Column(name = "container_id")
    private String containerId;

    @Column(name = "manifest_id")
    private Integer manifestId;

    @Column(name = "container_type_id")
    private Integer containerTypeId;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "cargo_type_id")
    private Integer cargoTypeId;

    @Column(name = "gross_weight")
    private BigDecimal grossWeight;

    @Column(name = "seal_number")
    private String sealNumber;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    private Instant createdAt;
}

