package com.example.securemessaging.service;

import java.util.List;

import com.example.securemessaging.dto.InboxResponse;
import com.example.securemessaging.entity.Message;

public interface MessageService {

    Message sendMessage(String receiverUsername, String content);

    List<Message> getChatHistory(Long conversationId);
    
    List<InboxResponse> getInbox();

}



//package com.example.securemessaging.service;
//
//import com.example.securemessaging.entity.Message;
//
//import java.util.List;
//
//public interface MessageService {
//
//    Message sendMessage(String content, Long receiverId);
//
//    List<Message> getInbox();
//
//    List<Message> getOutbox();
//}
