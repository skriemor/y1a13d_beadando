package com.epam.training.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableConfigurationProperties
public class TicketServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }


}
