package com.example.securemessaging.dto;

import java.time.LocalDateTime;

public class InboxResponse {

    private Long conversationId;
    private String username;
    private String lastMessage;
    private LocalDateTime lastMessageTime;

    public InboxResponse(Long conversationId,
                         String username,
                         String lastMessage,
                         LocalDateTime lastMessageTime) {
        this.conversationId = conversationId;
        this.username = username;
        this.lastMessage = lastMessage;
        this.lastMessageTime = lastMessageTime;
    }

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public LocalDateTime getLastMessageTime() {
		return lastMessageTime;
	}

	public void setLastMessageTime(LocalDateTime lastMessageTime) {
		this.lastMessageTime = lastMessageTime;
	}

    // getters
}
