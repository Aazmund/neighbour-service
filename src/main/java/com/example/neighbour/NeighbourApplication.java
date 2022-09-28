package com.example.neighbour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.neighbour.*")
public class NeighbourApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeighbourApplication.class, args);
    }

}
