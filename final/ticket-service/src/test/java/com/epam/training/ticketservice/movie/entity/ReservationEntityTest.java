package com.epam.training.ticketservice.movie.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ReservationEntityTest {

    @Test
    void testEquals() {
        LocalDateTime temp = LocalDateTime.now().minusMinutes(15);
        LocalDateTime tempNow = LocalDateTime.now();
        ReservationEntity one = new ReservationEntity("One", "One", LocalDateTime.now());
        ReservationEntity two = new ReservationEntity("One", "One", LocalDateTime.now());
        ReservationEntity three = new ReservationEntity("Two", "Two", LocalDateTime.now().minusMinutes(5));
        ReservationEntity four = three;
        Assertions.assertFalse(one.getDate().isEqual(three.getDate()));
        Assertions.assertNotEquals(two.getMovie(), three.getMovie());
        Assertions.assertEquals(four.hashCode(), three.hashCode());
        ReservationEntity room_nine = new ReservationEntity();
        room_nine.setRoom("diffRoom");
        room_nine.setMovie("diffMovie");
        room_nine.setDate(LocalDateTime.now());
        ReservationEntity room_ten = new ReservationEntity();


        int exp = room_nine.hashCode();
        int got = room_nine.hashCode();

        int got2 = room_ten.hashCode();

        Assertions.assertEquals(exp, got);
        Assertions.assertNotEquals(exp, got2);
    }

    @Test
    void testEquals1() {
        ReservationEntity one = new ReservationEntity("One", "One", LocalDateTime.now());
        ReservationEntity two = new ReservationEntity("One", "One", LocalDateTime.now());
        ReservationEntity three = new ReservationEntity("Two", "Two", LocalDateTime.now().minusMinutes(5));

        Assertions.assertFalse(one.equals(two));
        Assertions.assertFalse(one.equals(three));

        ReservationEntity five = null;
        ReservationEntity six = new ReservationEntity("ee","ee",LocalDateTime.now());
        ReservationEntity seven = new ReservationEntity("ee","ee",LocalDateTime.now());
        ReservationEntity eight = seven;
        Assertions.assertNotEquals(five, six);
        Assertions.assertEquals(six, seven);
        Assertions.assertEquals(seven, eight);
    }
    @Test
    void testEquals2() {
        ReservationEntity one = new ReservationEntity("One", "One", LocalDateTime.now());
        ReservationEntity two = new ReservationEntity("One", "Two", LocalDateTime.now());
    }
}