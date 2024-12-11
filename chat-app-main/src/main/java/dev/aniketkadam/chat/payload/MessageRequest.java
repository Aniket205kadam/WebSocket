package dev.aniketkadam.chat.payload;

import lombok.*;

import java.time.LocalDateTime;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class MessageRequest {
    private String content;
    private String sender;
//    private LocalDateTime timestamp;

    public MessageRequest() {}

    public MessageRequest(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
