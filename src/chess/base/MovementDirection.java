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

    /**
     * Invert row and column direction. For example, invert UP to DOWN; LEFT to RIGHT; UP_LEFT to DOWN_RIGHT.
     *
     * @return Inverted direction enum (e.g. UP(1,0) to DOWN (-1,0) or LEFT(0,-1) to RIGHT(0,1))
     */
    public MovementDirection flipDirection() {
        return getMovementDirection(this.row * -1, this.column * -1);
    }

    /**
     * Invert row direction. For example, invert UP to DOWN; UP_LEFT to DOWN_LEFT but not flip columnar for LEFT and RIGHT.
     *
     * @return Inverted row direction enum (e.g. UP(1,0) to DOWN(-1,0) but LEFT(0,-1) still LEFT(0,-1))
     */
    public MovementDirection flipRowDirection() {
        return getMovementDirection(this.row * -1, this.column);
    }

    /**
     * Invert column direction. For example, invert LEFT to RIGHT; UP_LEFT to UP_RIGHT but not flip rower for UP and DOWN.
     *
     * @return Inverted column enum (e.g. LEFT(0,-1) to RIGHT(0,1) but DOWN(-1,0) still DOWN(-1,0))
     */
    public MovementDirection flipColumnDirection() {
        return getMovementDirection(this.row, this.column * -1);
    }

    private static int getOrdinate(int value) {
        return Integer.compare(value, 0);
    }
}
