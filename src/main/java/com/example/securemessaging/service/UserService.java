package com.example.securemessaging.service;

import com.example.securemessaging.entity.User;

public interface UserService {

    User createUser(String username, String password);
}
