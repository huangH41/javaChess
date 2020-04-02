package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

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
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        int magnitude = PieceMovement.doSingleRelativeColumnMovement(this, currentPosition);

        if (PieceMovement.isDiagonalMovement(this, dstPosition)) {
            System.out.println("diagnonally");
            for (int row = currentPosition.getRow() + magnitude, col = currentPosition.getColumn() + magnitude;
                 row != dstPosition.getRow(); row += magnitude, col += magnitude) {
                currentPosition.setPosition(row, col);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            System.out.println("vertically");
            for (int row = currentPosition.getRow() + magnitude; row != dstPosition.getRow(); row += magnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            System.out.println("horizontally");
            for (int col = currentPosition.getColumn() + magnitude; col != dstPosition.getColumn(); col += magnitude) {
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
