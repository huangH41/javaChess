package chess.base.exceptions;

import chess.chessPiece.ChessPiece;

/**
 * This exception thrown if any invalid position occurred
 * systematically (for example an index out of bounds or negative coordinates)
 * <p>
 * REMEMBER: Any invalid moves/chess notations will thrown as InvalidMoveException!
 */
public class InvalidMoveException extends IndexOutOfBoundsException {
    /**
     * Which piece caused to throw invalid move?
     *
     * @param causer the piece caused invalid move
     */
    public InvalidMoveException(ChessPiece causer) {
        super(String.format("Invalid move: %s moved out of bound!", causer.toString()));
    }

    /**
     * Which piece caused to throw invalid move with specified coordinates?
     *
     * @param causer   the piece caused invalid move
     * @param rowIndex index at row position
     * @param colIndex index at column position
     */
    public InvalidMoveException(ChessPiece causer, int rowIndex, int colIndex) {
        super(String.format("Invalid move: %s moved out of bound! (value: [row %d, col %d])",
                causer.toString(), rowIndex, colIndex));
    }

    /**
     * The coordinate outside chess index (array index from 0 to 7)
     *
     * @param rowIndex index at row position
     * @param colIndex index at column position
     */
    public InvalidMoveException(int rowIndex, int colIndex) {
        super(String.format("Illegal position! (value: [row %d, col %d])", rowIndex, colIndex));
    }

    /**
     * The coordinate outside chess index (array index from 0 to 7)
     *
     * @param index index at position
     */
    public InvalidMoveException(int index) {
        super(String.format("Illegal position! (value: %d)", index));
    }

    /**
     * The subject or detailed message that leads to invalid move
     *
     * @param s detail message about invalid moves
     */
    public InvalidMoveException(String s) {
        super(s);
    }
}
