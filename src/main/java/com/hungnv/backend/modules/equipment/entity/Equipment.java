package com.hungnv.backend.modules.equipment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Integer equipmentId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "status")
    private Short status;

    @Column(name = "current_zone_id")
    private Integer currentZoneId;
}

