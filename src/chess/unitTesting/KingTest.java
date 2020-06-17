package chess.unitTesting;

import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
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
        assertThrows(InvalidMoveException.class, () -> basicMove(ChessPieceRank.KING, "D6"));
    }
}