package com.hungnv.backend.modules.yard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "yards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Yard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "yard_id")
    private Integer yardId;

    @Column(name = "yard_name")
    private String yardName;

    @Column(name = "address")
    private String address;
}

