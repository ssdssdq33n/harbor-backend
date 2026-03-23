package com.hungnv.backend.modules.billing.controller;

import com.hungnv.backend.modules.billing.entity.BillOfLadingStatus;
import com.hungnv.backend.modules.billing.repository.BillOfLadingStatusRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillLookupController {
    private final BillOfLadingStatusRepository statusRepository;

    public BillLookupController(BillOfLadingStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/bill-statuses")
    public ResponseEntity<List<BillOfLadingStatus>> billStatuses() {
        return ResponseEntity.ok(statusRepository.findAll());
    }

    @PostMapping("/bill-statuses")
    public ResponseEntity<BillOfLadingStatus> createBillStatus(@Valid @RequestBody BillOfLadingStatus status) {
        status.setStatusId(null);
        return ResponseEntity.ok(statusRepository.save(status));
    }
}

