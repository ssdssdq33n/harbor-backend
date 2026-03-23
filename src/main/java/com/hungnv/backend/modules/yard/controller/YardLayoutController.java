package com.hungnv.backend.modules.yard.controller;

import com.hungnv.backend.modules.yard.entity.Block;
import com.hungnv.backend.modules.yard.entity.ContainerPosition;
import com.hungnv.backend.modules.yard.entity.ContainerPositionHistory;
import com.hungnv.backend.modules.yard.entity.Slot;
import com.hungnv.backend.modules.yard.entity.YardZone;
import com.hungnv.backend.modules.yard.repository.BlockRepository;
import com.hungnv.backend.modules.yard.repository.ContainerPositionHistoryRepository;
import com.hungnv.backend.modules.yard.repository.ContainerPositionRepository;
import com.hungnv.backend.modules.yard.repository.SlotRepository;
import com.hungnv.backend.modules.yard.repository.YardZoneRepository;
import com.hungnv.backend.modules.yard.service.RelocationService;
import com.hungnv.backend.modules.yard.service.SlotAllocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class YardLayoutController {
    private final YardZoneRepository zoneRepository;
    private final BlockRepository blockRepository;
    private final SlotRepository slotRepository;
    private final ContainerPositionRepository positionRepository;
    private final ContainerPositionHistoryRepository historyRepository;
    private final RelocationService relocationService;
    private final SlotAllocationService slotAllocationService;

    public YardLayoutController(
            YardZoneRepository zoneRepository,
            BlockRepository blockRepository,
            SlotRepository slotRepository,
            ContainerPositionRepository positionRepository,
            ContainerPositionHistoryRepository historyRepository,
            RelocationService relocationService,
            SlotAllocationService slotAllocationService
    ) {
        this.zoneRepository = zoneRepository;
        this.blockRepository = blockRepository;
        this.slotRepository = slotRepository;
        this.positionRepository = positionRepository;
        this.historyRepository = historyRepository;
        this.relocationService = relocationService;
        this.slotAllocationService = slotAllocationService;
    }

    @GetMapping("/yard-zones")
    public ResponseEntity<List<YardZone>> zones(@RequestParam(name = "yardId", required = false) Integer yardId) {
        if (yardId != null) return ResponseEntity.ok(zoneRepository.findByYardId(yardId));
        return ResponseEntity.ok(zoneRepository.findAll());
    }

    @GetMapping("/blocks")
    public ResponseEntity<List<Block>> blocks(@RequestParam(name = "zoneId", required = false) Integer zoneId) {
        if (zoneId != null) return ResponseEntity.ok(blockRepository.findByZoneId(zoneId));
        return ResponseEntity.ok(blockRepository.findAll());
    }

    @GetMapping("/slots")
    public ResponseEntity<List<Slot>> slots(@RequestParam(name = "blockId", required = false) Integer blockId) {
        if (blockId != null) return ResponseEntity.ok(slotRepository.findByBlockId(blockId));
        return ResponseEntity.ok(slotRepository.findAll());
    }

    @GetMapping("/positions")
    public ResponseEntity<?> positions(
            @RequestParam(name = "containerId", required = false) String containerId,
            @RequestParam(name = "slotId", required = false) Integer slotId
    ) {
        if (containerId != null) return ResponseEntity.of(positionRepository.findByContainerId(containerId));
        if (slotId != null) return ResponseEntity.ok(positionRepository.findBySlotId(slotId));
        return ResponseEntity.ok(positionRepository.findAll());
    }

    @GetMapping("/positions/{containerId}/history")
    public ResponseEntity<List<ContainerPositionHistory>> history(@PathVariable String containerId) {
        return ResponseEntity.ok(historyRepository.findByContainerIdOrderByMovedAtDesc(containerId));
    }

    @PostMapping("/positions/move")
    public ResponseEntity<ContainerPosition> move(@Valid @RequestBody MoveRequest request) {
        return ResponseEntity.ok(relocationService.move(
                request.getContainerId(),
                request.getSlotId(),
                request.getTier(),
                request.getEquipmentId(),
                request.getMovedBy()
        ));
    }

    @GetMapping("/positions/suggest")
    public ResponseEntity<?> suggest(@RequestParam("containerId") String containerId, @RequestParam(name = "blockId", required = false) Integer blockId) {
        return ResponseEntity.ok(slotAllocationService.suggestSlot(containerId, blockId));
    }

    public static class MoveRequest {
        @NotBlank
        private String containerId;
        @NotNull
        private Integer slotId;
        @NotNull
        private Integer tier;
        private Integer equipmentId;
        private Integer movedBy;

        public String getContainerId() {
            return containerId;
        }

        public void setContainerId(String containerId) {
            this.containerId = containerId;
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

        public Integer getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(Integer equipmentId) {
            this.equipmentId = equipmentId;
        }

        public Integer getMovedBy() {
            return movedBy;
        }

        public void setMovedBy(Integer movedBy) {
            this.movedBy = movedBy;
        }
    }
}
