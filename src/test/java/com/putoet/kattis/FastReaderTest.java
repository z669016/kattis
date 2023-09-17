package com.putoet.kattis;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FastReaderTest {
    @Test
    void bigFileTimer() {
        final long start = System.currentTimeMillis();
        final int[] list = new int[200_000];
        final FastReader scanner = new FastReader(IS.in("/FastReader-big.txt"));
        for (int i = 0; i < list.length; i++) {
            list[i] = scanner.nextInt();
        }
        final long end = System.currentTimeMillis();
        System.out.println("Loading the big file took " + (end - start) + " ms");
    }

    @Test
    void generate() {
        final Random rn = new Random();;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("FastReader-big-one.txt"))) {
            writer.write(String.valueOf(200_000));
            writer.write("\n");
            for (int i = 1; i <= 200_000; i++) {
                writer.write(" 1");
//                writer.write(" ");
//                final int value = rn.nextInt(400_000) + 1 - 200_000;
//                writer.write(String.valueOf(value == 0 ? 1 : value));
            }
        } catch (IOException ignored) {}
    }
}