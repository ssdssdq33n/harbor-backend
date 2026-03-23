package com.hungnv.backend.modules.chat.repository;

import com.hungnv.backend.modules.chat.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRoomIdOrderByCreatedAtAsc(Integer roomId);
}

