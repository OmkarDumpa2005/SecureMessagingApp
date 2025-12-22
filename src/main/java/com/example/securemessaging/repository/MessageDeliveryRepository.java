package com.example.securemessaging.repository;

import com.example.securemessaging.entity.MessageDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDeliveryRepository extends JpaRepository<MessageDelivery, Long> {

    List<MessageDelivery> findByRetryCountLessThan(int maxRetries);
}
