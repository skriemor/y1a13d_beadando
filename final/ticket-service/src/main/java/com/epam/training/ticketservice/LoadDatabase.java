package com.epam.training.ticketservice;

import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import com.epam.training.ticketservice.movie.repository.RoomEntityRepository;
import com.epam.training.ticketservice.movie.repository.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LoadDatabase {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:file:C:/temp/ticketsDB1");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            mr.deleteAll();
            ror.deleteAll();
            scr.deleteAll();
        };

    }

    @Autowired
    MovieEntityRepository mr;
    @Autowired
    RoomEntityRepository ror;
    @Autowired
    ScreeningRepository scr;
}
