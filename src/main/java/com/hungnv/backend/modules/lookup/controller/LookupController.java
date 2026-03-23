package com.hungnv.backend.modules.lookup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lookups")
public class LookupController {
    private final JdbcTemplate jdbcTemplate;

    public LookupController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/container-types")
    public ResponseEntity<List<Map<String, Object>>> containerTypes() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from container_types order by container_type_id"));
    }

    @GetMapping("/container-statuses")
    public ResponseEntity<List<Map<String, Object>>> containerStatuses() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from container_statuses order by status_id"));
    }

    @GetMapping("/cargo-types")
    public ResponseEntity<List<Map<String, Object>>> cargoTypes() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from cargo_types order by cargo_type_id"));
    }

    @GetMapping("/yard-types")
    public ResponseEntity<List<Map<String, Object>>> yardTypes() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from yard_types order by yard_type_id"));
    }

    @GetMapping("/block-types")
    public ResponseEntity<List<Map<String, Object>>> blockTypes() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from block_types order by block_type_id"));
    }

    @GetMapping("/alert-types")
    public ResponseEntity<List<Map<String, Object>>> alertTypes() {
        return ResponseEntity.ok(jdbcTemplate.queryForList("select * from alert_type order by alert_type_id"));
    }
}

