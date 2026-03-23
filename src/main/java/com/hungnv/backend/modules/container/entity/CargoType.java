package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cargo_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_type_id")
    private Integer cargoTypeId;

    @Column(name = "cargo_type_name")
    private String cargoTypeName;
}

