package com.example.securemessaging.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.securemessaging.dto.InboxResponse;
import com.example.securemessaging.entity.Message;
import com.example.securemessaging.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    
    @PostMapping("/send")
    public Message sendMessage(@RequestParam String receiverUsername,
                               @RequestParam String content) {
        return messageService.sendMessage(receiverUsername ,content);
    }

    @GetMapping("/inbox")
    public List<InboxResponse> inbox() {
        return messageService.getInbox();
    }
    
    @GetMapping("/{conversationId}/messages")
    public List<Message> getConversationMessages(
            @PathVariable Long conversationId) {

        return messageService.getChatHistory(conversationId);
    }

//    @GetMapping("/outbox")
//    public List<Message> outbox() {
//        return messageService.getOutbox();
//    }
}
