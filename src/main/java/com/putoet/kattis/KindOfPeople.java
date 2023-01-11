package com.putoet.kattis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class KindOfPeople {
    public static void main(String[] args) {
        final Input input = InputFactory.get(System.in);
        final ColorSearch search = new ColorSearch(input);

        for (Query query : input.queries) {
            if (search.binary(input, query))
                System.out.println("binary");
            else if (search.decimal(input, query))
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

class ColorSearch {
    private final int[][] colors;

    public ColorSearch(Input input) {
        this.colors = ColorSearch.colorGrid(input);
    }

    private static int[][] colorGrid(Input input) {
        final int[][] colors = new int[input.rows][input.columns];
        final int UNCOLORED = 0;
        int color = 1;

        for (int y = 0; y < input.rows; y++) {
            for (int x = 0; x < input.columns; x++) {
                if (input.grid[y][x] == '0' || input.grid[y][x] == '1') {
                    final char people = input.grid[y][x];

                    final Deque<Point> queue = new ArrayDeque<>();
                    queue.offer(new Point(y, x));
                    while (!queue.isEmpty()) {
                        final Point current = queue.poll();
                        if (colors[current.row][current.column] == UNCOLORED &&
                            input.grid[current.row][current.column] == people) {

                            colors[current.row][current.column] = color;

                            if (current.row > 0)
                                queue.offer(new Point(current.row - 1, current.column));
                            if (current.row < input.rows - 1)
                                queue.offer(new Point(current.row + 1, current.column));
                            if (current.column > 0)
                                queue.offer(new Point(current.row, current.column - 1));
                            if (current.column < input.columns - 1)
                                queue.offer(new Point(current.row, current.column + 1));
                        }
                    }
                    color++;
                }
            }
        }

        return colors;
    }

    public boolean binary(Input input, Query query) {
        return canNavigate(input, query, '0');
    }


    public boolean decimal(Input input, Query query) {
        return canNavigate(input, query, '1');
    }

    private boolean canNavigate(Input input, Query query, char people) {
        final Point from = query.from;
        final Point to = query.to;

        if (input.grid[from.row][from.column] != input.grid[to.row][to.column] ||
            input.grid[to.row][to.column] != people)
            return false;

        return colors[from.row][from.column] == colors[to.row][to.column];
    }
}
