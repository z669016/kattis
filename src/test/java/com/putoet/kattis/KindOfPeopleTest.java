package com.putoet.kattis;

import com.putoet.kattis.ColorSearch;
import com.putoet.kattis.Input;
import com.putoet.kattis.InputFactory;
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
        assertArrayEquals(new char[][]{{'1', '1', '0', '0'}}, input.grid);
    }

    @Test
    void colorSearchOne() {
        final Input input = InputFactory.get(in("/kindofpeople-1.txt"));
        final ColorSearch search = new ColorSearch(input);

        assertFalse(search.binary(input, input.queries[0]));
        assertFalse(search.decimal(input, input.queries[0]));
        assertFalse(search.binary(input, input.queries[1]));
        assertTrue(search.decimal(input, input.queries[1]));
    }

    @Test
    void colorSearchTwo() {
        final Input input = InputFactory.get(in("/kindofpeople-2.txt"));
        final ColorSearch search = new ColorSearch(input);

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