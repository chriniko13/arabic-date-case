package com.chriniko.example.service;

import com.chriniko.example.domain.User;

public interface UserService {
    User extract(String json);
}
