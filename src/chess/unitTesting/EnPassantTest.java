package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.Pawn;
import org.junit.Assert;

public class EnPassantTest {

    private final ChessAssertor assertor = new ChessAssertor();

    @org.junit.jupiter.api.Test
    void whiteEnpassLeft() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "A2", "A4", true);
        assertor.movePiece(board, "B7", "B6", true);
        assertor.movePiece(board, "B2", "B4", true);
        assertor.movePiece(board, "B8", "C6", true);
        assertor.movePiece(board, "B4", "B5", true);
        assertor.movePiece(board, "A7", "A5");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("B5"));
        assert (p.isEnPassable(board, new BoardPosition("A6")));

        // Move to apply en-passment
        assertor.movePiece(board, "B5", "A6");

        // Post En-Passant test
        assert (board.getPiece(new BoardPosition("A6")).getChessColor() == ChessPieceColor.WHITE);
        assert (!board.isOccupied(new BoardPosition("A5")));
    }

    @org.junit.jupiter.api.Test
    void whiteEnpassRight() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "C2", "C4", true);
        assertor.movePiece(board, "B7", "B6", true);
        assertor.movePiece(board, "B2", "B4", true);
        assertor.movePiece(board, "B8", "A6", true);
        assertor.movePiece(board, "B4", "B5", true);
        assertor.movePiece(board, "C7", "C5");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("B5"));
        assert (p.isEnPassable(board, new BoardPosition("C6")));

        // Move to apply en-passment
        assertor.movePiece(board, "B5", "C6");

        // Post En-Passant test
        assert (board.getPiece(new BoardPosition("C6")).getChessColor() == ChessPieceColor.WHITE);
        assert (!board.isOccupied(new BoardPosition("C5")));
    }

    @org.junit.jupiter.api.Test
    void blackEnpassLeft() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "C2", "C4", true);
        assertor.movePiece(board, "D7", "D5", true);
        assertor.movePiece(board, "B2", "B4", true);
        assertor.movePiece(board, "D5", "D4", true);
        assertor.movePiece(board, "B1", "A3");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("D4"));
        assert (p.isEnPassable(board, new BoardPosition("C3")));

        // Move to apply en-passment
        assertor.movePiece(board, "D4", "C3");

        // Post En-Passant test
        assert (board.getPiece(new BoardPosition("C3")).getChessColor() == ChessPieceColor.BLACK);
        assert (!board.isOccupied(new BoardPosition("C4")));
    }

    @org.junit.jupiter.api.Test
    void blackEnpassRight() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "D2", "D4", true);
        assertor.movePiece(board, "C7", "C5", true);
        assertor.movePiece(board, "C1", "H6", true);
        assertor.movePiece(board, "C5", "C4", true);
        assertor.movePiece(board, "H6", "G7");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert (p.isEnPassable(board, new BoardPosition("D3")));

        // Move to apply en-passment
        assertor.movePiece(board, "C4", "D3");

        // Post En-Passant test
        assert (board.getPiece(new BoardPosition("D3")).getChessColor() == ChessPieceColor.BLACK);
        assert (!board.isOccupied(new BoardPosition("D4")));
    }

    /**
     * Assert that the piece do not enpass opponent pieces who moved single row twice.
     */
    @org.junit.jupiter.api.Test
    void DoNotEnpassTwoMovers() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "C2", "C4", true);
        assertor.movePiece(board, "B7", "B6", true);
        assertor.movePiece(board, "B2", "B4", true);
        assertor.movePiece(board, "B6", "B5", true);
        assertor.movePiece(board, "C4", "C5", true);
        assertor.movePiece(board, "F7", "F5");

        try {
            Pawn twoMover = (Pawn) board.getPiece(new BoardPosition("B5"));
            assert ((int) ChessAssertor.accessPrivateMethodValuers(twoMover, "getMovementCount") == 2);
        } catch (Exception e) {
            Assert.fail("Failed to access private method!");
        }

        // Test en-passability (expected not en-passable)
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C5"));
        assert (!p.isEnPassable(board, new BoardPosition("B6")));

        // Move to apply en-passant
        try {
            assertor.movePiece(board, "B5", "C6");
            Assert.fail("En-passed opponent piece that should not passable due of second moves");
        } catch (InvalidMoveException e) {
            assert(e.getClass().equals(InvalidMoveException.class));
            System.out.println("En-passant to opponent piece who moved two moves are not applicable!");
        }
    }

}
