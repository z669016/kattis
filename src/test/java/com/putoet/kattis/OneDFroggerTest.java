package com.putoet.kattis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneDFroggerTest {

    @Test
    void playOne() {
        final OneDFrogger.Input input = OneDFrogger.InputFactory.get(IS.in("/OneDFrogger-1.txt"));
        assertEquals(new OneDFrogger.Fate("magic", 2), OneDFrogger.play(input.s, input.m, input.squares));
    }

    @Test
    void playTwo() {
        final OneDFrogger.Input input = OneDFrogger.InputFactory.get(IS.in("/OneDFrogger-2.txt"));
        assertEquals(new OneDFrogger.Fate("cycle", 4), OneDFrogger.play(input.s, input.m, input.squares));
    }

    @Test
    void playCycle() {
        final OneDFrogger.Input input = new OneDFrogger.Input(4, 42, new int[] {-9, 2, 42, -2, -3, -3});
        assertEquals(new OneDFrogger.Fate("cycle", 2), OneDFrogger.play(input.s, input.m, input.squares));
    }

    @Test
    void playLeft() {
        final OneDFrogger.Input input = new OneDFrogger.Input(4, 42, new int[] {-9, 2, 42, -4, -3, -3});
        assertEquals(new OneDFrogger.Fate("left", 1), OneDFrogger.play(input.s, input.m, input.squares));
    }

    @Test
    void playRight() {
        final OneDFrogger.Input input = new OneDFrogger.Input(4, 42, new int[] {-9, 2, 42, 3, -3, -3});
        assertEquals(new OneDFrogger.Fate("right", 1), OneDFrogger.play(input.s, input.m, input.squares));
    }
}