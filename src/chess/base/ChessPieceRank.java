package chess.base;

public enum ChessPieceRank {
    PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING;

    public String toString() {
        return this.name();
    }
}
