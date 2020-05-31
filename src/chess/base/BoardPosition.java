package chess.base;

import chess.base.exceptions.InvalidMoveException;

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
        if (!isValidCoordinateNumber(row)) {
            throw new InvalidMoveException(row);
        }
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        if (!isValidCoordinateNumber(column)) {
            throw new InvalidMoveException(column);
        }
        this.column = column;
    }

    public static boolean isValidCoordinateNumber(int coordinateNumber){
        return (coordinateNumber >= 1 && coordinateNumber <= 8);
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
