package com.putoet.kattis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OneDFroggerEasyTest {

    @Test
    void playOne() {
        final OneDFroggerEasy.Input input = OneDFroggerEasy.InputFactory.get(IS.in("/onedfrogger-1.txt"));
        assertEquals(new OneDFroggerEasy.Fate("magic", 2), OneDFroggerEasy.play(input.s, input.m, input.squares));
    }

    @Test
    void playTwo() {
        final OneDFroggerEasy.Input input = OneDFroggerEasy.InputFactory.get(IS.in("/onedfrogger-2.txt"));
        assertEquals(new OneDFroggerEasy.Fate("cycle", 4), OneDFroggerEasy.play(input.s, input.m, input.squares));
    }

    @Test
    void playCycle() {
        final OneDFroggerEasy.Input input = new OneDFroggerEasy.Input(4, 42, new int[] {-9, 2, 42, -2, -3, -3});
        assertEquals(new OneDFroggerEasy.Fate("cycle", 2), OneDFroggerEasy.play(input.s, input.m, input.squares));
    }

    @Test
    void playLeft() {
        final OneDFroggerEasy.Input input = new OneDFroggerEasy.Input(4, 42, new int[] {-9, 2, 42, -4, -3, -3});
        assertEquals(new OneDFroggerEasy.Fate("left", 1), OneDFroggerEasy.play(input.s, input.m, input.squares));
    }

    @Test
    void playRight() {
        final OneDFroggerEasy.Input input = new OneDFroggerEasy.Input(4, 42, new int[] {-9, 2, 42, 3, -3, -3});
        assertEquals(new OneDFroggerEasy.Fate("right", 1), OneDFroggerEasy.play(input.s, input.m, input.squares));
    }
}