package com.g28.model;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class PositionTest {
    @Test
    public void testGetX() {
        Random r = new Random();
        int x = r.nextInt(9) + 1;
        int y = r.nextInt(4) + 1;
        Position position = new Position(x, y);

        assertEquals(x, position.getX());
    }

    @Test
    public void testGetY() {
        Random r = new Random();
        int x = r.nextInt(9) + 1;
        int y = r.nextInt(4) + 1;
        Position position = new Position(x, y);

        assertEquals(y, position.getY());
    }

    @Test
    public void testEqualsDifferentObjects() {
        Random r = new Random();
        int x = r.nextInt(9) + 1;
        int y = r.nextInt(4) + 1;
        Position a = new Position(x, y);
        Position b = new Position(x, y);

        assertEquals(a, b);
    }

    @Test
    public void testEqualsSameObject() {
        Random r = new Random();
        int x = r.nextInt(9) + 1;
        int y = r.nextInt(4) + 1;
        Position a = new Position(x, y);

        assertEquals(a, a);
    }

    @Test
    public void testEqualsNullObject() {
        Random r = new Random();
        int x = r.nextInt(9) + 1;
        int y = r.nextInt(4) + 1;
        Position a = new Position(x, y);

        assertNotEquals(a, null);
    }
}