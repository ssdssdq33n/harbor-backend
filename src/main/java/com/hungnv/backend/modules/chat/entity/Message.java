package com.hungnv.backend.modules.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "room_id")
    private Integer roomId;

    @Column(name = "sender_id")
    private Integer senderId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Instant createdAt;
}

