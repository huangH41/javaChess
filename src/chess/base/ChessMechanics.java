package chess.base;

import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

/**
 * This class controls the chess game mechanics
 */
public class ChessMechanics {

    public static boolean isKingChecked(King k) {
        return !(k.isChecked() == King.KingCheckState.SAFE);
    }

    private static boolean performSimulateMove(ChessPiece piece, BoardPosition position) {
        return false;
    }

}
