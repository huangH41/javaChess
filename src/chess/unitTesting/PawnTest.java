package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.base.ChessPiece;
import chess.chessPiece.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnTest extends ChessPieceTestEssentials {
    @Test
    void testRegularMove() throws IllegalAccessException {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert (board.isOccupied(new BoardPosition("C4")));
        assert (!board.isOccupied(new BoardPosition("C2")));
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 1);
        assertor.drawBoard(board);

        assertor.movePiece(board, "C4", "C5");
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 2);
        assertor.drawBoard(board);
    }

    @org.junit.jupiter.api.Test
    void doNotMoveLeft() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.PAWN, "A4")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveRight() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.PAWN, "H4")
        );
    }

    @org.junit.jupiter.api.Test
    void whiteDoNotOvermove() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.PAWN, "D8")
        );
    }

    @Test
    void blackDoNotOvermove() {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");

        assertThrows(InvalidMoveException.class, () -> assertor.movePiece(board, "D7", "D3"));
    }

    @Test
    void attackLeft() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece pawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("D4"));
        ChessPiece enemyPawn = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition("C5"));

        board.setPiece(pawn.getPosition(), pawn);
        board.setPiece(enemyPawn.getPosition(), enemyPawn);

        assertor.drawBoard(board);
        assertor.movePiece(board, pawn, "C5");
        assert (assertor.isExpectedPiece(board, new BoardPosition("C5"),
                ChessPieceRank.PAWN, ChessPieceColor.WHITE));
    }

    @Test
    void attackRight() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece pawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("D4"));
        ChessPiece enemyPawn = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition("E5"));

        board.setPiece(pawn.getPosition(), pawn);
        board.setPiece(enemyPawn.getPosition(), enemyPawn);

        assertor.drawBoard(board);
        assertor.movePiece(board, pawn, "E5");
        assert (assertor.isExpectedPiece(board, new BoardPosition("E5"),
                ChessPieceRank.PAWN, ChessPieceColor.WHITE));
    }

    @Test
    void whiteDoNotAttackStraight() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece pawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("D4"));
        ChessPiece enemyPawn = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition("D5"));

        board.setPiece(pawn.getPosition(), pawn);
        board.setPiece(enemyPawn.getPosition(), enemyPawn);

        assertor.drawBoard(board);
        assertThrows(InvalidMoveException.class, () -> assertor.movePiece(board, pawn, "D5"));
    }

    @Test
    void blackDoNotAttackStraight() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece pawn = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition("D5"));
        ChessPiece enemyPawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("D4"));

        board.setPiece(pawn.getPosition(), pawn);
        board.setPiece(enemyPawn.getPosition(), enemyPawn);

        assertor.drawBoard(board);
        assertThrows(InvalidMoveException.class, () -> assertor.movePiece(board, pawn, "D4"));
    }

    @Test
    void doNotReverseWhites() {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");

        assertThrows(InvalidMoveException.class, () -> assertor.movePiece(board, "C4", "C3"));
    }

    @Test
    void doNotReverseBlacks() {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4");
        assertor.movePiece(board, "D7", "D5");

        assertThrows(InvalidMoveException.class, () -> assertor.movePiece(board, "D5", "D6"));
    }


    /**
     * A very simple test to test pawn ability to do a regular move, cross move, and en-passant simultaneously
     *
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
        assertThat(board.getPiece(new BoardPosition("C5")).equals(whitePawn),
                true, String.format("%s attacked pawn at C5", whitePawn));

        assertor.movePiece(board, "B7", "B5");
        assertThat((int) ChessAssertor.accessPrivateMethodValuers(
                board.getPiece(new BoardPosition("B5")), "getMovementCount") == 1,
                true, "The black pawn is moved once after white pawn's move");
        assertThat(whitePawn.isEnPassable(board, new BoardPosition("B6")),
                true, "White pawn is able to en-pass black pawn");

        assertor.movePiece(board, "C5", "B6");
        assertThat(!board.isOccupied(new BoardPosition("B5")),
                true, "Black pawn has been en-passed by white pawn");

        Pawn blackPawnFirstMover = (Pawn) board.getPiece(new BoardPosition("A7"));
        assertor.movePiece(board, "A7", "B6");
        assertThat(board.getPiece(new BoardPosition("B6")).equals(blackPawnFirstMover),
                true, "Black pawn attacked white pawn at their first move immediately");
    }
}