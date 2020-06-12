package chess.unitTesting;

import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest extends ChessPieceTestEssentials {
    @Test
    void moveUpperLeft() {
        basicMove(ChessPieceRank.KNIGHT, "C6");
    }

    @Test
    void moveUpperRight() {
        basicMove(ChessPieceRank.KNIGHT, "E6");
    }

    @Test
    void moveRightyUp() {
        basicMove(ChessPieceRank.KNIGHT, "F5");
    }

    @Test
    void moveRightyDown() {
        basicMove(ChessPieceRank.KNIGHT, "F3");
    }

    @Test
    void moveBelowRight() {
        basicMove(ChessPieceRank.KNIGHT, "E2");
    }

    @Test
    void moveBelowLeft() {
        basicMove(ChessPieceRank.KNIGHT, "C2");
    }

    @Test
    void moveLeftyDown() {
        basicMove(ChessPieceRank.KNIGHT, "B3");
    }

    @Test
    void moveLeftyUp() {
        basicMove(ChessPieceRank.KNIGHT, "B5");
    }

    @org.junit.jupiter.api.Test
    void doNotMoveUp() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.KNIGHT, "D8")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveLeft() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.KNIGHT, "A4")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveDownRight() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.KNIGHT, "G1")
        );
    }
}
