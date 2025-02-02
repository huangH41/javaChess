package chess.unitTesting;

import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopTest extends ChessPieceTestEssentials {
    @org.junit.jupiter.api.Test
    void moveUpLeft() {
        basicMove(ChessPieceRank.BISHOP, "A7");
    }

    @org.junit.jupiter.api.Test
    void moveUpRight() {
        basicMove(ChessPieceRank.BISHOP, "G7");
    }

    @org.junit.jupiter.api.Test
    void moveDownLeft() {
        basicMove(ChessPieceRank.BISHOP, "A1");
    }

    @org.junit.jupiter.api.Test
    void moveDownRight() {
        basicMove(ChessPieceRank.BISHOP, "G1");
    }

    @org.junit.jupiter.api.Test
    void doNotMoveUp() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.BISHOP, "D8")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveDown() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.BISHOP, "D1")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveLeft() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.BISHOP, "A4")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveRight() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.BISHOP, "H4")
        );
    }
}