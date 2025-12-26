package com.example.securemessaging.repository;

import com.example.securemessaging.entity.Conversation;
import com.example.securemessaging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    // Find conversation between two users (order-independent)
    Optional<Conversation> findByUser1AndUser2(User user1, User user2);

    Optional<Conversation> findByUser2AndUser1(User user1, User user2);

    // Inbox: all conversations where user participates
    List<Conversation> findByUser1OrUser2(User user1, User user2);
}
