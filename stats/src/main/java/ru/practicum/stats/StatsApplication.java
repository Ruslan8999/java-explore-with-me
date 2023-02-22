package ru.practicum.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class StatsApplication {
    private static final String port = "9090";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StatsApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));
        app.run(args);
    }

}
