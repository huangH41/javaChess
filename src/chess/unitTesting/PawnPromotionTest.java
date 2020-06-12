package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnPromotionTest {

    private static final ChessAssertor assertor = new ChessAssertor();

    @Test
    void promoteAsRook() {
        promote(ChessPieceRank.ROOK);
    }

    @Test
    void promoteAsKnight() {
        promote(ChessPieceRank.KNIGHT);
    }

    @Test
    void promoteAsBishop() {
        promote(ChessPieceRank.BISHOP);
    }

    @Test
    void promoteAsQueen() {
        promote(ChessPieceRank.QUEEN);
    }

    @Test
    void doNotPromoteAsKing() {
        assertThrows(IllegalStateException.class, () -> {
            promote(ChessPieceRank.KING);
        });
    }

    @Test
    void doNotPromoteAsPawn() {
        assertThrows(IllegalStateException.class, () -> {
            promote(ChessPieceRank.PAWN);
        });
    }

    private void promote(ChessPieceRank rank) {
        Board board = new Board();
        ChessPiece whitePawn = board.getPiece(new BoardPosition("H2"));

        doSampleMovesToPromotion(board);

        assertor.movePiece(board, "G7", "G8");
        assert (board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assert (((Pawn) whitePawn).isPawnPromotable());

        try {
            whitePawn = ((Pawn) whitePawn).promote(rank);
        } catch (IllegalStateException ex) {
            throw ex;
        }

        board.setPiece(new BoardPosition("G8"), whitePawn);

        assert (whitePawn.getClass().equals(ChessPieceClassificator.getClassInstance(rank)));
        assert (board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assert (assertor.isExpectedPiece(board, new BoardPosition("G8"),
                rank, ChessPieceColor.WHITE));

        assertor.drawBoard(board);
    }

    private void doSampleMovesToPromotion(Board board) {
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
}
