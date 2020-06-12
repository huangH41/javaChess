package chess.unitTesting;

import chess.base.ChessPieceRank;

class QueenTest extends ChessPieceTestEssentials {
    @org.junit.jupiter.api.Test
    void moveUpLeft() {
        basicMove(ChessPieceRank.QUEEN, "A7");
    }

    @org.junit.jupiter.api.Test
    void moveUpRight() {
        basicMove(ChessPieceRank.QUEEN, "G7");
    }

    @org.junit.jupiter.api.Test
    void moveDownLeft() {
        basicMove(ChessPieceRank.QUEEN, "A1");
    }

    @org.junit.jupiter.api.Test
    void moveDownRight() {
        basicMove(ChessPieceRank.QUEEN, "G1");
    }

    @org.junit.jupiter.api.Test
    void moveUp() {
        basicMove(ChessPieceRank.QUEEN, "D8");
    }

    @org.junit.jupiter.api.Test
    void moveDown() {
        basicMove(ChessPieceRank.QUEEN, "D1");
    }

    @org.junit.jupiter.api.Test
    void moveLeft() {
        basicMove(ChessPieceRank.QUEEN, "A4");
    }

    @org.junit.jupiter.api.Test
    void moveRight() {
        basicMove(ChessPieceRank.QUEEN, "H4");
    }
}
