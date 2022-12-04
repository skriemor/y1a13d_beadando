package com.epam.training.ticketservice.movie.id;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationID implements Serializable {
    private String movie;
    private String room;
    private LocalDateTime date;
}
