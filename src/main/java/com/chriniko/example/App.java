package com.chriniko.example;

import com.chriniko.example.domain.User;
import com.chriniko.example.service.UserService;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class App {

    /*
        Note:
        "١٩٥١-٠١-١٢"
        "٢٠٠١-١١-١٠"
     */
    public static void main(String[] args) throws Exception {

        Injector injector = Guice.createInjector(new BasicModule());
        UserService userService = injector.getInstance(UserService.class);


        IntStream.rangeClosed(1, 3)
                .forEach(idx -> {

                    try {
                        String loadedUserJsonFile = loadFile("user" + idx + ".json");
                        User user = userService.extract(loadedUserJsonFile);
                        System.out.println("user" + idx + " = " + user);
                    } catch (Exception o_O) {

                        System.err.println(o_O.getMessage());
                    }

                });
    }

    private static String loadFile(String filename) throws IOException {
        return Files
                .readAllLines(
                        Paths.get("src/main/resources/" + filename)
                )
                .stream()
                .reduce("", String::concat);
    }
}
