package com.chriniko.example.service;

import com.chriniko.example.domain.User;
import com.chriniko.example.infrastructure.JsonParser;
import com.google.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private JsonParser jsonParser;

    @Override
    public User extract(String json) {
        return jsonParser
                .parse(json)
                .orElse(null);
    }
}
