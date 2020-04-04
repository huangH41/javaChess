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
            board.movePiece(this, dstPosition);
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
        return null;
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea() {
        return null;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        int colMagnitude = PieceMovement.doSingleRelativeColumnMovement(this, dstPosition);
        int rowMagnitude = PieceMovement.doSingleRelativeRowMovement(this, dstPosition);

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
