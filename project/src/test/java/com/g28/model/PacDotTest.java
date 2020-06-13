package com.g28.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PacDotTest {
    @Test
    public void testConstructor() {
        PacDot pacDot = new PacDot(10, 15);

        assertEquals(10, pacDot.getPosition().getX());
        assertEquals(15, pacDot.getPosition().getY());
    }
}