package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardUITest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();

    @Test
    public void printsEmptyGridOnFirstMove() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);

        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        ticTacToeModel.setCurrentBoard(3);

        String formattedBoard = new BoardFormatter(new Board(3)).format();
        assertEquals(formattedBoard + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void printsEmptyGridAndRequestsMoveFromHumanPlayer() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("3\n");
        new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);

        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.setCurrentBoard(3);

        Board board = new Board(3);
        String firstBoard = new BoardFormatter(board).format();
        board.setSquare(3, PlayerSymbol.X);
        String secondBoard = new BoardFormatter(board).format();
        String formattedRequest = String.format(PlayerMessages.MOVE_REQUEST, PlayerSymbol.X);

        assertEquals(
                firstBoard + "\n" + formattedRequest + "\n" + secondBoard + "\n",
                ioTestHelper.getOutContentString());
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidInputWithNonIntegerValues() {
        IOTestHelper ioTestHelper = new IOTestHelper();
        ioTestHelper.setInputStream("invalid input\n3\n");

        new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);

        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.setCurrentBoard(3);

        Board board = new Board(3);
        String firstBoard = new BoardFormatter(board).format();
        board.setSquare(3, PlayerSymbol.X);
        String secondBoard = new BoardFormatter(board).format();

        String formattedRequest = String.format(PlayerMessages.MOVE_REQUEST, PlayerSymbol.X);

        assertEquals(
                firstBoard + "\n" + formattedRequest + "\n" + PlayerMessages.INVALID_INPUT + "\n" + secondBoard + "\n",
                ioTestHelper.getOutContentString());
    }
}
