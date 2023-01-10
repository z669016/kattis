package com.putoet.kindofpeople;

import java.io.InputStream;
import java.util.*;

public class KindOfPeople {
    public static void main(String[] args) {
        final Input input = InputFactory.get(System.in);

        for (int queryId = 0; queryId < input.queries.length; queryId++) {
            if (Search.binary(input, queryId))
                System.out.println("binary");
            else if (Search.decimal(input, queryId))
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
    public final int[] grid;
    public final int rows;
    public final int columns;
    public final Query[] queries;

    Input(int[] grid, int rows, int columns, Query[] queries) {
        this.grid = grid;
        this.rows = rows;
        this.columns = columns;
        this.queries = queries;
    }
}

class InputFactory {
    static Input get(InputStream in) {
        final Scanner scanner = new Scanner(in);
        final int rows = scanner.nextInt();
        final int columns = scanner.nextInt();
        final int[] grid = new int[rows * columns];

        for (int r = 0; r < rows; r++) {
            final String line = scanner.next();
            for (int c = 0; c < columns; c++) {
                grid[r * columns + c] = line.charAt(c) - '0';
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
    static boolean binary(Input input, int queryId) {
        return canNavigate(input, queryId, 0);
    }

    static boolean decimal(Input input, int queryId) {
        return canNavigate(input, queryId, 1);
    }

    static boolean canNavigate(Input input, int queryId, int people) {
        final int[] grid = input.grid;
        final Query query = input.queries[queryId];
        final Point init = query.from;
        final Point to = query.to;
        final int rows = input.rows;
        final int columns = input.columns;

        if (grid[init.row * columns + init.column] != grid[to.row * columns + to.column] ||
            grid[to.row * columns + to.column] != people) {
            return false;
        }

        final boolean[] visited = new boolean[grid.length];
        visited[init.row * columns + init.column] = true;
        final Queue<Point> queue = new PriorityQueue<>(Comparator.comparing(p -> manhattanDistance(p, to)));
        queue.offer(init);
        while (!queue.isEmpty()) {
            final Point current = queue.poll();
            if (current.row == to.row && current.column == to.column)
                return true;

            if (current.row > 0) {
                final Point n = new Point(current.row - 1, current.column);
                final int index = n.row * columns + n.column;
                if (grid[index] == people && !visited[index]) {
                    visited[index] = true;
                    queue.offer(n);
                }
            }

            if (current.row < rows - 1) {
                final Point n = new Point(current.row + 1, current.column);
                final int index = n.row * columns + n.column;
                if (grid[index] == people && !visited[index]) {
                    visited[index] = true;
                    queue.offer(n);
                }
            }

            if (current.column > 0) {
                final Point n = new Point(current.row, current.column - 1);
                final int index = n.row * columns + n.column;
                if (grid[index] == people && !visited[index]) {
                    visited[index] = true;
                    queue.offer(n);
                }
            }

            if (current.column < columns - 1) {
                final Point n = new Point(current.row, current.column + 1);
                final int index = n.row * columns + n.column;
                if (grid[index] == people && !visited[index]) {
                    visited[index] = true;
                    queue.offer(n);
                }
            }
        }

        return false;
    }

    static int manhattanDistance(Point from, Point to) {
        return Math.abs(from.row - to.row) + Math.abs(from.column - to.column);
    }
}
