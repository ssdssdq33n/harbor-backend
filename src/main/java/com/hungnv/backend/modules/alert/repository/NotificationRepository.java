package com.hungnv.backend.modules.alert.repository;

import com.hungnv.backend.modules.alert.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}

