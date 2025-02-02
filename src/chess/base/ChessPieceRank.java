package chess.base;

import chess.base.exceptions.IllegalNotationException;

public enum ChessPieceRank {
    PAWN('P'),
    ROOK('R'),
    KNIGHT('N'),
    BISHOP('B'),
    QUEEN('Q'),
    KING('K');

    private final char initialPieceRank;

    ChessPieceRank(char initialPieceRank) {
        this.initialPieceRank = initialPieceRank;
    }

    public static ChessPieceRank getPieceRankByInitial(char initialPieceRank) throws IllegalNotationException {
        for (ChessPieceRank rank : ChessPieceRank.values()) {
            if (rank.initialPieceRank == initialPieceRank) {
                return rank;
            }
        } throw IllegalNotationException.rank();
    }

    public char getInitialPieceRank(ChessPieceColor color) {
        return (color == ChessPieceColor.WHITE) ?
                Character.toLowerCase(this.initialPieceRank) : Character.toUpperCase(this.initialPieceRank);
    }

    public boolean isPromotable() {
        return this.equals(ROOK) || this.equals(KNIGHT) || this.equals(BISHOP) || this.equals(QUEEN);
    }

    @Override
    public String toString() {
        return this.name();
    }
}
