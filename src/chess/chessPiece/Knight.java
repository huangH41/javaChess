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
    public void move(BoardPosition dstPosition, Board board) {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            board.movePiece(this, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return (board.isOccupied(targetPosition) && isOpponent(board.getPiece(targetPosition)));
    }

    @Override
    public void markGuardedPlot(BoardPlot boardPlot) {

    }

    @Override
    public void unmarkGuardedPlot(BoardPlot boardPlot) {

    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return (isCapturable(board, dstPosition) || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isLetterLMovement(this, dstPosition));
    }
}
