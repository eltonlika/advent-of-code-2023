package aoc.util;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Position2D(int x, int y) implements Comparable<Position2D> {

    public static Position2D of(int x, int y) {
        return new Position2D(x, y);
    }

    public static Stream<Position2D> generate(final int width, final int height) {
        return IntStream.range(0, height)
                .boxed()
                .flatMap(y -> IntStream.range(0, width).mapToObj(x -> new Position2D(x, y)));
    }

    public static Stream<Position2D> generate(final char[][] array2d) {
        return generate(array2d[0].length, array2d.length);
    }

    public static Stream<Position2D> generate(final int[][] array2d) {
        return generate(array2d[0].length, array2d.length);
    }

    public <T> void set(final T[][] array2d, final T value) {
        array2d[y][x] = value;
    }

    public <T> void set(final char[][] array2d, final char value) {
        array2d[y][x] = value;
    }

    public int get(final int[][] array2d) {
        return array2d[y][x];
    }

    public char get(final char[][] array2d) {
        return array2d[y][x];
    }

    public boolean get(final boolean[][] array2d) {
        return array2d[y][x];
    }

    public <T> T get(final T[][] array2d) {
        return array2d[y][x];
    }

    public Position2D up() {
        return new Position2D(x, y - 1);
    }

    public Position2D down() {
        return new Position2D(x, y + 1);
    }

    public Position2D left() {
        return new Position2D(x - 1, y);
    }

    public Position2D right() {
        return new Position2D(x + 1, y);
    }

    public Position2D upLeft() {
        return new Position2D(x - 1, y - 1);
    }

    public Position2D upRight() {
        return new Position2D(x + 1, y - 1);
    }

    public Position2D downLeft() {
        return new Position2D(x - 1, y + 1);
    }

    public Position2D downRight() {
        return new Position2D(x + 1, y + 1);
    }

    public Position2D move(final Direction direction) {
        return switch (direction) {
            case up -> up();
            case down -> down();
            case left -> left();
            case right -> right();
        };
    }

    public boolean inside(final int width, final int height) {
        return this.x() >= 0 && this.x() < width && this.y() >= 0 && this.y() < height;
    }

    public double distance(final Position2D other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2));
    }

    public Stream<Position2D> adjacent(final int width, final int height) {
        return Stream.of(
                        up(), down(), left(), right(), upLeft(), upRight(), downLeft(), downRight())
                .filter(pos -> pos.inside(width, height));
    }

    public Stream<Position2D> adjacent(final char[][] array2d) {
        return adjacent(array2d[0].length, array2d.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position2D that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Position2D o) {
        if (this.y == o.y) {
            return Integer.compare(this.x, o.x);
        }
        return Integer.compare(this.y, o.y);
    }
}
