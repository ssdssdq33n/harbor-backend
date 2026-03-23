package com.hungnv.backend.modules.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "order_container")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderContainer {
    @EmbeddedId
    private OrderContainerId id;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderContainerId implements Serializable {
        @Column(name = "order_id")
        private Integer orderId;
        @Column(name = "container_id")
        private String containerId;
    }
}

