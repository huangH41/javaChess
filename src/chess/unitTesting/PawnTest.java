package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;
import chess.chessPiece.Rook;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import sun.plugin.dom.exception.InvalidStateException;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private static final ChessAssertor assertor = new ChessAssertor();

    @Test
    void testRegularMove() throws IllegalAccessException {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert(board.isOccupied(new BoardPosition("C4")));
        assert(!board.isOccupied(new BoardPosition("C2")));
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 1);
        assertor.drawBoard(board);

        assertor.movePiece(board, "C4", "C5");
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 2);
        assertor.drawBoard(board);
    }

    @Test
    void doNotReverse() {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");

        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "C4", "C3");
        }).printStackTrace();
    }


    /**
     * A very simple test to test pawn ability to do a regular move, cross move, and en-passant simultaneously
     * @throws IllegalAccessException if happened, these test would failed automatically!
     */
    @Test
    void zigzaggerScenario() throws IllegalAccessException {
        Board board = new Board();
        Pawn whitePawn = (Pawn) board.getPiece(new BoardPosition("B2"));

        assertor.drawBoard(board);
        assertor.movePiece(board, "B2", "B4");
        assertor.movePiece(board, "C7", "C5");
        assertor.movePiece(board, "B4", "C5");
        assert(board.getPiece(new BoardPosition("C5")).equals(whitePawn));

        assertor.movePiece(board, "B7", "B5");
        assert((int) ChessAssertor.accessPrivateMethodValuers(
                board.getPiece(new BoardPosition("B5")), "getMovementCount") == 1);
        assert(whitePawn.isEnPassable(board, new BoardPosition("B6")));

        assertor.movePiece(board, "C5", "B6");
        assert(!board.isOccupied(new BoardPosition("B5")));

        Pawn blackPawnFirstMover = (Pawn) board.getPiece(new BoardPosition("A7"));
        assertor.movePiece(board, "A7", "B6");
        assert(board.getPiece(new BoardPosition("B6")).equals(blackPawnFirstMover));
    }
}