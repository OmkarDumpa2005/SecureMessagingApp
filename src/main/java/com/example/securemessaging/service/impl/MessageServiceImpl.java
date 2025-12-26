package com.example.securemessaging.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.securemessaging.dto.InboxResponse;
import com.example.securemessaging.entity.Conversation;
import com.example.securemessaging.entity.Message;
import com.example.securemessaging.entity.MessageStatus;
import com.example.securemessaging.entity.User;
import com.example.securemessaging.repository.MessageRepository;
import com.example.securemessaging.repository.UserRepository;
import com.example.securemessaging.service.ConversationService;
import com.example.securemessaging.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ConversationService conversationService;

    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository,
                              ConversationService conversationService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.conversationService = conversationService;
    }

    @Override
    public Message sendMessage(String receiverUsername, String content) {

        String senderUsername = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Conversation conversation =
                conversationService.getOrCreateConversation(sender, receiver);

        Message message = new Message();
        message.setConversation(conversation);
        message.setSender(sender);
        message.setContent(content);
        message.setStatus(MessageStatus.SENT);
        message.setCreatedAt(LocalDateTime.now());

        return messageRepository.save(message);
    }

    

    @Override
    public List<Message> getChatHistory(Long conversationId) {

        Conversation conversation = conversationService
                .getUserConversations(
                        userRepository.findByUsername(
                                SecurityContextHolder.getContext()
                                        .getAuthentication().getName()
                        ).orElseThrow()
                )
                .stream()
                .filter(c -> c.getId().equals(conversationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unauthorized access"));

        return messageRepository.findByConversationOrderByCreatedAtAsc(conversation);
    }
    
    @Override
    public List<InboxResponse> getInbox() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Conversation> conversations =
                conversationService.getUserConversations(currentUser);

        return conversations.stream().map(conversation -> {

            // Identify the other user
            User otherUser = conversation.getUser1().equals(currentUser)
                    ? conversation.getUser2()
                    : conversation.getUser1();

            // Get last message
            Message lastMessage = messageRepository
                    .findByConversationOrderByCreatedAtDesc(conversation)
                    .stream()
                    .findFirst()
                    .orElse(null);

            return new InboxResponse(
                    conversation.getId(),
                    otherUser.getUsername(),
                    lastMessage != null ? lastMessage.getContent() : "",
                    lastMessage != null ? lastMessage.getCreatedAt() : null
            );

        }).toList();
    }
    
    

}





//package com.example.securemessaging.service.impl;
//
//import com.example.securemessaging.entity.Message;
//import com.example.securemessaging.entity.MessageStatus;
//import com.example.securemessaging.entity.User;
//import com.example.securemessaging.repository.MessageRepository;
//import com.example.securemessaging.repository.UserRepository;
//import com.example.securemessaging.service.MessageService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class MessageServiceImpl implements MessageService {
//
//    private final MessageRepository messageRepository;
//    private final UserRepository userRepository;
//
//    public MessageServiceImpl(MessageRepository messageRepository,
//                              UserRepository userRepository) {
//        this.messageRepository = messageRepository;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Message sendMessage(String content, Long receiverId) {
//
//        // 1. Get authenticated user
//        String username = SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getName();
//
//        User sender = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Sender not found"));
//
//        // 2. Get receiver
//        User receiver = userRepository.findById(receiverId)
//                .orElseThrow(() -> new RuntimeException("Receiver not found"));
//
//        // 3. Create message
//        Message message = new Message();
//        message.setSender(sender);
//        message.setReceiver(receiver);
//        message.setContent(content);
//        message.setStatus(MessageStatus.SENT);
//        message.setCreatedAt(LocalDateTime.now());
//
//        // 4. Save
//        return messageRepository.save(message);
//    }
//
//    @Override
//    public List<Message> getInbox() {
//
//        String username = SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getName();
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return messageRepository.findByReceiver(user);
//    }
//
//    @Override
//    public List<Message> getOutbox() {
//
//        String username = SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getName();
//
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return messageRepository.findBySender(user);
//    }
//}
