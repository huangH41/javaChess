package chess.base;

import chess.chessPiece.ChessPiece;

public class PieceMovement {

    /**
     * DO NOT CREATE A OBJECT!
     */
    private PieceMovement() {
    }

    /**
     * Get relative column distance from a piece current column position to target column position
     *
     * @param piece             piece to move
     * @param destinationColumn the target column position
     * @return relative column distance of a piece
     */
    public static int getRelativeColDistance(ChessPiece piece, int destinationColumn) {
        return Math.abs(destinationColumn - piece.getPosition().getColumn());
    }

    /**
     * Get relative row distance from a piece current row position to target row position
     *
     * @param piece          piece to move
     * @param destinationRow the target row position
     * @return relative row distance of a piece
     */
    public static int getRelativeRowDistance(ChessPiece piece, int destinationRow) {
        return Math.abs(destinationRow - piece.getPosition().getRow());
    }

    /**
     * Is the piece moved diagonally (relative move of row and column are same)?
     *
     * @param piece             which piece to move
     * @param destinationRow    the destination row
     * @param destinationColumn the destination column
     * @return piece moved diagonally?
     */
    public static boolean isDiagonalMovement(ChessPiece piece, int destinationRow, int destinationColumn) {
        return getRelativeRowDistance(piece, destinationRow) == getRelativeColDistance(piece, destinationColumn);
    }

    /**
     * Is the piece moved horizontally (row to row)?
     *
     * @param piece             which piece to move
     * @param destinationRow    the destination row
     * @param destinationColumn the destination column
     * @return piece moved horizontally?
     */
    public static boolean isHorizontalMovement(ChessPiece piece, int destinationRow, int destinationColumn) {
        return getRelativeRowDistance(piece, destinationRow) == 0 && getRelativeColDistance(piece, destinationColumn) > 0;
    }

    /**
     * Is the piece moved vertically (column to column)?
     *
     * @param piece             which piece to move
     * @param destinationRow    the destination row
     * @param destinationColumn the destination column
     * @return piece moved vertically?
     */
    public static boolean isVerticalMovement(ChessPiece piece, int destinationRow, int destinationColumn) {
        return getRelativeRowDistance(piece, destinationRow) > 0 && getRelativeColDistance(piece, destinationColumn) == 0;
    }

    /**
     * Check if the board position has a obstacle
     *
     * @param board    the board to check it's piece position
     * @param position the position to check it's piece availability
     * @return return true if no obstacle, return false if has a obstacle
     */
    public static boolean checkObstacles(Board board, BoardPosition position) {
        return (ChessPiece.getFromBoard(board, position) == null);
    }
}
