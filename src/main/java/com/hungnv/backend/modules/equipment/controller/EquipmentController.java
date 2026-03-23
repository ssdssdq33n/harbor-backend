package com.hungnv.backend.modules.equipment.controller;

import com.hungnv.backend.modules.equipment.entity.Equipment;
import com.hungnv.backend.modules.equipment.entity.EquipmentType;
import com.hungnv.backend.modules.equipment.repository.EquipmentRepository;
import com.hungnv.backend.modules.equipment.repository.EquipmentTypeRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentController(EquipmentRepository equipmentRepository, EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Equipment>> list(
            @RequestParam(name = "status", required = false) Short status,
            @RequestParam(name = "zoneId", required = false) Integer zoneId
    ) {
        if (status != null) return ResponseEntity.ok(equipmentRepository.findByStatus(status));
        if (zoneId != null) return ResponseEntity.ok(equipmentRepository.findByCurrentZoneId(zoneId));
        return ResponseEntity.ok(equipmentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Equipment> create(@Valid @RequestBody Equipment equipment) {
        equipment.setEquipmentId(null);
        return ResponseEntity.ok(equipmentRepository.save(equipment));
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Equipment> updateStatus(@PathVariable Integer id, @Valid @RequestBody UpdateStatusRequest request) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        equipment.setStatus(request.getStatus());
        equipment.setCurrentZoneId(request.getCurrentZoneId());
        return ResponseEntity.ok(equipmentRepository.save(equipment));
    }

    @GetMapping("/types")
    public ResponseEntity<List<EquipmentType>> types() {
        return ResponseEntity.ok(equipmentTypeRepository.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity<EquipmentType> createType(@Valid @RequestBody EquipmentType type) {
        type.setTypeId(null);
        return ResponseEntity.ok(equipmentTypeRepository.save(type));
    }

    public static class UpdateStatusRequest {
        @NotNull
        private Short status;
        private Integer currentZoneId;

        public Short getStatus() {
            return status;
        }

        public void setStatus(Short status) {
            this.status = status;
        }

        public Integer getCurrentZoneId() {
            return currentZoneId;
        }

        public void setCurrentZoneId(Integer currentZoneId) {
            this.currentZoneId = currentZoneId;
        }
    }
}

