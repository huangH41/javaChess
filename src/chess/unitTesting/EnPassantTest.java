package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.Pawn;
import org.junit.Assert;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnPassantTest {

    private final ChessAssertor assertor = new ChessAssertor();

    @org.junit.jupiter.api.Test
    void whiteEnpassLeft() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "A2", "A4");
        assertor.movePiece(board, "B7", "B6");
        assertor.movePiece(board, "B2", "B4");
        assertor.movePiece(board, "B8", "C6");
        assertor.movePiece(board, "B4", "B5");
        assertor.movePiece(board, "A7", "A5");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("B5"));
        assert (p.isEnPassable(board, new BoardPosition("A6")));

        // Move to apply en-passant
        assertor.movePiece(board, "B5", "A6");

        // Post En-Passant test
        assert (assertor.isExpectedPiece(board, new BoardPosition("A6"),
                ChessPieceRank.PAWN, ChessPieceColor.WHITE));
        assert (!board.isOccupied(new BoardPosition("A5")));
    }

    @org.junit.jupiter.api.Test
    void whiteEnpassRight() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "C2", "C4");
        assertor.movePiece(board, "B7", "B6");
        assertor.movePiece(board, "B2", "B4");
        assertor.movePiece(board, "B8", "A6");
        assertor.movePiece(board, "B4", "B5");
        assertor.movePiece(board, "C7", "C5");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("B5"));
        assert (p.isEnPassable(board, new BoardPosition("C6")));

        // Move to apply en-passment
        assertor.movePiece(board, "B5", "C6");

        // Post En-Passant test
        assert (assertor.isExpectedPiece(board, new BoardPosition("C6"),
                ChessPieceRank.PAWN, ChessPieceColor.WHITE));
        assert (!board.isOccupied(new BoardPosition("C5")));
    }

    @org.junit.jupiter.api.Test
    void blackEnpassLeft() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "B2", "B4");
        assertor.movePiece(board, "D7", "D5");
        assertor.movePiece(board, "B1", "A3");
        assertor.movePiece(board, "D5", "D4");
        assertor.movePiece(board, "C2", "C4");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("D4"));
        assert (p.isEnPassable(board, new BoardPosition("C3")));

        // Move to apply en-passment
        assertor.movePiece(board, "D4", "C3");

        // Post En-Passant test
        assertor.isExpectedPiece(board, new BoardPosition("C3"),
                ChessPieceRank.PAWN, ChessPieceColor.BLACK);
        assert (!board.isOccupied(new BoardPosition("C4")));
    }

    @org.junit.jupiter.api.Test
    void blackEnpassRight() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "B1", "A3");
        assertor.movePiece(board, "C7", "C5");
        assertor.movePiece(board, "A2", "A4");
        assertor.movePiece(board, "C5", "C4");
        assertor.movePiece(board, "D2", "D4");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert (p.isEnPassable(board, new BoardPosition("D3")));

        // Move to apply en-passment
        assertor.movePiece(board, "C4", "D3");

        // Post En-Passant test
        assert (assertor.isExpectedPiece(board, new BoardPosition("D3"),
                ChessPieceRank.PAWN, ChessPieceColor.BLACK));
        assert (!board.isOccupied(new BoardPosition("D4")));
    }

    /**
     * Assert that the piece do not enpass opponent pieces who already move before
     * your pawn stand beside the opponent pawn
     */
    @org.junit.jupiter.api.Test
    void DoNotEnpassOldMover() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "D2", "D4");
        assertor.movePiece(board, "C7", "C5");
        assertor.movePiece(board, "C1", "H6");
        assertor.movePiece(board, "C5", "C4");
        assertor.movePiece(board, "H6", "G7");

        // Test en-passability
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert (!p.isEnPassable(board, new BoardPosition("D3")));

        // Expect that en-passment is not appliable
        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "C4", "D3");
        });
    }

    /**
     * Assert that the piece do not enpass opponent pieces who moved single row twice.
     */
    @org.junit.jupiter.api.Test
    void DoNotEnpassTwoMover() {
        Board board = new Board();
        assertor.drawBoard(board);
        assertor.movePiece(board, "C2", "C4");
        assertor.movePiece(board, "B7", "B6");
        assertor.movePiece(board, "B2", "B4");
        assertor.movePiece(board, "F7", "F5");
        assertor.movePiece(board, "C4", "C5");
        assertor.movePiece(board, "B6", "B5");

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
        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "B5", "C6");
        });
    }

}
