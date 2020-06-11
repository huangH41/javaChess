package chess.base;

import chess.base.exceptions.IllegalNotationException;

/**
 * This class includes chess board position and notations
 */
public class BoardPosition {
    public static final int MIN_INDEX = 1, MAX_INDEX = 8;
    public static final int WHITE_SIDE = MIN_INDEX, BLACK_SIDE = MAX_INDEX;
    private int row, column;

    /**
     * Declare board position using defined row and column
     *
     * @param row    the row beginning from left white rook
     * @param column the column
     */
    public BoardPosition(int row, int column) {
        this.setPosition(row, column);
    }

    /**
     * Declare board position using defined chess coordinate notation [A-H][1-8]
     *
     * @param coordinateNotation chess coordinate notation with format [A-H][1-8] (A-H for column, 1-8 for row)
     */
    public BoardPosition(String coordinateNotation) {
        this.setPosition(coordinateNotation);
    }

    /**
     * Set position to a chess coordinate notation
     *
     * @param coordinateNotation chess coordinate notation with format [A-H][1-8] (A-H for column, 1-8 for row)
     */
    public void setPosition(String coordinateNotation) {
        if (coordinateNotation.matches("[A-H][1-8]")) {
            setColumn(coordinateNotation.codePointAt(0) - 'A' + 1);
            setRow(Integer.parseInt(String.valueOf(coordinateNotation.charAt(1))));
        } else {
            throw IllegalNotationException.notation(coordinateNotation);
        }
    }

    /**
     * Set position to new row and column
     *
     * @param row    new row position
     * @param column new column position
     */
    public void setPosition(int row, int column) {
        try {
            setRow(row);
            setColumn(column);
        } catch (IllegalNotationException ex) {
            throw new IllegalNotationException(row, column);
        }
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (!isValidCoordinateNumber(row)) {
            throw IllegalNotationException.row(row);
        }
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        if (!isValidCoordinateNumber(column)) {
            throw IllegalNotationException.column(column);
        }
        this.column = column;
    }

    public static boolean isValidCoordinateNumber(int coordinateNumber) {
        return (coordinateNumber >= 1 && coordinateNumber <= 8);
    }

    /**
     * Move chess piece relative to its direction and specified coordinate
     *
     * @param row number of row away from the current row
     * @param column number of col away from the current col
     * @param direction moving direction
     * @return
     */
    public BoardPosition moveBy(int row, int column, MovementDirection direction) {
        int newRow = this.row + (row * direction.getRowOrdinate());
        int newColumn = this.column + (column * direction.getColumnOrdinate());

        return new BoardPosition(newRow, newColumn);
    }

    /**
     * Outputs Board Position in Coordinate Chess Notation
     *
     * @return coordinate chess notation
     */
    @Override
    public String toString() {
        return String.format("%c%d", (char) ((column - 1) + 'A'), row);
    }

}
