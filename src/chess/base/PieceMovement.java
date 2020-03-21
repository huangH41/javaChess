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
     * @param piece       which piece to move
     * @param dstPosition the destination position
     * @return piece moved diagonally?
     */
    public static boolean isDiagonalMovement(ChessPiece piece, BoardPosition dstPosition) {
        return getRelativeRowDistance(piece, dstPosition.getRow())
                == getRelativeColDistance(piece, dstPosition.getColumn());
    }

    /**
     * Is the piece moved horizontally (row to row)?
     *
     * @param piece       which piece to move
     * @param dstPosition the destination position
     * @return piece moved horizontally?
     */
    public static boolean isHorizontalMovement(ChessPiece piece, BoardPosition dstPosition) {
        return getRelativeRowDistance(piece, dstPosition.getRow()) == 0
                && getRelativeColDistance(piece, dstPosition.getColumn()) > 0;
    }

    /**
     * Is the piece moved vertically (column to column)?
     *
     * @param piece       which piece to move
     * @param dstPosition the destination position
     * @return piece moved vertically?
     */
    public static boolean isVerticalMovement(ChessPiece piece, BoardPosition dstPosition) {
        return getRelativeRowDistance(piece, dstPosition.getRow()) > 0
                && getRelativeColDistance(piece, dstPosition.getColumn()) == 0;
    }

    /**
     * Check if knight moves in L-shaped style direction at any angle
     *
     * @param piece       which piece to move
     * @param dstPosition the destination position
     * @return true if queen moves in L-shaped style direction at any angle
     */
    public static boolean isLetterLMovement(ChessPiece piece, BoardPosition dstPosition) {
        int relativeRow = getRelativeRowDistance(piece, dstPosition.getRow());
        int relativeCol = getRelativeColDistance(piece, dstPosition.getColumn());
        return (Math.abs(relativeCol) == 2 && Math.abs(relativeRow) == 1)
                || (Math.abs(relativeCol) == 1 && Math.abs(relativeRow) == 2);
    }

    /**
     * Check if queen moves in any linear direction by any block
     *
     * @param piece       which piece to move
     * @param dstPosition destination position
     * @return true if queen moves any linear directional by any block
     */
    public static boolean isAnyDirectionalMove(ChessPiece piece, BoardPosition dstPosition) {
        return PieceMovement.isDiagonalMovement(piece, dstPosition)
                || PieceMovement.isHorizontalMovement(piece, dstPosition)
                || PieceMovement.isVerticalMovement(piece, dstPosition);
    }

    /**
     * Check if the board position has a obstacle
     *
     * @param board       the board to check it's piece position
     * @param dstPosition the position to check it's piece availability
     * @return return true if no obstacle, return false if has a obstacle
     */
    public static boolean checkObstacles(Board board, BoardPosition dstPosition) {
        return (board.getPiece(dstPosition) == null);
    }

    public static int doSingleRelativeRowMovement(ChessPiece piece, BoardPosition dstPosition) {
        int relativeRow = dstPosition.getRow() - piece.getPosition().getRow();
        return Integer.compare(relativeRow, 0);
    }

    public static int doSingleRelativeColumnMovement(ChessPiece piece, BoardPosition dstPosition) {
        int relativeColumn = dstPosition.getColumn() - piece.getPosition().getColumn();
        return Integer.compare(relativeColumn, 0);
    }
}
