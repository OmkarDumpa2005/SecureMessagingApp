package com.example.securemessaging.service.impl;

import com.example.securemessaging.entity.Message;
import com.example.securemessaging.entity.MessageStatus;
import com.example.securemessaging.entity.User;
import com.example.securemessaging.repository.MessageRepository;
import com.example.securemessaging.repository.UserRepository;
import com.example.securemessaging.service.MessageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Message sendMessage(String content, Long receiverId) {

        // 1. Get authenticated user
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User sender = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        // 2. Get receiver
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // 3. Create message
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setStatus(MessageStatus.SENT);
        message.setCreatedAt(LocalDateTime.now());

        // 4. Save
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getInbox() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return messageRepository.findByReceiver(user);
    }

    @Override
    public List<Message> getOutbox() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return messageRepository.findBySender(user);
    }
}
