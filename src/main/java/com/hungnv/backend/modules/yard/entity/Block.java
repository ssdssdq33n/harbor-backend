package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Integer blockId;

    @Column(name = "zone_id")
    private Integer zoneId;

    @Column(name = "block_type_id")
    private Integer blockTypeId;

    @Column(name = "block_name")
    private String blockName;
}

