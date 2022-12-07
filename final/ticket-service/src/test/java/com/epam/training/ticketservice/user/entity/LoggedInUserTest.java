package com.epam.training.ticketservice.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggedInUserTest {

    @Test
    void testEquals() {
        LoggedInUser one = new LoggedInUser("One", "One");
        LoggedInUser two = new LoggedInUser("One", "One");
        LoggedInUser three = new LoggedInUser("Two", "Two");
        LoggedInUser four = three;
        Assertions.assertFalse(one.getName().equals(three.getName()));
        Assertions.assertNotEquals(two.getUserType(), three.getUserType());
        Assertions.assertEquals(four.hashCode(), three.hashCode());
        LoggedInUser room_nine = new LoggedInUser();
        room_nine.setUserType("diffRoom");
        room_nine.setUserType("diffMovie");
        LoggedInUser room_ten = new LoggedInUser();


        int exp = room_nine.hashCode();
        int got = room_nine.hashCode();

        int got2 = room_ten.hashCode();

        Assertions.assertEquals(exp, got);
        Assertions.assertNotEquals(exp, got2);
    }

    @Test
    void testEquals1() {
        LoggedInUser one = new LoggedInUser ("One", "One");
        LoggedInUser two = new LoggedInUser ("One", "One");
        LoggedInUser three = new LoggedInUser("Two", "Two");

        Assertions.assertTrue(one.equals(two));
        Assertions.assertFalse(one.equals(three));
    }
    @Test
    void testEquals2() {
        LoggedInUser one = null;
        LoggedInUser two = new LoggedInUser ("One", "One");
        LoggedInUser three = two;
        UserEntity four = new UserEntity("One", "One");
        Assertions.assertNotEquals(two, four);
        Assertions.assertEquals(two, three);
        Assertions.assertNotEquals(one, three);
    }

}