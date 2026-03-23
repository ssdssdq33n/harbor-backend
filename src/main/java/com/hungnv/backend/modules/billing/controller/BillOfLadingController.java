package com.hungnv.backend.modules.billing.controller;

import com.hungnv.backend.modules.billing.entity.BillOfLading;
import com.hungnv.backend.modules.billing.entity.BillOfLadingHistory;
import com.hungnv.backend.modules.billing.repository.BillOfLadingHistoryRepository;
import com.hungnv.backend.modules.billing.repository.BillOfLadingRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillOfLadingController {
    private final BillOfLadingRepository billRepository;
    private final BillOfLadingHistoryRepository historyRepository;

    public BillOfLadingController(BillOfLadingRepository billRepository, BillOfLadingHistoryRepository historyRepository) {
        this.billRepository = billRepository;
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public ResponseEntity<List<BillOfLading>> list(@RequestParam(name = "orderId", required = false) Integer orderId) {
        if (orderId != null) return ResponseEntity.ok(billRepository.findByOrderId(orderId));
        return ResponseEntity.ok(billRepository.findAll());
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillOfLading> get(@PathVariable Integer billId) {
        return ResponseEntity.of(billRepository.findById(billId));
    }

    @GetMapping("/{billId}/history")
    public ResponseEntity<List<BillOfLadingHistory>> history(@PathVariable Integer billId) {
        return ResponseEntity.ok(historyRepository.findByBillIdOrderByCreatedAtDesc(billId));
    }

    @PostMapping
    public ResponseEntity<BillOfLading> create(@Valid @RequestBody BillOfLading bill) {
        bill.setBillId(null);
        return ResponseEntity.ok(billRepository.save(bill));
    }

    @PostMapping("/{billId}/status")
    public ResponseEntity<BillOfLadingHistory> changeStatus(@PathVariable Integer billId, @Valid @RequestBody ChangeStatusRequest request) {
        BillOfLading bill = billRepository.findById(billId).orElseThrow();
        bill.setStatusId(request.getStatusId());
        billRepository.save(bill);
        BillOfLadingHistory history = BillOfLadingHistory.builder()
                .billId(billId)
                .statusId(request.getStatusId())
                .description(request.getDescription())
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.ok(historyRepository.save(history));
    }

    public static class ChangeStatusRequest {
        private Integer statusId;
        private String description;

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

