package com.hungnv.backend.modules.chat.controller;

import com.hungnv.backend.modules.chat.entity.ChatRoom;
import com.hungnv.backend.modules.chat.entity.ChatRoomMember;
import com.hungnv.backend.modules.chat.entity.Message;
import com.hungnv.backend.modules.chat.repository.ChatRoomMemberRepository;
import com.hungnv.backend.modules.chat.repository.ChatRoomRepository;
import com.hungnv.backend.modules.chat.repository.MessageRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatRoomRepository roomRepository;
    private final ChatRoomMemberRepository memberRepository;
    private final MessageRepository messageRepository;

    public ChatController(ChatRoomRepository roomRepository, ChatRoomMemberRepository memberRepository, MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.memberRepository = memberRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> rooms() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping("/rooms/users/{userId}")
    public ResponseEntity<List<ChatRoomMember>> roomsForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(memberRepository.findByIdUserId(userId));
    }

    @PostMapping("/rooms")
    public ResponseEntity<ChatRoom> createRoom(@Valid @RequestBody CreateRoomRequest request) {
        ChatRoom room = ChatRoom.builder()
                .typeId(request.getTypeId())
                .roomName(request.getRoomName())
                .createdAt(Instant.now())
                .build();
        ChatRoom saved = roomRepository.save(room);
        memberRepository.save(ChatRoomMember.builder()
                .id(new ChatRoomMember.ChatRoomMemberId(saved.getRoomId(), request.getOwnerUserId()))
                .role("owner")
                .joinedAt(Instant.now())
                .build());
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/rooms/{roomId}/join")
    public ResponseEntity<ChatRoomMember> join(@PathVariable Integer roomId, @Valid @RequestBody JoinRequest request) {
        ChatRoomMember member = ChatRoomMember.builder()
                .id(new ChatRoomMember.ChatRoomMemberId(roomId, request.getUserId()))
                .role(request.getRole() == null ? "member" : request.getRole())
                .joinedAt(Instant.now())
                .build();
        return ResponseEntity.ok(memberRepository.save(member));
    }

    @GetMapping("/rooms/{roomId}/messages")
    public ResponseEntity<List<Message>> messages(@PathVariable Integer roomId) {
        return ResponseEntity.ok(messageRepository.findByRoomIdOrderByCreatedAtAsc(roomId));
    }

    @PostMapping("/rooms/{roomId}/messages")
    public ResponseEntity<Message> send(@PathVariable Integer roomId, @Valid @RequestBody SendMessageRequest request) {
        Message msg = Message.builder()
                .roomId(roomId)
                .senderId(request.getSenderId())
                .description(request.getDescription())
                .createdAt(Instant.now())
                .build();
        return ResponseEntity.ok(messageRepository.save(msg));
    }

    public static class CreateRoomRequest {
        @NotNull
        private Integer typeId;
        private String roomName;
        @NotNull
        private Integer ownerUserId;

        public Integer getTypeId() {
            return typeId;
        }

        public void setTypeId(Integer typeId) {
            this.typeId = typeId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public Integer getOwnerUserId() {
            return ownerUserId;
        }

        public void setOwnerUserId(Integer ownerUserId) {
            this.ownerUserId = ownerUserId;
        }
    }

    public static class JoinRequest {
        @NotNull
        private Integer userId;
        private String role;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class SendMessageRequest {
        @NotNull
        private Integer senderId;
        @NotBlank
        private String description;

        public Integer getSenderId() {
            return senderId;
        }

        public void setSenderId(Integer senderId) {
            this.senderId = senderId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

