package com.hungnv.backend.modules.billing.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bill_of_lading")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillOfLading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Integer billId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "bill_number")
    private String billNumber;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "note")
    private String note;
}

