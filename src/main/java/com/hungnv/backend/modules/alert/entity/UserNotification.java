package com.hungnv.backend.modules.alert.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "user_notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNotification {
    @EmbeddedId
    private UserNotificationId id;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "created_at")
    private Instant createdAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationId implements Serializable {
        @Column(name = "notification_id")
        private Integer notificationId;
        @Column(name = "user_id")
        private Integer userId;
    }
}

