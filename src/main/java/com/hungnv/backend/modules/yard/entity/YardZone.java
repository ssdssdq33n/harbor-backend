package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "yard_zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YardZone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "yard_id")
    private Integer yardId;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "capacity_slots")
    private Integer capacitySlots;
}

