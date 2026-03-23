package com.hungnv.backend.modules.gate.controller;

import com.hungnv.backend.modules.gate.entity.GateInReceipt;
import com.hungnv.backend.modules.gate.entity.GateOutReceipt;
import com.hungnv.backend.modules.gate.service.GateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gate")
public class GateController {
    private final GateService gateService;

    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @PostMapping("/in")
    public ResponseEntity<GateInReceipt> gateIn(@Valid @RequestBody GateInRequest request) {
        return ResponseEntity.ok(gateService.gateIn(
                request.getContainerId(),
                request.getVoyageId(),
                request.getCreatedBy(),
                request.getNote(),
                request.getSlotId(),
                request.getTier(),
                request.getYardId()
        ));
    }

    @PostMapping("/out")
    public ResponseEntity<GateOutReceipt> gateOut(@Valid @RequestBody GateOutRequest request) {
        return ResponseEntity.ok(gateService.gateOut(request.getContainerId(), request.getCreatedBy(), request.getNote()));
    }

    public static class GateInRequest {
        @NotBlank
        private String containerId;
        private Integer voyageId;
        private Integer yardId;
        @NotNull
        private Integer slotId;
        @NotNull
        private Integer tier;
        private Integer createdBy;
        private String note;

        public String getContainerId() {
            return containerId;
        }

        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }

        public Integer getVoyageId() {
            return voyageId;
        }

        public void setVoyageId(Integer voyageId) {
            this.voyageId = voyageId;
        }

        public Integer getYardId() {
            return yardId;
        }

        public void setYardId(Integer yardId) {
            this.yardId = yardId;
        }

        public Integer getSlotId() {
            return slotId;
        }

        public void setSlotId(Integer slotId) {
            this.slotId = slotId;
        }

        public Integer getTier() {
            return tier;
        }

        public void setTier(Integer tier) {
            this.tier = tier;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }

    public static class GateOutRequest {
        @NotBlank
        private String containerId;
        private Integer createdBy;
        private String note;

        public String getContainerId() {
            return containerId;
        }

        public void setContainerId(String containerId) {
            this.containerId = containerId;
        }

        public Integer getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Integer createdBy) {
            this.createdBy = createdBy;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}

