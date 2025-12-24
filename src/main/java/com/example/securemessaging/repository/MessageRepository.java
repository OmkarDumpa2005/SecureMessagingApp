package com.example.securemessaging.repository;

import com.example.securemessaging.entity.Message;
import com.example.securemessaging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Inbox: messages received by user
    List<Message> findByReceiver(User receiver);

    // Outbox: messages sent by user
    List<Message> findBySender(User sender);

    // Conversation between two users
    List<Message> findBySenderAndReceiver(User sender, User receiver);
}

