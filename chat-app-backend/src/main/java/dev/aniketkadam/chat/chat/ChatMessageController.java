package dev.aniketkadam.chat.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatMessageController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    public ChatMessageController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService) {
        this.messagingTemplate = messagingTemplate;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ) {
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        // aniket/queue/messages
        messagingTemplate.convertAndSendToUser(
                savedMsg.getRecipientId(),
                "/queue/messages",
                new ChatNotification(
                        savedMsg.getChatId(), savedMsg.getSenderId(), savedMsg.getRecipientId(), savedMsg.getContent()
                )
        );
    }

    @GetMapping("/messages/{sender-id}/{recipient-id}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("sender-id") String senderId,
            @PathVariable("recipient-id") String recipientId
    ) {
        System.out.println(senderId);
        System.out.println(recipientId);
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}

/*
* Instead of using the @SendTo annotation to send messages to a specific destination in a controller method, you can use the SimpMessagingTemplate class to send messages programmatically.
*/