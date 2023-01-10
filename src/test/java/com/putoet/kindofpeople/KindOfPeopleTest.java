package com.putoet.kindofpeople;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class KindOfPeopleTest {
    @Test
    void get1() {
        final Input input = InputFactory.get(in("/kindofpeople-1.txt"));
        assertEquals(1, input.rows);
        assertEquals(4, input.columns);
        assertEquals(2, input.queries.length);
        assertArrayEquals(new int[]{1, 1, 0, 0}, input.grid);
    }

    @Test
    void searchOne() {
        final Input input = InputFactory.get(in("/kindofpeople-1.txt"));

        assertFalse(Search.binary(input, 0));
        assertFalse(Search.decimal(input, 0));
        assertFalse(Search.binary(input, 1));
        assertTrue(Search.decimal(input, 1));
    }

    @Test
    void searchTwo() {
        final Input input = InputFactory.get(in("/kindofpeople-2.txt"));

        assertTrue(Search.binary(input, 0));
        assertFalse(Search.decimal(input, 0));
        assertFalse(Search.binary(input, 1));
        assertTrue(Search.decimal(input, 1));
        assertFalse(Search.binary(input, 2));
        assertFalse(Search.decimal(input, 2));
    }

    InputStream in(String resourceName) {
        return this.getClass().getResourceAsStream(resourceName);
    }
}