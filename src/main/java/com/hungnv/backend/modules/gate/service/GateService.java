package com.hungnv.backend.modules.gate.service;

import com.hungnv.backend.infrastructure.kafka.KafkaEventProducer;
import com.hungnv.backend.modules.gate.entity.GateInReceipt;
import com.hungnv.backend.modules.gate.entity.GateOutReceipt;
import com.hungnv.backend.modules.gate.repository.GateInReceiptRepository;
import com.hungnv.backend.modules.gate.repository.GateOutReceiptRepository;
import com.hungnv.backend.modules.yard.repository.ContainerPositionRepository;
import com.hungnv.backend.modules.yard.repository.YardStorageRepository;
import com.hungnv.backend.modules.yard.entity.YardStorage;
import com.hungnv.backend.modules.yard.service.RelocationService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;

@Service
public class GateService {
    private final GateInReceiptRepository gateInRepository;
    private final GateOutReceiptRepository gateOutRepository;
    private final RelocationService relocationService;
    private final ContainerPositionRepository positionRepository;
    private final YardStorageRepository yardStorageRepository;
    private final ObjectProvider<KafkaEventProducer> kafkaEventProducer;

    public GateService(
            GateInReceiptRepository gateInRepository,
            GateOutReceiptRepository gateOutRepository,
            RelocationService relocationService,
            ContainerPositionRepository positionRepository,
            YardStorageRepository yardStorageRepository,
            ObjectProvider<KafkaEventProducer> kafkaEventProducer
    ) {
        this.gateInRepository = gateInRepository;
        this.gateOutRepository = gateOutRepository;
        this.relocationService = relocationService;
        this.positionRepository = positionRepository;
        this.yardStorageRepository = yardStorageRepository;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Transactional
    public GateInReceipt gateIn(String containerId, Integer voyageId, Integer createdBy, String note, Integer slotId, Integer tier, Integer yardId) {
        relocationService.move(containerId, slotId, tier, null, createdBy);
        if (yardId != null) {
            yardStorageRepository.save(YardStorage.builder()
                    .containerId(containerId)
                    .yardId(yardId)
                    .storageStartDate(LocalDate.now())
                    .note(note)
                    .build());
        }
        GateInReceipt saved = gateInRepository.save(GateInReceipt.builder()
                .containerId(containerId)
                .voyageId(voyageId)
                .createdBy(createdBy)
                .note(note)
                .gateInTime(Instant.now())
                .build());
        kafkaEventProducer.ifAvailable(p -> p.publishYardEvent(Map.of(
                "type", "GATE_IN",
                "containerId", containerId,
                "voyageId", voyageId,
                "slotId", slotId,
                "tier", tier,
                "yardId", yardId,
                "createdBy", createdBy
        )));
        return saved;
    }

    @Transactional
    public GateOutReceipt gateOut(String containerId, Integer createdBy, String note) {
        yardStorageRepository.findTopByContainerIdOrderByStorageStartDateDesc(containerId).ifPresent(s -> {
            if (s.getStorageEndDate() == null) {
                s.setStorageEndDate(LocalDate.now());
                yardStorageRepository.save(s);
            }
        });
        positionRepository.findByContainerId(containerId).ifPresent(positionRepository::delete);
        GateOutReceipt saved = gateOutRepository.save(GateOutReceipt.builder()
                .containerId(containerId)
                .createdBy(createdBy)
                .note(note)
                .gateOutTime(Instant.now())
                .build());
        kafkaEventProducer.ifAvailable(p -> p.publishYardEvent(Map.of(
                "type", "GATE_OUT",
                "containerId", containerId,
                "createdBy", createdBy
        )));
        return saved;
    }
}
