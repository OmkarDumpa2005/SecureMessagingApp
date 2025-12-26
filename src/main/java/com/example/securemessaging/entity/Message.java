package com.example.securemessaging.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_id")
    private User sender;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "receiver_id")
//    private User receiver;

    @Column(nullable = false, length = 1000)
    private String content;

    public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

//	public User getReceiver() {
//		return receiver;
//	}
//
//	public void setReceiver(User receiver) {
//		this.receiver = receiver;
//	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    // getters and setters
}
