package com.epam.training.ticketservice.movie.repository;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieEntityRepository extends JpaRepository<MovieEntity, String> {

}