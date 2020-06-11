package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    private final ChessAssertor assertor = new ChessAssertor();

    @Test
    void moveUp() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("D5");
        assertor.movePiece(board, "D4", "D5", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveUpRight() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("E5");
        assertor.movePiece(board, "D4", "E5", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveRight() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("E4");
        assertor.movePiece(board, "D4", "E4", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveDownRight() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("E3");
        assertor.movePiece(board, "D4", "E3", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveDown() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("D3");
        assertor.movePiece(board, "D4", "D3", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveDownLeft() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("C3");
        assertor.movePiece(board, "D4", "C3", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }

    @Test
    void moveLeft() throws InterruptedException {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("C4");
        assertor.movePiece(board, "D4", "C4", false);

        assertEquals(true, assertor.isExpectedPiece(board, nextKingPosition, ChessPieceRank.KING));
    }
}