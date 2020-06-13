package com.g28.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PowerUpTest {
    @Test
    public void testConstructor() {
        PowerUp powerUp = new PowerUp(10, 15);

        assertEquals(10, powerUp.getPosition().getX());
        assertEquals(15, powerUp.getPosition().getY());
    }

    @Test
    public void testActivation() {
        List<PowerUp> powerUps = new ArrayList<>();

        PowerUp powerUp = new PowerUp(10, 15);
        Player player = new Player();

        powerUps.add(powerUp);

        assertEquals(false, player.getPoweredUp());

        powerUp.activate(player, powerUps);
        assertEquals(true, player.getPoweredUp());


        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        assertEquals(false, player.getPoweredUp());
                    }
                },
                5000
        );
    }


}