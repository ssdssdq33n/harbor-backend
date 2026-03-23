package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Integer slotId;

    @Column(name = "block_id")
    private Integer blockId;

    @Column(name = "row_no")
    private Integer rowNo;

    @Column(name = "bay_no")
    private Integer bayNo;

    @Column(name = "max_tier")
    private Integer maxTier;

    @Column(name = "supported_size")
    private Integer supportedSize;
}

