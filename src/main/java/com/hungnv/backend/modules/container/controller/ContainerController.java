package com.hungnv.backend.modules.container.controller;

import com.hungnv.backend.modules.container.entity.Container;
import com.hungnv.backend.modules.container.entity.ExportPriority;
import com.hungnv.backend.modules.container.repository.ContainerRepository;
import com.hungnv.backend.modules.container.repository.ExportPriorityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
public class ContainerController {
    private final ContainerRepository containerRepository;
    private final ExportPriorityRepository exportPriorityRepository;

    public ContainerController(ContainerRepository containerRepository, ExportPriorityRepository exportPriorityRepository) {
        this.containerRepository = containerRepository;
        this.exportPriorityRepository = exportPriorityRepository;
    }

    @GetMapping
    public ResponseEntity<List<Container>> list() {
        return ResponseEntity.ok(containerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> get(@PathVariable String id) {
        return ResponseEntity.of(containerRepository.findById(id));
    }

    @GetMapping("/{id}/export-priority")
    public ResponseEntity<ExportPriority> exportPriority(@PathVariable String id) {
        return ResponseEntity.of(exportPriorityRepository.findByContainerId(id));
    }

    @PostMapping("/{id}/export-priority")
    public ResponseEntity<ExportPriority> upsertExportPriority(@PathVariable String id, @Valid @RequestBody UpsertExportPriorityRequest request) {
        ExportPriority ep = exportPriorityRepository.findByContainerId(id).orElseGet(() -> ExportPriority.builder().containerId(id).build());
        ep.setPriorityLevel(request.getPriorityLevel());
        ep.setNote(request.getNote());
        return ResponseEntity.ok(exportPriorityRepository.save(ep));
    }

    public static class UpsertExportPriorityRequest {
        @NotNull
        private Integer priorityLevel;
        private String note;

        public Integer getPriorityLevel() {
            return priorityLevel;
        }

        public void setPriorityLevel(Integer priorityLevel) {
            this.priorityLevel = priorityLevel;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
