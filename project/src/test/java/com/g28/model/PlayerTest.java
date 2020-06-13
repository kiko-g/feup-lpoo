package com.g28.model;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testConstructor() {
        Player player = new Player(5, 10);

        assertEquals(5, player.getPosition().getX());
        assertEquals(10, player.getPosition().getY());
    }

    @Test
    public void testGetScore() {
        Player player = new Player();
        player.setScore(2);

        assertEquals(2, player.getScore());
    }

    @Test
    public void testGetXVelocity() {
        Player player = new Player();
        player.setVelocityX(2);

        assertEquals(2, player.getVelocityX());
    }

    @Test
    public void testGetYVelocity() {
        Player player = new Player();
        player.setVelocityY(2);

        assertEquals(2, player.getVelocityY());
    }

    @Test
    public void testGetHearts() {
        Player player = new Player();

        assertEquals(4, player.getHearts());
    }

    @Test
    public void testDecreaseHearts() {
        Player player = new Player();

        player.decreaseHearts();

        assertEquals(3, player.getHearts());
    }

    @Test
    public void testIncrementScore() {
        Player player = new Player();
        player.setScore(8);
        player.incrementScore(2);

        assertEquals(10, player.getScore());
    }

    @Test
    public void testPoweredUp() {
        Player player = new Player();
        player.setPoweredUp(true);

        assertEquals(true, player.getPoweredUp());

        player.setPoweredUp(false);
        assertEquals(false, player.getPoweredUp());
    }

    @Test
    public void testMoveUp() {
        Player player = new Player();
        Position position = player.getPosition();

        assertEquals(position.getY() - 1, player.moveUp().getY());
    }

    @Test
    public void testMoveDown() {
        Player player = new Player();
        Position position = player.getPosition();

        assertEquals(position.getY() + 1, player.moveDown().getY());
    }

    @Test
    public void testMoveLeft() {
        Player player = new Player();
        Position position = player.getPosition();

        assertEquals(position.getX() - 1, player.moveLeft().getX());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player();
        Position position = player.getPosition();

        assertEquals(position.getX() + 1, player.moveRight().getX());
    }

    @Test
    public void testWallCollision() {
        Player player = new Player(0, 0);
        Wall wall = new Wall(0, 0);

        assertEquals(true, player.getPosition().equals(wall.getPosition()));
    }
}