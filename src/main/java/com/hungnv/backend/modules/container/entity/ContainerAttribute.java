package com.hungnv.backend.modules.container.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "container_attributes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerAttribute {
    @EmbeddedId
    private ContainerAttributeId id;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContainerAttributeId implements Serializable {
        @Column(name = "container_id")
        private String containerId;
        @Column(name = "attribute_id")
        private Integer attributeId;
    }
}

