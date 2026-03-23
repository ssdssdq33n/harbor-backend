package com.hungnv.backend.modules.chat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_room_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "type_name")
    private String typeName;
}

