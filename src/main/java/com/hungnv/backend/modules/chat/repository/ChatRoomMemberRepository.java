package com.hungnv.backend.modules.chat.repository;

import com.hungnv.backend.modules.chat.entity.ChatRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, ChatRoomMember.ChatRoomMemberId> {
    List<ChatRoomMember> findByIdUserId(Integer userId);
    List<ChatRoomMember> findByIdRoomId(Integer roomId);
}

