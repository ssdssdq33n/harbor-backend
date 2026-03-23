package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "container_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "container_type_id")
    private Integer containerTypeId;

    @Column(name = "container_type_name")
    private String containerTypeName;

    @Column(name = "size")
    private Integer size;
}

