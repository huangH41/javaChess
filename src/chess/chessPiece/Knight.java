package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

public class Knight extends ChessPiece {

    /**
     * Define Knight with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of knight
     */
    public Knight(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.KNIGHT, chessColor, position);
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
        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isLetterLMovement(this, dstPosition));
    }


}
