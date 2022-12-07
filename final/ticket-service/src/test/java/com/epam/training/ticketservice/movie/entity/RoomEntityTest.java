package com.epam.training.ticketservice.movie.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class RoomEntityTest {

    @Test
    void testEquals() {

        ArrayList<RoomEntity> list = new ArrayList<>(Arrays.asList(
                new RoomEntity("newMovie", 3, 5),
                new RoomEntity("diffMovie", 12, 2),
                new RoomEntity("newMovie", 12, 2),
                new RoomEntity("newMovie", 5, 55),
                new RoomEntity("diffMovie", 5, 5),
                new RoomEntity("diffMovie", 10, 20),
                new RoomEntity("newMovie", 10, 20),
                new RoomEntity("diffMovie", 10, 10)
        ));

        RoomEntity room_nine = new RoomEntity();
        room_nine.setName("five");
        room_nine.setRowCount(3);
        room_nine.setColCount(55);
        var allargs = new RoomEntity("one",15,150,null);

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
        int exp = room_nine.hashCode();
        int got = room_nine.hashCode();

        Assertions.assertEquals(exp, got);

    }
}