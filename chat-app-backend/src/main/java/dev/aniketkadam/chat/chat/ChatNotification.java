package dev.aniketkadam.chat.chat;

public class ChatNotification {
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;

    // constructor
    public ChatNotification() {}

    public ChatNotification(String chatId, String senderId, String recipientId, String content) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }

    // setter and Getter
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toString
    @Override
    public String toString() {
        return "ChatNotification{" +
                "chatId='" + chatId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
