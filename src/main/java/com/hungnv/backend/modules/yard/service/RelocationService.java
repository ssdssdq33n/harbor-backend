package com.hungnv.backend.modules.yard.service;

import com.hungnv.backend.infrastructure.kafka.KafkaEventProducer;
import com.hungnv.backend.modules.yard.entity.ContainerPosition;
import com.hungnv.backend.modules.yard.entity.ContainerPositionHistory;
import com.hungnv.backend.modules.yard.entity.Slot;
import com.hungnv.backend.modules.yard.repository.ContainerPositionHistoryRepository;
import com.hungnv.backend.modules.yard.repository.ContainerPositionRepository;
import com.hungnv.backend.modules.yard.repository.SlotRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;

@Service
public class RelocationService {
    private final ContainerPositionRepository positionRepository;
    private final ContainerPositionHistoryRepository historyRepository;
    private final SlotRepository slotRepository;
    private final ObjectProvider<KafkaEventProducer> kafkaEventProducer;

    public RelocationService(
            ContainerPositionRepository positionRepository,
            ContainerPositionHistoryRepository historyRepository,
            SlotRepository slotRepository,
            ObjectProvider<KafkaEventProducer> kafkaEventProducer
    ) {
        this.positionRepository = positionRepository;
        this.historyRepository = historyRepository;
        this.slotRepository = slotRepository;
        this.kafkaEventProducer = kafkaEventProducer;
    }

    @Transactional
    public ContainerPosition move(String containerId, Integer slotId, Integer tier, Integer equipmentId, Integer movedBy) {
        Slot slot = slotRepository.findById(slotId).orElseThrow();
        if (tier < 1 || (slot.getMaxTier() != null && tier > slot.getMaxTier())) throw new RuntimeException("Invalid tier");
        if (positionRepository.findBySlotIdAndTier(slotId, tier).isPresent()) throw new RuntimeException("Target tier occupied");

        ContainerPosition position = positionRepository.findByContainerId(containerId).orElseGet(() -> ContainerPosition.builder().containerId(containerId).build());
        position.setSlotId(slotId);
        position.setTier(tier);
        position.setUpdatedAt(Instant.now());
        ContainerPosition saved = positionRepository.save(position);

        historyRepository.save(ContainerPositionHistory.builder()
                .containerId(containerId)
                .slotId(slotId)
                .tier(tier)
                .equipmentId(equipmentId)
                .movedBy(movedBy)
                .movedAt(Instant.now())
                .build());
        kafkaEventProducer.ifAvailable(p -> p.publishYardEvent(Map.of(
                "type", "MOVE",
                "containerId", containerId,
                "slotId", slotId,
                "tier", tier,
                "equipmentId", equipmentId,
                "movedBy", movedBy
        )));
        return saved;
    }
}
