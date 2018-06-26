package com.chriniko.example;

import com.chriniko.example.service.UserService;
import com.chriniko.example.service.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class).in(Scopes.SINGLETON);
    }
}
