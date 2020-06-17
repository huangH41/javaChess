package chess.unitTesting;

import chess.base.ChessPieceRank;
import chess.chessPiece.*;

final class ChessPieceClassificator {
    public static Class<?> getClassInstance(ChessPieceRank rank) {
        switch (rank) {
            case PAWN:
                return Pawn.class;
            case ROOK:
                return Rook.class;
            case KNIGHT:
                return Knight.class;
            case BISHOP:
                return Bishop.class;
            case QUEEN:
                return Queen.class;
            case KING:
                return King.class;
        }
        return null;
    }
}
