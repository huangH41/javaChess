package chess.base;

import chess.base.exceptions.InvalidMoveException;

/**
 * This class includes chess board position and notations
 */
public class BoardPosition {
    public static final int MIN_INDEX = 0, MAX_INDEX = 7;
    public static final int WHITE_SIDE = MIN_INDEX, BLACK_SIDE = MAX_INDEX;
    private int row, column;

    /**
     * Declare board position using defined row and column
     *
     * @param row    the row
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
            setRow(coordinateNotation.codePointAt(0) - 'A' + 1);
            setColumn(Integer.parseInt(String.valueOf(coordinateNotation.charAt(1))));
        } else {
            throw new InvalidMoveException("Invalid notation: must be [A-H][1-8]");
        }
    }

    /**
     * Set position to new row and column
     *
     * @param row    new row position
     * @param column new column position
     */
    public void setPosition(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row < 1 || row > 8) {
            throw new InvalidMoveException(row);
        }
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        if (column < 1 || column > 8) {
            throw new InvalidMoveException(column);
        }
        this.column = column;
    }

    /**
     * Outputs Board Position in Coordinate Chess Notation
     *
     * @return coordinate chess notation
     */
    @Override
    public String toString() {
        return String.format("%c%d", (char) (column + 'A'), row);
    }

}
