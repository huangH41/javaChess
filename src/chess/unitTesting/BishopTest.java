package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    private static final ChessAssertor assertor = new ChessAssertor();

    @org.junit.jupiter.api.Test
    void zigzagMove() {

    }

    @org.junit.jupiter.api.Test
    void moveUpLeft() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece bishop = ChessPiece.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("D4"));
        board.setPiece(bishop.getPosition(), bishop);

        assertor.movePiece(board, "D4", "B7");
        assert(assertor.isExpectedPiece(board, new BoardPosition("B7"), ChessPieceRank.BISHOP));
    }

    @org.junit.jupiter.api.Test
    void moveUpRight() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece bishop = ChessPiece.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("D4"));
        board.setPiece(bishop.getPosition(), bishop);

        assertor.movePiece(board, "D4", "G7");
        assert(assertor.isExpectedPiece(board, new BoardPosition("G7"), ChessPieceRank.BISHOP));
    }

    @org.junit.jupiter.api.Test
    void moveDownLeft() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece bishop = ChessPiece.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("D4"));
        board.setPiece(bishop.getPosition(), bishop);

        assertor.movePiece(board, "D4", "A1");
        assert(assertor.isExpectedPiece(board, new BoardPosition("A1"), ChessPieceRank.BISHOP));
    }

    @org.junit.jupiter.api.Test
    void moveDownRight() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece bishop = ChessPiece.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("D4"));
        board.setPiece(bishop.getPosition(), bishop);

        assertor.movePiece(board, "D4", "G1");
        assert(assertor.isExpectedPiece(board, new BoardPosition("G1"), ChessPieceRank.BISHOP));
    }
}