package com.epam.training.ticketservice.movie.repository;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.entity.ReservationEntity;
import com.epam.training.ticketservice.movie.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface ScreeningRepository extends JpaRepository<ReservationEntity, String> {
    Boolean existsByMovieAndRoomAndDate(String movie, String room, LocalDateTime date);

    ReservationEntity findByMovieAndRoomAndDate(String movie, String room, LocalDateTime date);

    void deleteByMovieAndRoomAndDate(String movie, String room, LocalDateTime date);
}