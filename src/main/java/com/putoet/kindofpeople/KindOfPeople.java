package com.putoet.kindofpeople;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class KindOfPeople {
    public static void main(String[] args) {
        final Input input = InputFactory.get(System.in);

        for (Query query : input.queries) {
            if (Search.binary(input, query))
                System.out.println("binary");
            else if (Search.decimal(input, query))
                System.out.println("decimal");
            else
                System.out.println("neither");
        }
    }
}


class Point {
    public final int row;
    public final int column;

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    Point(int row, int column) {

        this.row = row;
        this.column = column;
    }

    int manhattanDistance(Point to) {
        return Math.abs(row - to.row) + Math.abs(column - to.column);
    }
}

class Query {
    public final Point from;
    public final Point to;

    @Override
    public String toString() {
        return "Query{" +
               "from=" + from +
               ", to=" + to +
               '}';
    }

    Query(Point from, Point to) {
        this.from = from;
        this.to = to;
    }

    Query reverse() {
        return new Query(to, from);
    }
}

class Input {
    public final char[][] grid;
    public final int rows;
    public final int columns;
    public final Query[] queries;

    Input(char[][] grid, int rows, int columns, Query[] queries) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.queries = queries;
    }
}

class FastReader {
    final BufferedReader br;
    StringTokenizer st;

    public FastReader(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

class InputFactory {
    static Input get(InputStream in) {
        final FastReader scanner = new FastReader(in);
        final int rows = scanner.nextInt();
        final int columns = scanner.nextInt();
        final char[][] grid = new char[rows][];

        for (int r = 0; r < rows; r++) {
            final String line = scanner.next();
            for (int c = 0; c < columns; c++) {
                grid[r] = line.toCharArray();
            }
        }

        final int queryCount = scanner.nextInt();
        final Query[] queries = new Query[queryCount];
        for (int i = 0; i < queryCount; i++) {
            final int fromRow = scanner.nextInt();
            final int fromColumn = scanner.nextInt();
            final int toRow = scanner.nextInt();
            final int toColumn = scanner.nextInt();
            queries[i] = new Query(new Point(fromRow - 1, fromColumn - 1), new Point(toRow - 1, toColumn - 1));
        }

        return new Input(grid, rows, columns, queries);
    }
}

class Search {
    static boolean binary(Input input, Query query) {
        if (input.grid[query.from.row][query.from.column] != input.grid[query.to.row][query.to.column] ||
            input.grid[query.to.row][query.to.column] != '0') {
            return false;
        }

        return Stream.of(query, query.reverse())
                .parallel()
                .map(q -> canNavigate(input, q, '0'))
                .findFirst()
                .orElseThrow();
    }

    static boolean decimal(Input input, Query query) {
        if (input.grid[query.from.row][query.from.column] != input.grid[query.to.row][query.to.column] ||
            input.grid[query.to.row][query.to.column] != '1') {
            return false;
        }

        return Stream.of(query, query.reverse())
                .parallel()
                .map(q -> canNavigate(input, q, '1'))
                .findFirst()
                .orElseThrow();
    }

    static boolean canNavigate(Input input, Query query, char people) {
        final char[][] grid = input.grid;
        final Point init = query.from;
        final Point to = query.to;
        final int rows = input.rows;
        final int columns = input.columns;

        final boolean[][] visited = new boolean[rows][columns];
        visited[init.row][init.column] = true;

        final Queue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> p.manhattanDistance(p)));
        queue.offer(init);

        while (!queue.isEmpty()) {
            final Point current = queue.poll();
            if (current.row == to.row && current.column == to.column)
                return true;

            if (current.row > 0) {
                final Point n = new Point(current.row - 1, current.column);
                if (n.row == to.row && n.column == to.column)
                    return true;

                if (grid[n.row][n.column] == people && !visited[n.row][n.column]) {
                    visited[n.row][n.column] = true;
                    queue.offer(n);
                }
            }

            if (current.row < rows - 1) {
                final Point n = new Point(current.row + 1, current.column);
                if (n.row == to.row && n.column == to.column)
                    return true;

                if (grid[n.row][n.column] == people && !visited[n.row][n.column]) {
                    visited[n.row][n.column] = true;
                    queue.offer(n);
                }
            }

            if (current.column > 0) {
                final Point n = new Point(current.row, current.column - 1);
                if (n.row == to.row && n.column == to.column)
                    return true;

                if (grid[n.row][n.column] == people && !visited[n.row][n.column]) {
                    visited[n.row][n.column] = true;
                    queue.offer(n);
                }
            }

            if (current.column < columns - 1) {
                final Point n = new Point(current.row, current.column + 1);
                if (n.row == to.row && n.column == to.column)
                    return true;

                if (grid[n.row][n.column] == people && !visited[n.row][n.column]) {
                    visited[n.row][n.column] = true;
                    queue.offer(n);
                }
            }
        }

        return false;
    }
}
