package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;


public class Bishop extends ChessPiece {

    /**
     * Define Bishop with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of bishop
     */
    public Bishop(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.BISHOP, chessColor, position);
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
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        guardedPositions.addAll(generateDiagonalGuardedArea(board, currentPosition));

        return guardedPositions;
    }

    //TODO generate lest right move function is duplicate with generate top bottom move
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
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return true;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        int colMagnitude = pointer.getColumnDegreeOrdinate();
        int rowMagnitude = pointer.getRowDegreeOrdinate();

        for (int row = currentPosition.getRow() + rowMagnitude, col = currentPosition.getColumn() + colMagnitude;
             row != dstPosition.getRow(); row += rowMagnitude, col += colMagnitude) {
            currentPosition.setPosition(row, col);
            if (board.isOccupied(currentPosition)) return false;
        }
        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isDiagonalMovement(this, dstPosition));
    }


}
