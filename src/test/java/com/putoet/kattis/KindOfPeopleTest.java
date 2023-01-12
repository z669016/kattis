package com.putoet.kattis;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class KindOfPeopleTest {
    @Test
    void get1() {
        final KindOfPeople.Input input = KindOfPeople.InputFactory.get(in("/kindofpeople-1.txt"));
        assertEquals(1, input.rows);
        assertEquals(4, input.columns);
        assertEquals(2, input.queries.length);
        assertArrayEquals(new char[][]{{'1', '1', '0', '0'}}, input.grid);
    }

    @Test
    void colorSearchOne() {
        final KindOfPeople.Input input = KindOfPeople.InputFactory.get(in("/kindofpeople-1.txt"));
        final KindOfPeople.ColorSearch search = new KindOfPeople.ColorSearch(input);

        assertFalse(search.binary(input, input.queries[0]));
        assertFalse(search.decimal(input, input.queries[0]));
        assertFalse(search.binary(input, input.queries[1]));
        assertTrue(search.decimal(input, input.queries[1]));
    }

    @Test
    void colorSearchTwo() {
        final KindOfPeople.Input input = KindOfPeople.InputFactory.get(in("/kindofpeople-2.txt"));
        final KindOfPeople.ColorSearch search = new KindOfPeople.ColorSearch(input);

        assertTrue(search.binary(input, input.queries[0]));
        assertFalse(search.decimal(input, input.queries[0]));
        assertFalse(search.binary(input, input.queries[1]));
        assertTrue(search.decimal(input, input.queries[1]));
        assertFalse(search.binary(input, input.queries[2]));
        assertFalse(search.decimal(input, input.queries[2]));
    }

    InputStream in(String resourceName) {
        return this.getClass().getResourceAsStream(resourceName);
    }
}