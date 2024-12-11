package dev.aniketkadam.chat.chat;

import dev.aniketkadam.chat.chatroom.ChatRoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomService chatRoomService) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomService = chatRoomService;
    }

    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatRoomId
                (
                        chatMessage.getSenderId(),
                        chatMessage.getRecipientId(),
                        true
                ).orElseThrow(); // todo - create the dedicated Exception
        chatMessage.setChatId(chatId);
        chatMessage.setId(String.valueOf(UUID.randomUUID()));
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(
            String senderId,
            String recipientId
    ) {
        String chatId = chatRoomService.getChatRoomId
                (
                        senderId,
                        recipientId,
                        true // todo - here first false
                ).orElseThrow(() -> new EntityNotFoundException("Chat-Room is not found!")); // todo - create the dedicated Exception
        List<ChatMessage> chatMessages = chatMessageRepository.findByChatId(chatId);
        if (chatMessages.isEmpty()) {
            return new ArrayList<>();
        }
        return chatMessages;
    }
}
