package com.hungnv.backend.modules.vessel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vessels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vessel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vessel_id")
    private Integer vesselId;

    @Column(name = "vessel_name")
    private String vesselName;

    @Column(name = "shipping_line")
    private String shippingLine;
}

