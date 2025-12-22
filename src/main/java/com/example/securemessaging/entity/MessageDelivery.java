package com.example.securemessaging.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_delivery")
public class MessageDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(nullable = false)
    private int retryCount;

    @Column(nullable = false)
    private LocalDateTime lastAttemptAt;

    @Column(length = 500)
    private String failureReason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public LocalDateTime getLastAttemptAt() {
		return lastAttemptAt;
	}

	public void setLastAttemptAt(LocalDateTime lastAttemptAt) {
		this.lastAttemptAt = lastAttemptAt;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

    // getters and setters
}
