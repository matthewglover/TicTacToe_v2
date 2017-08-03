package com.matthewglover.tictactoe.consoleui;

import com.matthewglover.tictactoe.core.Board;
import com.matthewglover.tictactoe.core.GameType;
import com.matthewglover.tictactoe.core.PlayerSymbol;
import com.matthewglover.tictactoe.core.TicTacToeModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardUITest {
    private final TicTacToeModel ticTacToeModel = new TicTacToeModel();
    private final IOTestHelper ioTestHelper = new IOTestHelper();

    @Test
    public void printsEmptyGridOnFirstMove() {
        setupBoard();

        ticTacToeModel.setCurrentGameType(GameType.COMPUTER_HUMAN);
        ticTacToeModel.setCurrentBoard(3);

        String formattedBoard = new BoardFormatter(new Board(3)).format();
        assertEquals(formattedBoard + "\n", ioTestHelper.getOutContentString());
    }

    @Test
    public void printsEmptyGridAndRequestsMoveFromHumanPlayer() {
        ioTestHelper.setInputStream("3\n");
        setupBoard();

        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.setCurrentBoard(3);

        String[] boardStates = getBoardStates(3);
        String formattedRequest = String.format(PlayerMessages.MOVE_REQUEST, PlayerSymbol.X);

        assertEquals(
                boardStates[0] + "\n" + formattedRequest + "\n" + boardStates[1] + "\n",
                ioTestHelper.getOutContentString());
    }

    @Test
    public void promptForMoveReportsErrorAndPromptsForValidInputWithNonIntegerValues() {
        ioTestHelper.setInputStream("invalid input\n3\n");
        setupBoard();

        ticTacToeModel.setCurrentGameType(GameType.HUMAN_COMPUTER);
        ticTacToeModel.setCurrentBoard(3);

        String[] boardStates = getBoardStates(3);
        String formattedRequest = String.format(PlayerMessages.MOVE_REQUEST, PlayerSymbol.X);

        assertEquals(
                boardStates[0] + "\n" + formattedRequest + "\n" + PlayerMessages.INVALID_INPUT + "\n" + boardStates[1] + "\n",
                ioTestHelper.getOutContentString());
    }

    private BoardUI setupBoard() {
        return new BoardUI(ioTestHelper.getInputStream(), ioTestHelper.getOutputStream(), ticTacToeModel);
    }

    private String[] getBoardStates(int move) {
        Board board = new Board(3);
        String firstBoard = new BoardFormatter(board).format();
        board.setSquare(move, PlayerSymbol.X);
        String secondBoard = new BoardFormatter(board).format();

        return new String[]{firstBoard, secondBoard};
    }
}
