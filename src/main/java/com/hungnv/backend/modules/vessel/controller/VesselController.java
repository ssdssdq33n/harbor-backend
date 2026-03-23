package com.hungnv.backend.modules.vessel.controller;

import com.hungnv.backend.modules.vessel.entity.Vessel;
import com.hungnv.backend.modules.vessel.repository.VesselRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vessels")
public class VesselController {
    private final VesselRepository vesselRepository;

    public VesselController(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vessel>> list() {
        return ResponseEntity.ok(vesselRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Vessel> create(@Valid @RequestBody Vessel vessel) {
        return ResponseEntity.ok(vesselRepository.save(vessel));
    }
}

