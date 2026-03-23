package com.hungnv.backend.modules.billing.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bill_of_lading_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOfLadingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    private String statusName;
}

