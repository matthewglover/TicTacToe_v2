package com.matthewglover.tictactoe.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Game {
    private Board board;
    private PlayerSymbol nextPlayerSymbol = PlayerSymbol.X;
    private List<Integer> moveSequence = new ArrayList<>();

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public PlayerSymbol getNextPlayerSymbol() {
        return nextPlayerSymbol;
    }

    public PlayerSymbol getWinner() {
        if (board.isAnyWinningLine(PlayerSymbol.X)) {
            return PlayerSymbol.X;
        } else if (board.isAnyWinningLine(PlayerSymbol.O)) {
            return PlayerSymbol.O;
        }
        return PlayerSymbol.NEITHER;
    }

    public int getCurrentMove() {
        return moveSequence.get(moveSequence.size() - 1);
    }

    public List<Game> getNextMoves() {
        return getEmptySquares()
                .stream()
                .map(makeMove())
                .collect(Collectors.toList());
    }

    public boolean isOver() {
        return isWinner() || board.isFull();
    }

    public boolean isWinner() {
        return !getWinner().isEmpty();
    }

    public void move(int squareNumber) {
        if (board.isEmptySquare(squareNumber)) {
            moveSequence.add(squareNumber);
            board.setSquare(squareNumber, getNextPlayerSymbol());
            toggleNextPlayer();
        }
    }

    public Game duplicate() {
        Board duplicateBoard = board.duplicate();
        Game duplicateGame = new Game(duplicateBoard);
        duplicateGame.nextPlayerSymbol = nextPlayerSymbol;
        duplicateGame.moveSequence = new ArrayList<>(moveSequence);
        return duplicateGame;
    }

    private List<Integer> getEmptySquares() {
        return board.getEmptySquares();
    }

    private Function<Integer, Game> makeMove() {
        return (Integer squareNumber) -> {
            Game newGame = duplicate();
            newGame.move(squareNumber);
            return newGame;
        };
    }

    private void toggleNextPlayer() {
        nextPlayerSymbol = (nextPlayerSymbol == PlayerSymbol.X) ? PlayerSymbol.O : PlayerSymbol.X;
    }

    public List<Integer> getMoveSequence() {
        return moveSequence;
    }
}
