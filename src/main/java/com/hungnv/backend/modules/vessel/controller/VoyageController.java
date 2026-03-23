package com.hungnv.backend.modules.vessel.controller;

import com.hungnv.backend.modules.vessel.entity.Voyage;
import com.hungnv.backend.modules.vessel.repository.VoyageRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voyages")
public class VoyageController {
    private final VoyageRepository voyageRepository;

    public VoyageController(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    @GetMapping
    public ResponseEntity<List<Voyage>> list(@RequestParam(name = "vesselId", required = false) Integer vesselId) {
        if (vesselId != null) return ResponseEntity.ok(voyageRepository.findByVesselId(vesselId));
        return ResponseEntity.ok(voyageRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Voyage> create(@Valid @RequestBody Voyage voyage) {
        return ResponseEntity.ok(voyageRepository.save(voyage));
    }
}

