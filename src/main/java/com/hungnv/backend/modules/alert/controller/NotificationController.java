package com.hungnv.backend.modules.alert.controller;

import com.hungnv.backend.modules.alert.entity.Notification;
import com.hungnv.backend.modules.alert.entity.UserNotification;
import com.hungnv.backend.modules.alert.repository.NotificationRepository;
import com.hungnv.backend.modules.alert.repository.UserNotificationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationRepository notificationRepository;
    private final UserNotificationRepository userNotificationRepository;

    public NotificationController(NotificationRepository notificationRepository, UserNotificationRepository userNotificationRepository) {
        this.notificationRepository = notificationRepository;
        this.userNotificationRepository = userNotificationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> listAll() {
        return ResponseEntity.ok(notificationRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Notification> create(@Valid @RequestBody Notification notification) {
        notification.setNotificationId(null);
        if (notification.getCreatedAt() == null) notification.setCreatedAt(Instant.now());
        return ResponseEntity.ok(notificationRepository.save(notification));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<UserNotification>> listForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userNotificationRepository.findByIdUserId(userId));
    }

    @PostMapping("/users/{userId}/read")
    public ResponseEntity<UserNotification> markRead(@PathVariable Integer userId, @Valid @RequestBody MarkReadRequest request) {
        UserNotification.UserNotificationId id = new UserNotification.UserNotificationId(request.getNotificationId(), userId);
        UserNotification un = userNotificationRepository.findById(id).orElseGet(() -> UserNotification.builder().id(id).build());
        un.setIsRead(true);
        if (un.getCreatedAt() == null) un.setCreatedAt(Instant.now());
        return ResponseEntity.ok(userNotificationRepository.save(un));
    }

    public static class MarkReadRequest {
        @NotNull
        private Integer notificationId;

        public Integer getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(Integer notificationId) {
            this.notificationId = notificationId;
        }
    }
}

