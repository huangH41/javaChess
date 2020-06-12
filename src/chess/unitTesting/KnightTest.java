package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {

    private static final ChessAssertor assertor = new ChessAssertor();

    @Test
    void move() {
        Exception ex;
        Board board = new Board();
        ChessPiece knight = board.getPiece(new BoardPosition("B1"));

        assertor.movePiece(board, "B1", "C3");
        assert(knight.getPosition().toString().equals("C3"));

        assertor.movePiece(board, "C3", "B1");
        assert(knight.getPosition().toString().equals("B1"));

        assertor.movePiece(board, "B1", "A3");
        assert(knight.getPosition().toString().equals("A3"));

        assertor.movePiece(board, "A3", "B1");
        assert(knight.getPosition().toString().equals("B1"));

        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "B1", "D2");
        }).printStackTrace();

        assertor.movePiece(board, "B1", "C3");
        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "C3", "E2");
        }).printStackTrace();

        assertor.movePiece(board, "C3", "E4");
        assert(knight.getPosition().toString().equals("E4"));

        assertor.movePiece(board, "E4", "C3");
        assertor.movePiece(board, "C3", "A4");
        assert(knight.getPosition().toString().equals("A4"));

    }
}
