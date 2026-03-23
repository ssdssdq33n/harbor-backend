package com.hungnv.backend.modules.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "chat_room_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomMember {
    @EmbeddedId
    private ChatRoomMemberId id;

    @Column(name = "role")
    private String role;

    @Column(name = "joined_at")
    private Instant joinedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatRoomMemberId implements Serializable {
        @Column(name = "room_id")
        private Integer roomId;
        @Column(name = "user_id")
        private Integer userId;
    }
}

