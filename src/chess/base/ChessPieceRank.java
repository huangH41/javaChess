package chess.base;

public enum ChessPieceRank {
    PAWN('P'),
    ROOK('R'),
    KNIGHT('N'),
    BISHOP('B'),
    QUEEN('Q'),
    KING('K');

    private char initialPieceRank;

    ChessPieceRank(char initialPieceRank) {
        this.initialPieceRank = initialPieceRank;
    }

    public String toString() {
        return this.name();
    }

    public char getInitialPieceRank(ChessPieceColor color) {
        return (color == ChessPieceColor.WHITE) ?
                Character.toLowerCase(this.initialPieceRank) : Character.toUpperCase(this.initialPieceRank);
    }
}
