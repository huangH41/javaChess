package chess.base;

public class PieceMovement {

    /**
     * DO NOT CREATE A OBJECT!
     */
    private PieceMovement() {
    }

    /**
     * Get relative column distance from a piece current column position to target column position (non-negative value)
     *
     * @param piece             piece to move
     * @param destinationColumn the target column position
     * @return relative column distance of a piece
     */
    public static int getRelativeColDistance(ChessPiece piece, int destinationColumn) {
        return getRelativeColDistance(piece, destinationColumn, false);
    }

    /**
     * Get relative row distance from a piece current row position to target row position (non-negative value)
     *
     * @param piece          piece to move
     * @param destinationRow the target row position
     * @return relative row distance of a piece
     */
    public static int getRelativeRowDistance(ChessPiece piece, int destinationRow) {
        return getRelativeRowDistance(piece, destinationRow, false);
    }

    /**
     * Get relative column distance from a piece current column position to target column position
     *
     * @param piece             piece to move
     * @param destinationColumn the target column position
     * @param negativeValue     negative values?
     * @return relative column distance of a piece
     */
    public static int getRelativeColDistance(ChessPiece piece, int destinationColumn, boolean negativeValue) {
        int value = destinationColumn - piece.getPosition().getColumn();
        return negativeValue ? value : Math.abs(value);
    }

    /**
     * Get relative row distance from a piece current row position to target row position
     *
     * @param piece          piece to move
     * @param destinationRow the target row position
     * @param negativeValue  final values are non-negative values
     * @return relative row distance of a piece
     */
    public static int getRelativeRowDistance(ChessPiece piece, int destinationRow, boolean negativeValue) {
        int value = destinationRow - piece.getPosition().getRow();
        return negativeValue ? value : Math.abs(value);
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
        int relativeRows = getRelativeRowDistance(piece, dstPosition.getRow());
        int relativeCols = getRelativeColDistance(piece, dstPosition.getColumn());
        return relativeCols > 0 && relativeRows == 0;
    }

    /**
     * Is the piece moved vertically (column to column)?
     *
     * @param piece       which piece to move
     * @param dstPosition the destination position
     * @return piece moved vertically?
     */
    public static boolean isVerticalMovement(ChessPiece piece, BoardPosition dstPosition) {
        int relativeRows = getRelativeRowDistance(piece, dstPosition.getRow());
        int relativeCols = getRelativeColDistance(piece, dstPosition.getColumn());

        return relativeRows > 0
                && relativeCols == 0;
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

}
