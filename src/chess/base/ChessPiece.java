package chess.base;

import chess.base.exceptions.InvalidMoveException;
import java.util.Vector;

public abstract class ChessPiece {
    private final ChessPieceRank pieceRank;
    private final ChessPieceColor chessColor;
    protected boolean hasMovedOnce = false;
    private BoardPosition position;
    private int firstMoveAt = -1;

    /**
     * Define chess piece with status, color (either black or white), and position on the chess
     *
     * @param pieceRank  the chess piece name
     * @param chessColor the chess piece color as player or opponent color (either black or white)
     * @param position   the chess piece position (define new BoardPosition in row and column)
     */
    public ChessPiece(ChessPieceRank pieceRank, ChessPieceColor chessColor, BoardPosition position) {
        this.pieceRank = pieceRank;
        this.chessColor = chessColor;
        this.position = position;
    }

    public ChessPieceProperties getProperties() {
        return new ChessPieceProperties(chessColor, pieceRank, position);
    }

    /**
     * Return this object as String by format: "Color, Rank, Chess-Position-Notation"
     *
     * @return Piece information with format "Color, Rank, Chess-Position-Notation"
     */
    @Override
    public String toString() {
        return getProperties().toString();
    }

    public ChessPieceRank getPieceRank() {
        return pieceRank;
    }

    public ChessPieceColor getChessColor() {
        return chessColor;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition newPosition) {
        this.position = newPosition;
    }

    public int getFirstMoveAt() {
        return firstMoveAt;
    }

    protected void setFirstMoveAt(int firstMoveAt) {
        this.firstMoveAt = firstMoveAt;
    }

    public boolean hasMovedOnce() {
        return hasMovedOnce;
    }

    public void alreadyMovedOnce() {
        hasMovedOnce = true;
    }

    public void setFirstMoveCounter(int turn) {
        alreadyMovedOnce();
        setFirstMoveAt(turn);
    }

    public boolean isOpponent(ChessPiece counterPiece) {
        return counterPiece.getChessColor() != this.getChessColor();
    }

    /**
     * Verify movement of the piece. This will verify piece obstacle <code>isValidMovePath()</code>,
     * verify piece movement style <code>isValidPieceMovement()</code> and verify king safety after
     * movement <code>isKingSafeAfterMove</code>
     *
     * @param board       board to check the validity
     * @param dstPosition the new position of that piece
     * @return is able to move and has no obstacles
     */
    protected boolean isValidMove(Board board, BoardPosition dstPosition) {
        boolean validMove = isValidMovePath(board, dstPosition) && isValidPieceMovement(dstPosition);

        if (!KingCheckState.isKingUnderCheckState(board, board.getCurrentColor())) {
            return validMove && KingCheckState.isKingSafeAfterMovement(board, this.getPosition(), dstPosition);
        } else if (KingCheckState.isKingSafeAfterMovement(board, this.getPosition(), dstPosition)) {
            return false;
        }

        throw new InvalidMoveException(String.format("Invalid Move! %s is checked", board.getCurrentColor()));
    }

    protected void movePiece(Board board, BoardPosition dstPosition) {
        this.unmarkGuardedPlot(board.getBoardPlot(), board);
        board.movePiece(this, dstPosition);
        this.markGuardedPlot(board.getBoardPlot(), board);
        setMoveCounter(board);
    }

    private void setMoveCounter(Board board) {
        if (!hasMovedOnce()) {
            setFirstMoveCounter(board.getNumOfTurns());
        }
        board.nextTurn();
    }

    /**
     * Verify movement path of the piece. Do recursive check of the obstacle to make sure that
     * every movement steps are valid and not obstructed by other piece.
     *
     * @param board       board to check the validity
     * @param dstPosition the new position of that piece
     * @return has no obstacle after some recursive
     */
    protected abstract boolean isValidMovePath(Board board, BoardPosition dstPosition);

