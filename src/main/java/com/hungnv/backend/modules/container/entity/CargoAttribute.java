package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargo_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargoAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Integer attributeId;

    @Column(name = "attribute_name")
    private String attributeName;
}

