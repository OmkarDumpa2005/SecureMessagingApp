package com.example.securemessaging.service;

import com.example.securemessaging.entity.Conversation;
import com.example.securemessaging.entity.User;

import java.util.List;

public interface ConversationService {

    Conversation getOrCreateConversation(User user1, User user2);

    List<Conversation> getUserConversations(User user);
}
