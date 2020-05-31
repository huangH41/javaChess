package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

public class Queen extends ChessPiece {

    /**
     * Define Queen with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of queen
     */
    public Queen(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.QUEEN, chessColor, position);
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
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        guardedPositions.addAll(generateHorizontalGuardedArea(board, currentPosition));
        guardedPositions.addAll(generateVerticalGuardedArea(board, currentPosition));
        guardedPositions.addAll(generateDiagonalGuardedArea(board, currentPosition));

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

    private Vector<BoardPosition> generateDiagonalGuardedArea(Board board, BoardPosition currentPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        if (currentPosition.getColumn() < 8 || currentPosition.getRow() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.UP_RIGHT));
        } if (currentPosition.getColumn() > 1 || currentPosition.getRow() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.UP_LEFT));
        } if (currentPosition.getColumn() < 8 || currentPosition.getRow() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.DOWN_RIGHT));
        } if (currentPosition.getColumn() > 1 || currentPosition.getRow() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.DOWN_LEFT));
        }
        return guardedPositions;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        int colMagnitude = pointer.getColumnDegreeOrdinate();
        int rowMagnitude = pointer.getRowDegreeOrdinate();

        if (PieceMovement.isDiagonalMovement(this, dstPosition)) {
            for (int row = currentPosition.getRow() + rowMagnitude, col = currentPosition.getColumn() + colMagnitude;
                 row != dstPosition.getRow(); row += rowMagnitude, col += colMagnitude) {
                currentPosition.setPosition(row, col);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            for (int row = currentPosition.getRow() + rowMagnitude; row != dstPosition.getRow(); row += rowMagnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            for (int col = currentPosition.getColumn() + colMagnitude; col != dstPosition.getColumn(); col += colMagnitude) {
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
        return (PieceMovement.isAnyDirectionalMove(this, dstPosition));
    }

}
