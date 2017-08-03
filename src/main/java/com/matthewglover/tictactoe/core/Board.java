package com.matthewglover.tictactoe.core;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.*;
import static java.util.function.UnaryOperator.identity;

public class Board {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 4;
    public static final int FIRST_SQUARE_NUMBER = 1;

    private final int size;
    private PlayerSymbol[] grid;

    public Board(int size) {
        this.size = size;
        setupGrid();
    }

    public int getSize() {
        return size;
    }

    public int getTotalSquares() {
        return size * size;
    }

    public List<Integer> getEmptySquares() {
        return getRowsOfSquareNumbers()
                .flatMap(identity())
                .filter(squareNumber -> isEmptySquare(squareNumber))
                .collect(Collectors.toList());
    }

    public Stream<Stream<Integer>> getRowsOfSquareNumbers() {
        return getGroupOfLinesOfSquareNumbers(size, 1);
    }

    public PlayerSymbol getSquare(int squareNumber) {
        return grid[squareNumber - 1];
    }

    public void setSquare(int squareNumber, PlayerSymbol playerSymbol) {
        grid[squareNumber - 1] = playerSymbol;
    }

    public boolean isEmptySquare(int squareNumber) {
        return getSquare(squareNumber).isEmpty();
    }

    public boolean isAnyWinningLine(PlayerSymbol playerSymbol) {
        return getLines().anyMatch(line -> isWinningLine(line, playerSymbol));
    }

    public boolean isFull() {
        return stream(grid).allMatch(square -> !square.isEmpty());
    }

    public boolean isValidMove(int move) {
        return isInBound(move) && isEmptySquare(move);
    }

    private void setupGrid() {
        grid = new PlayerSymbol[getTotalSquares()];
        fill(grid, PlayerSymbol.NEITHER);
    }

    private boolean isWinningLine(Stream<PlayerSymbol> line, PlayerSymbol playerSymbol) {
        return line.allMatch(square -> square == playerSymbol);
    }

    private Stream<Stream<PlayerSymbol>> getLines() {
        return Stream.concat(getRows(), Stream.concat(getColumns(), getDiagonals()));
    }

    private Stream<Stream<PlayerSymbol>> getRows() {
        return getRowsOfSquareNumbers()
                .map(line -> line.map(this::getSquare));
    }

    private Stream<Stream<PlayerSymbol>> getColumns() {
        return getGroupOfLinesOfSquareNumbers(1, size)
                .map(line -> line.map(this::getSquare));
    }

    private Stream<Stream<PlayerSymbol>> getDiagonals() {
        return Stream.of(getDiagonalTopLeft(), getDiagonalTopRight());
    }

    private Stream<PlayerSymbol> getDiagonalTopLeft() {
        return getDiagonal(1, size + 1);
    }

    private Stream<PlayerSymbol> getDiagonalTopRight() {
        return getDiagonal(size, size - 1);
    }

    private Stream<PlayerSymbol> getDiagonal(int startSquare, int squareIncrementer) {
        return getLineOfSquareNumbers(squareIncrementer).apply(startSquare).map(this::getSquare);
    }

    private Stream<Stream<Integer>> getGroupOfLinesOfSquareNumbers(int lineIncrementer, int squareIncrementer) {
        return Stream.iterate(1, d -> d + lineIncrementer)
                .limit(size)
                .map(getLineOfSquareNumbers(squareIncrementer));
    }

    private Function<Integer, Stream<Integer>> getLineOfSquareNumbers(int increment) {
        return (Integer firstItem) ->
                Stream.iterate(firstItem, d -> d + increment)
                        .limit(size);
    }

    private boolean isInBound(int move) {
        return move >= FIRST_SQUARE_NUMBER && move <= getTotalSquares();
    }
}
