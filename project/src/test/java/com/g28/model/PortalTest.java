package com.g28.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortalTest {
    @Test
    public void testConstructor() {
        Portal portal = new Portal(0, 9);

        assertEquals(0, portal.getPosition().getX());
        assertEquals(9, portal.getPosition().getY());
    }

    @Test
    public void testWarp() {
        Arena arena = new Arena(80, 51);

        Portal portal = new Portal(0, 9);
        Player player = new Player(0, 9);

        portal.warp(player, arena.getLevel());
        assertEquals(28, player.getPosition().getX());
        assertEquals(9, player.getPosition().getY());

        portal.warp(player, arena.getLevel());
        assertEquals(0, player.getPosition().getX());
        assertEquals(9, player.getPosition().getY());
    }
}
