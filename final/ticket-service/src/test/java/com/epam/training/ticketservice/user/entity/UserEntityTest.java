package com.epam.training.ticketservice.user.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserEntityTest {

    @Test
    void testEquals() {
        UserEntity one = new UserEntity("One", "One");
        UserEntity two = new UserEntity("One", "One");
        UserEntity three = new UserEntity("Two", "Two");
        UserEntity four = three;
        Assertions.assertFalse(one.getUserName().equals(three.getUserName()));
        Assertions.assertNotEquals(two.getPassword(), three.getPassword());
        Assertions.assertEquals(four.hashCode(), three.hashCode());
        UserEntity room_nine = new UserEntity();
        room_nine.setUserName("diffRoom");
        room_nine.setPassword("diffMovie");
        UserEntity room_ten = new UserEntity();


        int exp = room_nine.hashCode();
        int got = room_nine.hashCode();

        int got2 = room_ten.hashCode();

        Assertions.assertEquals(exp, got);
        Assertions.assertNotEquals(exp, got2);
    }

    @Test
    void testEquals1() {
        UserEntity one = new UserEntity ("One", "One");
        UserEntity two = new UserEntity ("One", "One");
        UserEntity three = new UserEntity("Two", "Two");

        Assertions.assertTrue(one.equals(two));
        Assertions.assertFalse(one.equals(three));
    }

    @Test
    void testEquals2() {
        UserEntity one = null;
        UserEntity two = new UserEntity ("One", "One");
        UserEntity three = two;
        LoggedInUser four = new LoggedInUser("One", "One");
        Assertions.assertNotEquals(two, four);
        Assertions.assertEquals(two, three);
        Assertions.assertNotEquals(one, three);

    }
}