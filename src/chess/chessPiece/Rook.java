package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

public class Rook extends ChessPiece {

    /**
     * Define Rook with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of rook
     */
    public Rook(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.ROOK, chessColor, position);
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            movePiece(board, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return true;
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        guardedPositions.addAll(generateHorizontalGuardedArea(board, currentPosition));
        guardedPositions.addAll(generateVerticalGuardedArea(board, currentPosition));

        return guardedPositions;
    }

    private Vector<BoardPosition> generateHorizontalGuardedArea(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        if (currentPosition.getColumn() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.RIGHT));
        } if (currentPosition.getColumn() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.LEFT));
        }
        return guardedPositions;
    }

    private Vector<BoardPosition> generateVerticalGuardedArea(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        if (currentPosition.getRow() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.UP));
        } if (currentPosition.getRow() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.DOWN));
        }
        return guardedPositions;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            int verticalMagnitude = pointer.getRowDegreeOrdinate();
            for (int row = currentPosition.getRow() + verticalMagnitude; row != dstPosition.getRow(); row += verticalMagnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            int horizontalMagnitude = pointer.getColumnDegreeOrdinate();
            for (int col = currentPosition.getColumn() + horizontalMagnitude; col != dstPosition.getColumn(); col += horizontalMagnitude) {
                currentPosition.setColumn(col);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else {
            return false;
        }

        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isHorizontalMovement(this, dstPosition)
                || PieceMovement.isVerticalMovement(this, dstPosition))
                && !PieceMovement.isDiagonalMovement(this, dstPosition);
    }
}