    /**
     * Verify piece movement style to make sure the movement are valid and not out-of-bound
     *
     * @param dstPosition the new position of that piece
     * @return correct movement style of the piece
     */
    protected abstract boolean isValidPieceMovement(BoardPosition dstPosition);

    /**
     * Allow the capture and voids of a piece if it is an opponent. For en-passant, pawn requires to move
     * diagonally at empty board behind the opponent piece to capture them.
     *
     * @param board          board to execute the piece capturing stage
     * @param targetPosition target piece position to capture and void
     */
    protected abstract boolean isCapturable(Board board, BoardPosition targetPosition);

    /**
     * Declare an abstract method that every class extended from this class to perform a movement.
     * Throws exception if any invalid move or obstacle occurred.
     *
     * @param dstPosition destination position
     */
    public abstract void move(BoardPosition dstPosition, Board board);

    /**
     * Generates all possible guarded positions from that piece
     *
     * @param board to plot guarded position
     * @return generated positions at each directions
     */
    protected abstract Vector<BoardPosition> generateGuardedArea(Board board);

    /**
     * Set the targeted position plot status into guarded
     *
     * @param boardPlot to set the guarded status in a targeted position
     */
    public void markGuardedPlot(BoardPlot boardPlot, Board board) {
        Vector<BoardPosition> allGuardedArea = generateGuardedArea(board);
        if (allGuardedArea != null) {
            for (BoardPosition guardedPosition : allGuardedArea) {
                if (guardedPosition != null && Board.isBoardValidPosition(guardedPosition)) {
                    BoardPlot.setGuardedByColor(boardPlot, guardedPosition, this.chessColor);
                }
            }
        }
    }

    /**
     * Unset the targeted position plot status from guarded area
     *
     * @param boardPlot to unset the guarded status from targeted position
     */
    public void unmarkGuardedPlot(BoardPlot boardPlot, Board board) {
        Vector<BoardPosition> allGuardedArea = generateGuardedArea(board);
        if (allGuardedArea != null) {
            for (BoardPosition guardedPosition : allGuardedArea) {
                if (guardedPosition != null && Board.isBoardValidPosition(guardedPosition)) {
                    BoardPlot.unsetGuardedByColor(boardPlot, guardedPosition, this.chessColor);
                }
            }
        }
    }

    /**
     * Generates all possible guarded positions directionally
     *
     * @param board           board to plot guarded position
     * @param currentPosition current piece position
     * @param direction       direction of guarded position generation
     * @return generated positions at a direction
     */
    protected Vector<BoardPosition> generatePossibleGuardedPositions(Board board, BoardPosition currentPosition, MovementDirection direction) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        int targetRow = (direction.getRowOrdinate() != 0) ? ((direction.getRowOrdinate() > 0) ? 8 : 1) : currentPosition.getRow();
        int targetCol = (direction.getColumnOrdinate() != 0) ? ((direction.getColumnOrdinate() > 0) ? 8 : 1) : currentPosition.getColumn();

        BoardPosition targetPosition = new BoardPosition(targetRow, targetCol);
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, targetPosition);

        int rowDirection = pointer.getRowDegreeOrdinate();
        int colDirection = pointer.getColumnDegreeOrdinate();

        for (int row = currentPosition.getRow() + rowDirection, col = currentPosition.getColumn() + colDirection;
             ((rowDirection < 0) ? row <= 8 : row >= 1) || ((colDirection < 0) ? col <= 8 : col >= 1);
             row += (rowDirection), col += (colDirection)) {
            if (row <= 0 || row > 8 || col <= 0 || col > 8) {
                break;
            }

            BoardPosition position = new BoardPosition(row, col);
            guardedPositions.add(position);

            if (board.isOccupied(position)) {
                if(isKingToIgnore(board, position)){
                    continue;
                } else {
                    break;
                }
            }
        }
        return guardedPositions;
    }

    private boolean isKingToIgnore(Board board, BoardPosition dstPosition) {
        return board.getPiece(dstPosition).getPieceRank() == ChessPieceRank.KING;
    }
}
