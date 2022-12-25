package com.example.Carrie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadOrders {

    @Bean
    CommandLineRunner initDatabase(OrderRepository repo) {
        return args -> {

        };
    }
}
