package com.epam.training.ticketservice.movie.repository;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEntityRepository extends JpaRepository<MovieEntity, Long> {
    Boolean existsByTitle(String title);
}