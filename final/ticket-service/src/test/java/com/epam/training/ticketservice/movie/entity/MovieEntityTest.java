package com.epam.training.ticketservice.movie.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieEntityTest {

    @Test
    void testEquals() {
        ArrayList<MovieEntity> list = new ArrayList<>(Arrays.asList(
                new MovieEntity("newMovie", "newCategory", 150),
        new MovieEntity("diffMovie", "newCategory", 150),
        new MovieEntity("newMovie", "diffCategory", 150),
        new MovieEntity("newMovie", "newCategory", 100),
        new MovieEntity("diffMovie", "diffCategory", 100),
        new MovieEntity("diffMovie", "newCategory", 55),
        new MovieEntity("newMovie", "diffCategory", 55),
        new MovieEntity("diffMovie", "diffCategory", 2100)
        ));

        MovieEntity movie_nineth = new MovieEntity();
        movie_nineth.setTitle("five");
        movie_nineth.setLength(150);
        movie_nineth.setCategory("five");
        movie_nineth.setReservationList(null);
        var allargs = new MovieEntity("one","two",150,null);


        Assertions.assertAll(() -> {
            for (int it = 0; it < list.size(); ++it) {
                for (int ti = 0; ti < list.size(); ++ti) {
                    if (it != ti) {
                        Assertions.assertNotEquals(list.get(it), list.get(ti));
                    } else {
                        Assertions.assertEquals(list.get(it), list.get(ti));
                    }
                }
            }


        });
        int exp = movie_nineth.hashCode();
        int got = movie_nineth.hashCode();

        Assertions.assertEquals(exp, got);

        MovieEntity one = null;
        MovieEntity two = new MovieEntity("ee","ee",15);
        MovieEntity three = new MovieEntity("ee","ee",15);
        MovieEntity four = three;
        Assertions.assertNotEquals(one, two);
        Assertions.assertEquals(two, three);
        Assertions.assertEquals(three, four);

    }
}