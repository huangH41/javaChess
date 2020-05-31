package chess.base;

/**
 * This class contains chess piece movement ordinate which compare the current position with targeted position.
 * <p>
 * It has negative, positive, and zero value for ordinate positioning
 */
public class MovementOrdinate {
    private final BoardPosition srcPosition, dstPosition;

    public MovementOrdinate(BoardPosition srcPosition, BoardPosition dstPosition) {
        this.srcPosition = srcPosition;
        this.dstPosition = dstPosition;
    }

    /**
     * Return direction of piece between current position with targeted position.
     *
     * @return direction of piece movement (up, down, left, right, or combined diagonally)
     */
    public MovementDirection getMovementDirection() {
        return MovementDirection.getMovementDirection(getRowDegreeOrdinate(), getColumnDegreeOrdinate());
    }

    /**
     * Return column movement position degree ordinate between current position with targeted position.
     *
     * @return (+ 1) if right, (-1) if left, or (0) if not moving horizontally
     */
    public int getColumnDegreeOrdinate() {
        return getDegreeOrdinate(srcPosition.getColumn(), dstPosition.getColumn());
    }

    /**
     * Return row movement position degree ordinate between current position with targeted position.
     *
     * @return (+ 1) if up, (-1) if down, or (0) if not moving vertically
     */
    public int getRowDegreeOrdinate() {
        return getDegreeOrdinate(srcPosition.getRow(), dstPosition.getRow());
    }

    /**
     * Calculate degree movement ordinary between current position with targeted position.
     *
     * @return (+ 1) if positive, (-1) if negative, or (0) if zero relative moves
     */
    private int getDegreeOrdinate(int source, int target) {
        int relativeDistance = target - source;
        return Integer.compare(relativeDistance, 0);
    }
}
