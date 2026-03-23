package com.hungnv.backend.modules.container.controller;

import com.hungnv.backend.modules.container.entity.CargoAttribute;
import com.hungnv.backend.modules.container.entity.CargoType;
import com.hungnv.backend.modules.container.entity.ContainerStatus;
import com.hungnv.backend.modules.container.entity.ContainerType;
import com.hungnv.backend.modules.container.repository.CargoAttributeRepository;
import com.hungnv.backend.modules.container.repository.CargoTypeRepository;
import com.hungnv.backend.modules.container.repository.ContainerStatusRepository;
import com.hungnv.backend.modules.container.repository.ContainerTypeRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/container-lookups")
public class ContainerLookupController {
    private final ContainerTypeRepository typeRepository;
    private final ContainerStatusRepository statusRepository;
    private final CargoTypeRepository cargoTypeRepository;
    private final CargoAttributeRepository cargoAttributeRepository;

    public ContainerLookupController(
            ContainerTypeRepository typeRepository,
            ContainerStatusRepository statusRepository,
            CargoTypeRepository cargoTypeRepository,
            CargoAttributeRepository cargoAttributeRepository
    ) {
        this.typeRepository = typeRepository;
        this.statusRepository = statusRepository;
        this.cargoTypeRepository = cargoTypeRepository;
        this.cargoAttributeRepository = cargoAttributeRepository;
    }

    @GetMapping("/types")
    public ResponseEntity<List<ContainerType>> types() {
        return ResponseEntity.ok(typeRepository.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity<ContainerType> createType(@Valid @RequestBody ContainerType type) {
        type.setContainerTypeId(null);
        return ResponseEntity.ok(typeRepository.save(type));
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<ContainerStatus>> statuses() {
        return ResponseEntity.ok(statusRepository.findAll());
    }

    @PostMapping("/statuses")
    public ResponseEntity<ContainerStatus> createStatus(@Valid @RequestBody ContainerStatus status) {
        status.setStatusId(null);
        return ResponseEntity.ok(statusRepository.save(status));
    }

    @GetMapping("/cargo-types")
    public ResponseEntity<List<CargoType>> cargoTypes() {
        return ResponseEntity.ok(cargoTypeRepository.findAll());
    }

    @PostMapping("/cargo-types")
    public ResponseEntity<CargoType> createCargoType(@Valid @RequestBody CargoType type) {
        type.setCargoTypeId(null);
        return ResponseEntity.ok(cargoTypeRepository.save(type));
    }

    @GetMapping("/cargo-attributes")
    public ResponseEntity<List<CargoAttribute>> cargoAttributes() {
        return ResponseEntity.ok(cargoAttributeRepository.findAll());
    }

    @PostMapping("/cargo-attributes")
    public ResponseEntity<CargoAttribute> createCargoAttribute(@Valid @RequestBody CargoAttribute attr) {
        attr.setAttributeId(null);
        return ResponseEntity.ok(cargoAttributeRepository.save(attr));
    }
}

