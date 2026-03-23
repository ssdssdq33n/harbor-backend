package com.hungnv.backend.modules.booking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_type_id")
    private Integer orderTypeId;

    @Column(name = "type_name")
    private String typeName;
}

