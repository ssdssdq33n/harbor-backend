package com.hungnv.backend.modules.alert.repository;

import com.hungnv.backend.modules.alert.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotification.UserNotificationId> {
    List<UserNotification> findByIdUserId(Integer userId);
}

