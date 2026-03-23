package com.hungnv.backend.modules.vessel.controller;

import com.hungnv.backend.modules.vessel.entity.Manifest;
import com.hungnv.backend.modules.vessel.repository.ManifestRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manifests")
public class ManifestController {
    private final ManifestRepository manifestRepository;

    public ManifestController(ManifestRepository manifestRepository) {
        this.manifestRepository = manifestRepository;
    }

    @GetMapping
    public ResponseEntity<List<Manifest>> list(@RequestParam(name = "voyageId", required = false) Integer voyageId) {
        if (voyageId != null) return ResponseEntity.ok(manifestRepository.findByVoyageId(voyageId));
        return ResponseEntity.ok(manifestRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Manifest> create(@Valid @RequestBody Manifest manifest) {
        return ResponseEntity.ok(manifestRepository.save(manifest));
    }
}

