package chess.base;

import chess.chessPiece.ChessPiece;

public class PawnMovement {
    public static int getColMovementToDest(ChessPiece piece, int destinationColumn) {
        return Math.abs(destinationColumn - piece.getPosition().getColumn());
    }

    public static int getRowMovementToDest(ChessPiece piece, int destinationRow) {
        return Math.abs(destinationRow - piece.getPosition().getRow());
    }

    public static boolean isDiagonalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getColMovementToDest(piece, destinationColumn) == getRowMovementToDest(piece, destinationRow);
    }

    public static boolean isHorizontalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getRowMovementToDest(piece, destinationRow) == 0 && getColMovementToDest(piece, destinationColumn) > 0;
    }

    public static boolean isVerticalMovement(ChessPiece piece, int destinationColumn, int destinationRow) {
        return getColMovementToDest(piece, destinationColumn) == 0 && getRowMovementToDest(piece, destinationRow) > 0;
    }
}
