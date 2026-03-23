package com.hungnv.backend.modules.chat.repository;

import com.hungnv.backend.modules.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
}

