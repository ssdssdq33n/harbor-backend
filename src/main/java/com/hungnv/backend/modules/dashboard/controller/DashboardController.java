package com.hungnv.backend.modules.dashboard.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final JdbcTemplate jdbcTemplate;
    public DashboardController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> summary() {
        Integer totalContainers = jdbcTemplate.queryForObject("select count(*) from container", Integer.class);
        Integer inYard = jdbcTemplate.queryForObject("select count(*) from container_positions", Integer.class);
        Integer zones = jdbcTemplate.queryForObject("select count(*) from yard_zones", Integer.class);
        return ResponseEntity.ok(Map.of(
                "totalContainers", totalContainers,
                "inYard", inYard,
                "zones", zones
        ));
    }
}

