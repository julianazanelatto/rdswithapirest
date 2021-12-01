package com.dioclass.rdswithapirest.MyfirstApi;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MyFirstApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.dioclass.rdswithapirest.MyfirstApi.MyFirstApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            personRepository.save(new Person("João","Silva"));
            personRepository.save(new Person("Maria","Antonieta"));
            personRepository.save(new Person("Ada","Lovace"));
        };
    }
}
