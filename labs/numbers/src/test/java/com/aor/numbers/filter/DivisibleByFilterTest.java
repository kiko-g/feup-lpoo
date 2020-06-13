package com.aor.numbers.filter;
import org.junit.Test;
import static org.junit.Assert.*;

public class DivisibleByFilterTest {

    @Test
    public void acceptZero() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertTrue(filter.accept(0));
    }

    @Test
    public void acceptNotDivisible() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertFalse(filter.accept(2));
    }

    @Test
    public void acceptDivisible() {
        DivisibleByFilter filter = new DivisibleByFilter(4);
        assertTrue(filter.accept(8));
    }
}