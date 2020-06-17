package chess.base.exceptions;

import chess.base.BoardPosition;
import chess.base.ChessPieceProperties;

/**
 * This exception thrown if any invalid movement/illegal position occurred
 * systematically (for example an index out of bounds or negative coordinates)
 * <p>
 * REMEMBER: Any invalid moves will thrown as InvalidMoveException!
 */
public class InvalidMoveException extends IndexOutOfBoundsException {
    /**
     * Which piece caused to throw illegal move?
     *
     * @param causer the piece caused illegal move
     */
    public InvalidMoveException(ChessPieceProperties causer) {
        super(String.format("Invalid move: %s performed illegal move or moved out of bound!", causer.toString()));
    }

    /**
     * Which piece caused to throw invalid move with specified coordinates?
     *
     * @param causer   the piece caused invalid move
     * @param position the target position of the causer
     */
    public InvalidMoveException(ChessPieceProperties causer, BoardPosition position) {
        this(causer, position.getRow(), position.getColumn());
    }

    /**
     * Which piece caused to throw illegal move with specified coordinates?
     *
     * @param causer   the piece caused illegal move
     * @param rowIndex index at row position
     * @param colIndex index at column position
     */
    public InvalidMoveException(ChessPieceProperties causer, int rowIndex, int colIndex) {
        super(String.format("Invalid move: %s performed illegal move or moved out of bound! (value: [row %d, col %d])",
                causer.toString(), rowIndex, colIndex));
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
