package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoardUITest {
    private final IOTestHelper ioTestHelper = new IOTestHelper();

    @Test
    public void printsEmptyGridOnFirstMove() {
        setupDelayedRunnerGame(GameType.COMPUTER_HUMAN);

        String formattedBoard = new BoardFormatter(new Board(3)).format();
        assertEquals(
                IOTestHelper.CLEAR_LINE + formattedBoard + "\n",
                ioTestHelper.getOutContentString());
    }

    @Test
    public void printsEmptyGridAndRequestsMoveFromHumanPlayer() {
        ioTestHelper.setInputStream("3\n");
        setupDelayedRunnerGame(GameType.HUMAN_COMPUTER);

        String[] boardStates = getBoardStates(3);
        String formattedRequest = String.format(PlayerMessages.MOVE_REQUEST, PlayerSymbol.X);

        assertEquals(
                IOTestHelper.CLEAR_LINE + boardStates[0] + "\n" +
                        formattedRequest + "\n" +
                        IOTestHelper.CLEAR_LINE + boardStates[1] + "\n",
                ioTestHelper.getOutContentString());
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidInputWithNonIntegerValues() {
        ioTestHelper.setInputStream("1\ninvalid input\n4\n2\n5\n3\n");
        runInvalidMoveTest();
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidInputWithTakenSquare() {
        ioTestHelper.setInputStream("1\n1\n4\n2\n5\n3\n");
        runInvalidMoveTest();
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidOutOfRangeSquare() {
        ioTestHelper.setInputStream("1\n10\n4\n2\n5\n3\n");
        runInvalidMoveTest();
    }

    private void setupDelayedRunnerGame(GameType humanComputer) {
        TicTacToeModel ticTacToeModel = new TicTacToeModel(new DelayedRunner());
        new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);

        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(humanComputer);
        ticTacToeModel.createGame(3);
    }

    private void runInvalidMoveTest() {
        TicTacToeModel ticTacToeModel = new TicTacToeModel(new ImmediateRunner());
        new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);

        ticTacToeModel.setupNewGame();
        ticTacToeModel.setGameType(GameType.HUMAN_HUMAN);
        ticTacToeModel.createGame(3);

        String[] boardStates = getBoardStates(1);

        String[] firstMessage = Arrays.copyOfRange(ioTestHelper.getOutContentAsLines(), 0, 13);

        assertEquals(
                IOTestHelper.CLEAR_LINE +
                        boardStates[0] + "\n" +
                        requestMoveString(PlayerSymbol.X) + "\n" +
                        IOTestHelper.CLEAR_LINE +
                        boardStates[1] + "\n" +
                        requestMoveString(PlayerSymbol.O) + "\n" +
                        PlayerMessages.INVALID_INPUT
                ,
                String.join("\n", firstMessage));
    }

    private String requestMoveString(PlayerSymbol playerSymbol) {
        return String.format(PlayerMessages.MOVE_REQUEST, playerSymbol);
    }

    private String[] getBoardStates(int move) {
        Board board = new Board(3);
        String firstBoard = new BoardFormatter(board).format();
        board.setSquare(move, PlayerSymbol.X);
        String secondBoard = new BoardFormatter(board).format();

        return new String[]{firstBoard, secondBoard};
    }
}
