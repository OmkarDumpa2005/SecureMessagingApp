package com.example.securemessaging.repository;

import com.example.securemessaging.entity.Message;
import com.example.securemessaging.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiver(User receiver);

    List<Message> findBySender(User sender);
}
