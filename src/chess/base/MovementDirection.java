package chess.base;

/**
 * Shows degree direction of piece movement.
 */
public enum MovementDirection {
    UP(1, 0), UP_RIGHT(1, 1), RIGHT(0, 1), DOWN_RIGHT(-1, 1),
    DOWN(-1, 0), DOWN_LEFT(-1, -1), LEFT(0, -1), UP_LEFT(1, -1),
    STATIC(0, 0);

    private final int row, column;

    MovementDirection(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get direction of piece between current position with targeted position.
     *
     * @param relativeRow    relative row position ordinate
     * @param relativeColumn relative column position ordinate
     * @return the movement direction
     */
    public static MovementDirection getMovementDirection(int relativeRow, int relativeColumn) {
        int rowOrdinate = getOrdinate(relativeRow), colOrdinate = getOrdinate(relativeColumn);
        for (MovementDirection direction : MovementDirection.values()) {
            if (direction.column == colOrdinate && direction.row == rowOrdinate) {
                return direction;
            }
        }
        return STATIC;
    }

    /**
     * Return row movement position degree ordinate of the constant.
     *
     * @return (+ 1) if up, (-1) if down, or (0) if not moving vertically
     */
    public int getRowOrdinate() {
        return row;
    }

    /**
     * Return column movement position degree ordinate of the constant.
     *
     * @return (+ 1) if right, (-1) if left, or (0) if not moving horizontally
     */
    public int getColumnOrdinate() {
        return column;
    }

    private static int getOrdinate(int value) {
        return Integer.compare(value, 0);
    }
}
