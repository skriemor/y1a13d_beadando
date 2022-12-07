package com.epam.training.ticketservice.shell;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.entity.ReservationEntity;
import com.epam.training.ticketservice.movie.entity.RoomEntity;
import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import com.epam.training.ticketservice.movie.repository.RoomEntityRepository;
import com.epam.training.ticketservice.movie.repository.ScreeningRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ScreeningCommandLineParserTest {
    @Mock
    ScreeningRepository screeningRepository;
    @Mock
    MovieEntityRepository movieEntityRepository;
    @Mock
    RoomEntityRepository roomEntityRepository;
    @InjectMocks
    ScreeningCommandLineParser parser;

    @Test
    void canAddScreening() {
        ReservationEntity res1 = new ReservationEntity("one", "two", LocalDateTime.now());
        ReservationEntity res2 = new ReservationEntity("one", "two", LocalDateTime.now().plusMinutes(30));
        ReservationEntity res3 = new ReservationEntity("one", "two", LocalDateTime.now().plusMinutes(45));
        ReservationEntity res4 = new ReservationEntity("one", "two", LocalDateTime.now().plusMinutes(46));

        Mockito.when(this.movieEntityRepository.findById(any())).thenReturn(Optional.of(
                new MovieEntity("one", "one", 15)
        ));

        Mockito.when(this.screeningRepository.findAll()).thenReturn(List.of(res1));

        var got = parser.canAddScreening(res2);
        Assertions.assertEquals("can", got);
        Mockito.when(this.screeningRepository.findAll()).thenReturn(List.of(res1, res2));
        got = parser.canAddScreening(res3);
        var expected = "This would start in the break period after another screening in this room";
        Assertions.assertEquals(expected, got);


        expected = "There is an overlapping screening";
        Mockito.when(this.screeningRepository.findAll()).thenReturn(List.of(res1, res3));
        got = parser.canAddScreening(res4);
        Assertions.assertEquals(expected, got);
    }
}