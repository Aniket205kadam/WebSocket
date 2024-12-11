package dev.aniketkadam.chat;

import dev.aniketkadam.chat.chat.ChatMessage;
import dev.aniketkadam.chat.chat.ChatMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ChatAppBackendApplicationTests {
	private final ChatMessageService service;

    ChatAppBackendApplicationTests(ChatMessageService service) {
        this.service = service;
    }

    @Test
	void testFindChatMessages() {
		List<ChatMessage> messages = service.findChatMessages("aniket205kadam", "rohit");

		for (ChatMessage message : messages) {
			System.out.println(message.getContent());
		}
	}

}
