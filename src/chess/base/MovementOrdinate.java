package chess.base;

public class MovementOrdinate {
    private final BoardPosition srcPosition, dstPosition;

    public MovementOrdinate(BoardPosition srcPosition, BoardPosition dstPosition) {
        this.srcPosition = srcPosition;
        this.dstPosition = dstPosition;
    }

    public MovementDirection getMovementDirection() {
        return MovementDirection.getMovementDirection(getRowDegreeOrdinate(), getColumnDegreeOrdinate());
    }

    public int getColumnDegreeOrdinate() {
        return getDegreeOrdinate(srcPosition.getColumn(), dstPosition.getColumn());
    }

    public int getRowDegreeOrdinate() {
        return getDegreeOrdinate(srcPosition.getRow(), dstPosition.getRow());
    }

    private int getDegreeOrdinate(int source, int target) {
        int relativeDistance = target - source;
        return Integer.compare(relativeDistance, 0);
    }
}
