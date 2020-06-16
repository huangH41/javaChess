package chess.base.exceptions;

/**
 * This exception thrown if any invalid movement/position notation occurred
 * systematically (for example an index out of bounds or negative coordinates)
 * <p>
 * REMEMBER: Any invalid chess notations will thrown as IllegalNotationException!
 */
public class IllegalNotationException extends IllegalArgumentException {
    /**
     * The coordinate outside chess index (array index from 0 to 7)
     *
     * @param rowIndex index at row position
     * @param colIndex index at column position
     */
    public IllegalNotationException(int rowIndex, int colIndex) {
        super(String.format("Illegal position notation! (value: [row %d, col %d])", rowIndex, colIndex));
    }

    /**
     * The row coordinate outside chess index (array index from 0 to 7)
     *
     * @param row row index at position
     */
    public static IllegalNotationException row(int row) {
        return new IllegalNotationException(String.format("Illegal row position! (value: %d)", row));
    }

    /**
     * The column coordinate outside chess index (array index from 0 to 7)
     *
     * @param column column index at position
     */
    public static IllegalNotationException column(int column) {
        return new IllegalNotationException(String.format("Illegal column position! (value: %d)", column));
    }

    /**
     * The notation coordinate which do not follow notation format [A-H][1-8] (A-H for column, 1-8 for row)
     *
     * @param notation notation which invalids notation formatting
     */
    public static IllegalNotationException notation(String notation) {
        return new IllegalNotationException(String.format("Invalid notation: must be [A-H][1-8]! (notation value: %s)", notation));
    }

    /**
     * The user inputted notation coordinate do not follow notation format [A-H][1-8] (A-H for column, 1-8 for row)
     *
     */
    public static IllegalNotationException userInputMismatch() {
        return IllegalNotationException.userInputMismatch(false);
    }

    /**
     * The user inputted notation coordinate do not follow notation format [A-H][1-8] (A-H for column, 1-8 for row)
     *
     */
    public static IllegalNotationException userInputMismatch(boolean promotion) {
        return new IllegalNotationException("Invalid notation: must be [A-H][1-8]! " +
                (promotion ? "(with additional R/N/K/Q for promotion if pawn reaches enemy base)" : ""));

    }

    /**
     * The piece rank notation which not contains either P, R, N, B, Q, or K
     */
    public static IllegalNotationException rank() {
        return new IllegalNotationException("Invalid notation: Rank initial must be P, R, N, B, Q, or K (uppercase)! (notation value: %s)");
    }

    /**
     * The subject or detailed message that leads to invalid movement notation
     *
     * @param s detail message about invalid moves
     */
    public IllegalNotationException(String s) {
        super(s);
    }
}
