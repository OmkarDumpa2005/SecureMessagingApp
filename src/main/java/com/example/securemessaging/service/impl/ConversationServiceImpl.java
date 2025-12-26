package com.example.securemessaging.service.impl;

import com.example.securemessaging.entity.Conversation;
import com.example.securemessaging.entity.User;
import com.example.securemessaging.repository.ConversationRepository;
import com.example.securemessaging.service.ConversationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Conversation getOrCreateConversation(User user1, User user2) {

        return conversationRepository
                .findByUser1AndUser2(user1, user2)
                .or(() -> conversationRepository.findByUser2AndUser1(user1, user2))
                .orElseGet(() -> {
                    Conversation conversation = new Conversation();
                    conversation.setUser1(user1);
                    conversation.setUser2(user2);
                    conversation.setCreatedAt(LocalDateTime.now());
                    return conversationRepository.save(conversation);
                });
    }

    @Override
    public List<Conversation> getUserConversations(User user) {
        return conversationRepository.findByUser1OrUser2(user, user);
    }
}
