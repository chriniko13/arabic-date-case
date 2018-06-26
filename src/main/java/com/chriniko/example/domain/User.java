package com.chriniko.example.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class User {

    private LocalDate birthday;

    private Gender gender;

    private List<String> interests;
}
