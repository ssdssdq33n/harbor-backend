package com.hungnv.backend.modules.alert.controller;

import com.hungnv.backend.modules.alert.entity.Alert;
import com.hungnv.backend.modules.alert.repository.AlertRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {
    private final AlertRepository alertRepository;

    public AlertController(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @GetMapping
    public ResponseEntity<List<Alert>> list(
            @RequestParam(name = "status", required = false) Short status,
            @RequestParam(name = "zoneId", required = false) Integer zoneId
    ) {
        if (status != null) return ResponseEntity.ok(alertRepository.findByStatus(status));
        if (zoneId != null) return ResponseEntity.ok(alertRepository.findByZoneId(zoneId));
        return ResponseEntity.ok(alertRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Alert> create(@Valid @RequestBody Alert alert) {
        alert.setAlertId(null);
        if (alert.getCreatedAt() == null) alert.setCreatedAt(Instant.now());
        if (alert.getStatus() == null) alert.setStatus((short) 0);
        return ResponseEntity.ok(alertRepository.save(alert));
    }

    @PostMapping("/{id}/resolve")
    @Transactional
    public ResponseEntity<Alert> resolve(@PathVariable Integer id, @Valid @RequestBody ResolveRequest request) {
        Alert alert = alertRepository.findById(id).orElseThrow();
        alert.setStatus((short) 1);
        alert.setResolvedAt(Instant.now());
        if (request.getDescription() != null && !request.getDescription().isBlank()) {
            alert.setDescription(request.getDescription());
        }
        return ResponseEntity.ok(alertRepository.save(alert));
    }

    public static class ResolveRequest {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
