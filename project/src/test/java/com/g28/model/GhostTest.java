package com.g28.model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GhostTest {
    @Test
    public void testConstructor() {
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseAgressive(), new FrightenedWandering());

        assertEquals(15, ghost.getPosition().getX());
        assertEquals(15, ghost.getPosition().getY());
        assertEquals("#d5212e", ghost.getGhostColor());
    }

    @Test
    public void testChaseModeAgressive() {
        Player player = new Player(15, 0);
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseAgressive(), new FrightenedWandering());

        List<Element> elements = new ArrayList<>();
        elements.add(ghost);

        ghost.enterChaseMode(player, elements);

        assertEquals("#d5212e", ghost.getGhostColor());
        assertNotEquals(new Position(0, 15), ghost.getPosition());
    }

    @Test
    public void testChaseModePredict() {
        Player player = new Player(15, 0);
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChasePredict(), new FrightenedWandering());

        List<Element> elements = new ArrayList<>();
        elements.add(ghost);

        ghost.enterChaseMode(player, elements);

        assertEquals("#d5212e", ghost.getGhostColor());
        assertNotEquals(new Position(0, 15), ghost.getPosition());
    }

    @Test
    public void testChaseModeRandom() {
        Player player = new Player(15, 0);
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseRandom(), new FrightenedWandering());

        List<Element> elements = new ArrayList<>();
        elements.add(ghost);

        ghost.enterChaseMode(player, elements);

        assertEquals("#d5212e", ghost.getGhostColor());
        assertNotEquals(new Position(0, 15), ghost.getPosition());
    }

    @Test
    public void testChaseModeSentinel() {
        Player player = new Player(15, 0);
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseSentinel(), new FrightenedWandering());

        List<Element> elements = new ArrayList<>();
        elements.add(ghost);

        ghost.enterChaseMode(player, elements);

        assertEquals("#d5212e", ghost.getGhostColor());
        assertNotEquals(new Position(0, 15), ghost.getPosition());
    }

    @Test
    public void testFrightenedMode() {
        Player player = new Player(15, 0);
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseAgressive(), new FrightenedWandering());

        List<Element> elements = new ArrayList<>();
        elements.add(ghost);

        ghost.enterFrightenedMode(player, elements);

        assertEquals("#1c2e4a", ghost.getGhostColor());
        assertNotEquals(new Position(0, 15), ghost.getPosition());
    }

    @Test
    public void calculateAvailableMoves() {

    }

    @Test
    public void testReturnToInitialPosition() {
        Ghost ghost = new Ghost(15, 15, "#d5212e", new ChaseAgressive(), new FrightenedWandering());
        ghost.setPosition(new Position(0, 0));

        assertEquals(0, ghost.getPosition().getX());
        assertEquals(0, ghost.getPosition().getX());

        ghost.returnToInitialPosition();

        assertEquals(15, ghost.getPosition().getX());
        assertEquals(15, ghost.getPosition().getX());
    }
}