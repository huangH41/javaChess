package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

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
            board.movePiece(this, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected void capture(Board board, BoardPosition targetPosition) {

    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        int magnitude = PieceMovement.doSingleRelativeColumnMovement(this, currentPosition);

        if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            for (int row = currentPosition.getRow() + magnitude; row != dstPosition.getRow(); row += magnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
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
        return (PieceMovement.isHorizontalMovement(this, dstPosition)
                || PieceMovement.isVerticalMovement(this, dstPosition))
                && !PieceMovement.isDiagonalMovement(this, dstPosition);
    }
}
