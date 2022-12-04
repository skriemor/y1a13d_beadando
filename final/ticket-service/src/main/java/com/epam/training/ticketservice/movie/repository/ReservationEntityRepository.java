package com.epam.training.ticketservice.movie.repository;

import com.epam.training.ticketservice.movie.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationEntityRepository extends JpaRepository<ReservationEntity, Long> {
}