package com.g28.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WallTest {
    @Test
    public void testConstructor() {
        Wall wall = new Wall(0, 0);

        assertEquals(0, wall.getPosition().getX());
        assertEquals(0, wall.getPosition().getY());
    }
}