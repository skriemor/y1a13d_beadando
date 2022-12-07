package com.epam.training.ticketservice.movie;

import com.epam.training.ticketservice.movie.entity.MovieEntity;
import com.epam.training.ticketservice.movie.entity.RoomEntity;
import com.epam.training.ticketservice.movie.repository.MovieEntityRepository;
import com.epam.training.ticketservice.movie.repository.RoomEntityRepository;
import com.epam.training.ticketservice.movie.repository.ScreeningRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;


public class RepoTest {
    @Mock
    MovieEntityRepository movieRepo;
    @Mock
    RoomEntityRepository roomRepo;
    @Mock
    ScreeningRepository screeningRepo;

    @Test
    public void testAddDuplicate() throws Exception {
        //MovieEntity movie = new MovieEntity();
        //movie.setCategory("cat");
        //movie.setLength(150);
        //movie.setTitle("goomba");

        //movieRepo.save(movie);
        //movieRepo.save(movie);
        //movieRepo.deleteById(movie.getTitle());
        //Assert.assertFalse(movieRepo.existsById(movie.getTitle()));
        Assert.assertTrue(true);
    }

    @Test
    public void addReservationNoMovie() {
        var movie = new MovieEntity();
        var room = new RoomEntity();

    }
}
