package com.chriniko.example.infrastructure;

import com.chriniko.example.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.vavr.control.Try;

import java.time.LocalDate;
import java.util.Optional;

public class JsonParser {

    private Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }

    public Optional<User> parse(String json) {

        return Try
                .of(() -> gson.fromJson(json, User.class))
                .map(Optional::of)
                .getOrElseGet(error -> {
                    System.err.println("Error occurred: " + error);
                    return Optional.empty();
                });

    }
}
