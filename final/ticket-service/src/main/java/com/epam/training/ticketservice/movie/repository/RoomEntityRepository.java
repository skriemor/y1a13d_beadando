package com.epam.training.ticketservice.movie.repository;

import com.epam.training.ticketservice.movie.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomEntityRepository extends JpaRepository<RoomEntity, String> {
}