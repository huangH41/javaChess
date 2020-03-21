package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;


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
    protected void move(BoardPosition dstPosition, Board board) {
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

        for (int row = currentPosition.getRow() + magnitude, col = currentPosition.getColumn() + magnitude;
             row != dstPosition.getRow(); row += magnitude, col += magnitude) {
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
