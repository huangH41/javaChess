package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

public class King extends ChessPiece {

    private KingCheckState checkState = KingCheckState.SAFE;

    /**
     * Define King with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of king
     */
    public King(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.KING, chessColor, position);
    }

    /**
     * Check if king moves in any direction by one block
     *
     * @param dstPosition destination position
     * @return true if king moves any-directional by one block
     */
    private boolean isSingleAnyDirectionalMove(BoardPosition dstPosition) {
        return PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1
                || PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1;
    }

    // TODO buat method untuk cek status "CHECK" dan "CHECKMATE"
    public void verifyCheckState() {
        // TODO: Coba lu kerjain method skak.. @HuangH41
        // if any movement caused this to check or checkmate

    }

    public void setCheck(KingCheckState checkState) {
        this.checkState = checkState;
    }

    public KingCheckState isChecked() {
        return checkState;
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
        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (isSingleAnyDirectionalMove(dstPosition));
    }

    public enum KingCheckState {
        SAFE, CHECK, CHECKMATE
    }

}
