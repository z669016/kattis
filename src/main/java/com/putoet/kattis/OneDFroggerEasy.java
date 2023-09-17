package com.putoet.kattis;

import java.io.InputStream;
import java.util.Objects;

public class OneDFroggerEasy {
    public static void main(String[] args) {
        final OneDFroggerEasy.Input input = OneDFroggerEasy.InputFactory.get(System.in);

        final Fate fate = play(input.s, input.m, input.squares);
        System.out.println(fate.fate);
        System.out.println(fate.hops);
    }

    public static Fate play(int s, int m, int[] squares) {
        final boolean[] visited = new boolean[squares.length];
        int hops = 0;
        s -= 1;

        while (true) {
            if (s < 0)
                return new Fate("left", hops);

            if (s >= squares.length)
                return new Fate("right", hops);

            if (squares[s] == m)
                return new Fate("magic", hops);

            if (visited[s])
                return new Fate("cycle", hops);

            final int k = squares[s];
            visited[s] = true;
            if (k > 0) {
                s += k;
            } else {
                s -= Math.abs(k);
            }

            hops++;
        }
    }

    static class Input {
        public final int s, m;
        public final int[] squares;

        Input(int s, int m, int[] squares) {
            this.s = s;
            this.m = m;
            this.squares = squares;
        }
    }

    static class InputFactory {
        public static Input get(InputStream in) {
            final FastReader scanner = new FastReader(in);
            final int n = scanner.nextInt();
            final int s = scanner.nextInt();
            final int m = scanner.nextInt();
            final int[] squares = new int[n];
            for (int i = 0; i < n; i++)
                squares[i] = scanner.nextInt();

            return new Input(s, m, squares);
        }
    }

    static class Fate {
        public final String fate;
        public final int hops;

        Fate(String fate, int hops) {
            this.fate = fate;
            this.hops = hops;
        }

        @Override
        public String toString() {
            return "Fate{" +
                   "fate='" + fate + '\'' +
                   ", hops=" + hops +
                   '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Fate)) return false;
            Fate fate1 = (Fate) o;
            return hops == fate1.hops && fate.equals(fate1.fate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fate, hops);
        }
    }
}

