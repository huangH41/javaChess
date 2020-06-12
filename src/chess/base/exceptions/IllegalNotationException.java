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
     * The coordinate outside chess index (array index from 0 to 7)
     *
     * @param index index at position
     */
    public IllegalNotationException(int index) {
        super(String.format("Illegal position notation! (value: %d)", index));
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
     * The subject or detailed message that leads to invalid movement notation
     *
     * @param s detail message about invalid moves
     */
    public IllegalNotationException(String s) {
        super(s);
    }
}