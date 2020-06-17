package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidPromotionException;
import chess.base.ChessPiece;
import chess.chessPiece.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnPromotionTest extends ChessPieceTestEssentials {

    @Test
    void promoteAsRook() {
        whitePawnPromotion(ChessPieceRank.ROOK);
    }

    @Test
    void promoteAsKnight() {
        whitePawnPromotion(ChessPieceRank.KNIGHT);
    }

    @Test
    void promoteAsBishop() {
        whitePawnPromotion(ChessPieceRank.BISHOP);
    }

    @Test
    void promoteAsQueen() {
        whitePawnPromotion(ChessPieceRank.QUEEN);
    }

    @Test
    void doNotPromoteAsKing() {
        assertThrows(InvalidPromotionException.class, () -> {
            whitePawnPromotion(ChessPieceRank.KING);
        });
    }

    @Test
    void doNotPromoteAsPawn() {
        assertThrows(InvalidPromotionException.class, () -> {
            whitePawnPromotion(ChessPieceRank.PAWN);
        });
    }

    @Test
    void promoteBlackAsBishop() {
        blackPawnPromotion(ChessPieceRank.BISHOP);
    }

    private void whitePawnPromotion(ChessPieceRank rank) {
        Board board = new Board();
        ChessPiece whitePawn = board.getPiece(new BoardPosition("H2"));

        doSampleMovesToWhitePiecePromotion(board);

        assertor.movePiece(board, "G7", "G8");
        assert (board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assertThat(((Pawn) whitePawn).isPawnPromotable(true),
                true, "That piece is promotable at enemy base row");

        try {
            whitePawn = ((Pawn) whitePawn).promote(rank);
        } catch (InvalidPromotionException ex) {
            throw new InvalidPromotionException(ex);
        }

        board.setPiece(new BoardPosition("G8"), whitePawn);

        assertThat(whitePawn.getClass().equals(ChessPieceClassificator.getClassInstance(rank)),
                true, "Black pawn have been promoted as " + rank);
        assert (board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assertThat(assertor.isExpectedPiece(board, new BoardPosition("G8"),
                rank, ChessPieceColor.WHITE), true, "Piece has already promoted as " + rank);

        assertor.drawBoard(board);
    }

    private void doSampleMovesToWhitePiecePromotion(Board board) {
        assertor.movePiece(board, "H2", "H4");
        assertor.movePiece(board, "E7", "E5");
        assertor.movePiece(board, "H4", "H5");
        assertor.movePiece(board, "G7", "G6");
        assertor.movePiece(board, "F2", "F4");
        assertor.movePiece(board, "H7", "H6");
        assertor.movePiece(board, "H5", "G6");
        assertor.movePiece(board, "H6", "H5");
        assertor.movePiece(board, "H1", "H5");
        assertor.movePiece(board, "G8", "F6");
        assertor.movePiece(board, "G6", "G7");
        assertor.movePiece(board, "F8", "B4");

        // TODO: CHECK STATE if H5 -> E5 (use Black-Castling for these scenario)
        assertor.movePiece(board, "H5", "F5");

        assertor.movePiece(board, "E5", "F4");
    }

    private void blackPawnPromotion(ChessPieceRank rank) {
        Board board = new Board();
        ChessPiece blackPawn = board.getPiece(new BoardPosition("D7"));

        doSampleMovesToBlackPiecePromotion(board);

        assertor.movePiece(board, "C2", "C1");
        assert (board.getPiece(new BoardPosition("C1")).equals(blackPawn));
        assertThat(((Pawn) blackPawn).isPawnPromotable(true),
                true, "That piece is promotable at enemy base row");

        try {
            blackPawn = ((Pawn) blackPawn).promote(rank);
        } catch (InvalidPromotionException ex) {
            throw new InvalidPromotionException(ex);
        }

        board.setPiece(new BoardPosition("C1"), blackPawn);

        assertThat(blackPawn.getClass().equals(ChessPieceClassificator.getClassInstance(rank)),
                    true, "Black pawn have been promoted as " + rank);
        assert (board.getPiece(new BoardPosition("C1")).equals(blackPawn));
        assertThat(assertor.isExpectedPiece(board, new BoardPosition("C1"),
                rank, ChessPieceColor.BLACK), true, "Piece has already promoted as " + rank);

        assertor.drawBoard(board);
    }

    private void doSampleMovesToBlackPiecePromotion(Board board) {
        assertor.movePiece(board, "G2", "G3");
        assertor.movePiece(board, "C7", "C5");
        assertor.movePiece(board, "D2", "D4");
        assertor.movePiece(board, "D7", "D5");
        assertor.movePiece(board, "D4", "C5");
        assertor.movePiece(board, "D5", "D4");
        assertor.movePiece(board, "C2", "C4");
        assertor.movePiece(board, "D4", "C3");

        // TODO: THIS IS THE WARNING IF QUEEN TRIED TO MOVE from D1 to A4! (buggy)
        assertor.movePiece(board, "D1", "B3");

        assertor.movePiece(board, "D8", "D7");
        assertor.movePiece(board, "C1", "G5");
        assertor.movePiece(board, "C3", "C2");
        assertor.movePiece(board, "B1", "D2");
    }
}
