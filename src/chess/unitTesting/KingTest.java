package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest extends ChessPieceTestEssentials {

    @Test
    void moveUp() {
        basicMove(ChessPieceRank.KING, "D5");
    }

    @Test
    void moveUpRight() {
        basicMove(ChessPieceRank.KING, "E5");
    }

    @Test
    void moveRight() {
        basicMove(ChessPieceRank.KING, "E4");
    }

    @Test
    void moveDownRight() {
        basicMove(ChessPieceRank.KING, "E3");
    }

    @Test
    void moveDown() {
        basicMove(ChessPieceRank.KING, "D3");
    }

    @Test
    void moveDownLeft() {
        basicMove(ChessPieceRank.KING, "C3");
    }

    @Test
    void moveLeft() {
        basicMove(ChessPieceRank.KING, "C4");
    }

    @Test
    void moveUpLeft() {
        basicMove(ChessPieceRank.KING, "C5");
    }

    @Test
    void invalidMove() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece king = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("D4"));
        board.setPiece(king.getPosition(), king);

        BoardPosition nextKingPosition = new BoardPosition("D6");
        assertor.drawBoard(board);
        assertThrows(InvalidMoveException.class, () -> king.move(nextKingPosition, board));
    }
}