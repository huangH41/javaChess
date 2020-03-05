package chess.base;

import chess.chessPiece.ChessPiece;

public class PieceMovement {

    public static int getRelativeColDistance(ChessPiece piece, int destinationColumn) {
        return Math.abs(destinationColumn - piece.getPosition().getColumn());
    }

    public static int getRelativeRowDistance(ChessPiece piece, int destinationRow) {
        return Math.abs(destinationRow - piece.getPosition().getRow());
    }

    public static boolean isDiagonalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getRelativeColDistance(piece, destinationColumn) == getRelativeRowDistance(piece, destinationRow);
    }

    public static boolean isHorizontalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getRelativeRowDistance(piece, destinationRow) == 0 && getRelativeColDistance(piece, destinationColumn) > 0;
    }

    /**
     * Is the piece moved vertically (column to column)?
     * @param piece
     * @param destinationColumn
     * @param destinationRow
     * @return
     */
    public static boolean isVerticalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getRelativeColDistance(piece, destinationColumn) == 0 && getRelativeRowDistance(piece, destinationRow) > 0;
    }
}
