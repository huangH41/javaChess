package chess.base;

public enum MovementDirection {
    UP(1,0), UP_RIGHT(1,1), RIGHT(0,1), DOWN_RIGHT(-1,1),
    DOWN(-1,0), DOWN_LEFT(-1,-1), LEFT(0,-1), UP_LEFT(1,-1),
    STATIC(0,0);

    private final int row, column;

    MovementDirection(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRowOrdinate() {
        return row;
    }

    public int getColumnOrdinate() {
        return column;
    }

    public static MovementDirection getMovementDirection(int relativeRow, int relativeColumn) {
        int rowOrdinate = getOrdinate(relativeRow), colOrdinate = getOrdinate(relativeColumn);
        for (MovementDirection direction : MovementDirection.values()) {
            if (direction.column == colOrdinate && direction.row == rowOrdinate) {
                return direction;
            }
        } return STATIC;
    }

    private static int getOrdinate(int value) {
        return Integer.compare(value, 0);
    }
}
