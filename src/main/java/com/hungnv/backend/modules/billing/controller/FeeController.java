package com.hungnv.backend.modules.billing.controller;

import com.hungnv.backend.modules.billing.entity.FeeConfig;
import com.hungnv.backend.modules.billing.entity.Invoice;
import com.hungnv.backend.modules.billing.entity.Payment;
import com.hungnv.backend.modules.billing.repository.FeeConfigRepository;
import com.hungnv.backend.modules.billing.repository.InvoiceRepository;
import com.hungnv.backend.modules.billing.repository.PaymentRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {
    private final FeeConfigRepository feeConfigRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public FeeController(FeeConfigRepository feeConfigRepository, InvoiceRepository invoiceRepository, PaymentRepository paymentRepository) {
        this.feeConfigRepository = feeConfigRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/configs")
    public ResponseEntity<List<FeeConfig>> configs() {
        return ResponseEntity.ok(feeConfigRepository.findAll());
    }

    @PostMapping("/configs")
    public ResponseEntity<FeeConfig> createConfig(@Valid @RequestBody FeeConfig config) {
        config.setFeeId(null);
        if (config.getCreatedAt() == null) config.setCreatedAt(Instant.now());
        return ResponseEntity.ok(feeConfigRepository.save(config));
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> invoices(@RequestParam(name = "orderId", required = false) Integer orderId) {
        if (orderId != null) return ResponseEntity.ok(invoiceRepository.findByOrderId(orderId));
        return ResponseEntity.ok(invoiceRepository.findAll());
    }

    @PostMapping("/invoices")
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice) {
        invoice.setInvoiceId(null);
        if (invoice.getCreatedAt() == null) invoice.setCreatedAt(Instant.now());
        if (invoice.getStatus() == null) invoice.setStatus("UNPAID");
        return ResponseEntity.ok(invoiceRepository.save(invoice));
    }

    @GetMapping("/invoices/{invoiceId}/payments")
    public ResponseEntity<List<Payment>> payments(@PathVariable Integer invoiceId) {
        return ResponseEntity.ok(paymentRepository.findByInvoiceId(invoiceId));
    }

    @PostMapping("/invoices/{invoiceId}/payments")
    @Transactional
    public ResponseEntity<Payment> pay(@PathVariable Integer invoiceId, @Valid @RequestBody CreatePaymentRequest request) {
        Payment payment = Payment.builder()
                .invoiceId(invoiceId)
                .amount(request.getAmount())
                .method(request.getMethod())
                .transactionRef(request.getTransactionRef())
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.ok(paymentRepository.save(payment));
    }

    public static class CreatePaymentRequest {
        @NotNull
        private BigDecimal amount;
        private String method;
        private String transactionRef;

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getTransactionRef() {
            return transactionRef;
        }

        public void setTransactionRef(String transactionRef) {
            this.transactionRef = transactionRef;
        }
    }
}

