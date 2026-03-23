package com.hungnv.backend.modules.container.service;

import com.hungnv.backend.modules.container.entity.Container;
import com.hungnv.backend.modules.container.entity.ContainerStatusHistory;
import com.hungnv.backend.modules.container.repository.ContainerRepository;
import com.hungnv.backend.modules.container.repository.ContainerStatusHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class ContainerLifecycleService {
    private final ContainerRepository containerRepository;
    private final ContainerStatusHistoryRepository historyRepository;

    public ContainerLifecycleService(ContainerRepository containerRepository, ContainerStatusHistoryRepository historyRepository) {
        this.containerRepository = containerRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public Container changeStatus(String containerId, Integer statusId, String description) {
        Container container = containerRepository.findById(containerId).orElseThrow();
        container.setStatusId(statusId);
        Container saved = containerRepository.save(container);
        historyRepository.save(ContainerStatusHistory.builder()
                .containerId(containerId)
                .statusId(statusId)
                .description(description)
                .createdAt(Instant.now())
                .build());
        return saved;
    }
}

