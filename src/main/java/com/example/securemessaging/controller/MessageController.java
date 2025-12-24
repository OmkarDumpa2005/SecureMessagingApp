package com.example.securemessaging.controller;

import com.example.securemessaging.entity.Message;
import com.example.securemessaging.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public Message sendMessage(@RequestParam String content,
                               @RequestParam Long receiverId) {
        return messageService.sendMessage(content, receiverId);
    }

    @GetMapping("/inbox")
    public List<Message> inbox() {
        return messageService.getInbox();
    }

    @GetMapping("/outbox")
    public List<Message> outbox() {
        return messageService.getOutbox();
    }
}
