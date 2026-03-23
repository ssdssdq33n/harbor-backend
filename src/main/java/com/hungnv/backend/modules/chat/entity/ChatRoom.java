package com.hungnv.backend.modules.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "chat_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "created_at")
    private Instant createdAt;
}

