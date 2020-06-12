package chess.unitTesting;

import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RookTest extends ChessPieceTestEssentials {
    @org.junit.jupiter.api.Test
    void moveUp() {
        basicMove(ChessPieceRank.ROOK, "D8");
    }

    @org.junit.jupiter.api.Test
    void moveDown() {
        basicMove(ChessPieceRank.ROOK, "D1");
    }

    @org.junit.jupiter.api.Test
    void moveLeft() {
        basicMove(ChessPieceRank.ROOK, "A4");
    }

    @org.junit.jupiter.api.Test
    void moveRight() {
        basicMove(ChessPieceRank.ROOK, "H4");
    }

    @org.junit.jupiter.api.Test
    void doNotMoveUpLeft() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.ROOK, "A7")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveUpRight() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.ROOK, "G7")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveDownLeft() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.ROOK, "A1")
        );
    }

    @org.junit.jupiter.api.Test
    void doNotMoveDownRight() {
        assertThrows(InvalidMoveException.class, () ->
                basicMove(ChessPieceRank.ROOK, "G1")
        );
    }
}
