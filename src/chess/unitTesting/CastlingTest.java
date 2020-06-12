package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CastlingTest {
    private final ChessAssertor assertor = new ChessAssertor();

    @Test
    void whiteLeftCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece rook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        assertor.movePiece(board, king, "C1");

        assert (expectedCastlingPosition(board, new BoardPosition("C1"),
                new BoardPosition("D1")));
    }

    @Test
    void whiteRightCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece rook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("H1"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        assertor.movePiece(board, king, "G1");

        assert (expectedCastlingPosition(board, new BoardPosition("G1"),
                new BoardPosition("F1")));
    }

    @Test
    void blackLeftCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineBlackPiece(ChessPieceRank.KING, new BoardPosition("E8"));
        ChessPiece rook = ChessPiece.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A8"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        assertor.movePiece(board, king, "C8");

        assert (expectedCastlingPosition(board, new BoardPosition("C8"),
                new BoardPosition("D8")));
    }

    @Test
    void blackRightCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineBlackPiece(ChessPieceRank.KING, new BoardPosition("E8"));
        ChessPiece rook = ChessPiece.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("H8"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        assertor.movePiece(board, king, "G8");

        assert (expectedCastlingPosition(board, new BoardPosition("G8"),
                new BoardPosition("F8")));
    }

    @Test
    void notValidRookToCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece rook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        rook.move(new BoardPosition("B1"), board);
        rook.move(new BoardPosition("A1"), board);
        assertThrows(InvalidMoveException.class, () ->
                assertor.movePiece(board, king, "C1")
        );
    }

    @Test
    void notValidKingToCastling() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece rook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);

        assertor.drawBoard(board);
        king.move(new BoardPosition("E2"), board);
        king.move(new BoardPosition("E1"), board);
        assertThrows(InvalidMoveException.class, () ->
                assertor.movePiece(board, king, "C1")
        );
    }

    @Test
    void notValidCastlingPosition() {
        Board board = assertor.clearBoard(new Board());
        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece rook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));
        ChessPiece queen = ChessPiece.defineWhitePiece(ChessPieceRank.QUEEN, new BoardPosition("D1"));

        board.setPiece(king.getPosition(), king);
        board.setPiece(rook.getPosition(), rook);
        board.setPiece(queen.getPosition(), queen);

        assertor.drawBoard(board);
        assertThrows(InvalidMoveException.class, () ->
                assertor.movePiece(board, king, "C1")
        );
    }

    private boolean expectedCastlingPosition(Board board, BoardPosition ExpectedKingPosition, BoardPosition ExpectedRookPosition) {
        return assertor.isExpectedPiece(board, ExpectedKingPosition, ChessPieceRank.KING)
                && assertor.isExpectedPiece(board, ExpectedRookPosition, ChessPieceRank.ROOK);
    }
}