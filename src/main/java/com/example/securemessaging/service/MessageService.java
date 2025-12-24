package com.example.securemessaging.service;

import com.example.securemessaging.entity.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(String content, Long receiverId);

    List<Message> getInbox();

    List<Message> getOutbox();
}
