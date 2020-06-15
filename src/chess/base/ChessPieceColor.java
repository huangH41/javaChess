package chess.base;

public enum ChessPieceColor {
    WHITE(BoardPosition.WHITE_SIDE, MovementDirection.UP), BLACK(BoardPosition.BLACK_SIDE, MovementDirection.DOWN);

    private final int startPosition;
    private final MovementDirection direction;

    ChessPieceColor(int startPosition, MovementDirection direction) {
        this.startPosition = startPosition;
        this.direction = direction;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public MovementDirection getMovementDirection() {
        return direction;
    }

    public int getMovementDirectionOrdinate() {
        return direction.getRowOrdinate();
    }

    public String toString() {
        return this.name();
    }
}
