package com.example.backend_keys;

import com.example.backend_keys.customer.Customer;
import com.example.backend_keys.customer.CustomerRepisotory;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BackEndKeysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndKeysApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepisotory customerRepisotory) {
        return args -> {
            generateRandomCustomers(customerRepisotory);
            customerRepisotory.findById(2).ifPresentOrElse(System.out::println,()->{
                System.out.println("customer 3 not founr");
            });


        };
    }

    private void generateRandomCustomers(CustomerRepisotory customerRepisotory) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            String name = faker.name().firstName();

            String lastName = faker.name().lastName();
            String email = String.format("%s.@adibo.com", name);
            LocalDateTime localDateTime=LocalDateTime.now();
            Customer student = new Customer(
                    name,
                    email,
                    faker.number().numberBetween(17, 55));
            customerRepisotory.save(student);
        }
    }

    }


