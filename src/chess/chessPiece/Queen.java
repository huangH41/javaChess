package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.PawnMovement;

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

    /**
     * Check if queen moves in any linear direction by any block
     *
     * @param dstCol destination column
     * @param dstRow destination row
     * @return true if queen moves any linear directional by any block
     */
    private boolean isOmniDirectionalMove(int dstCol, int dstRow) {
        return PawnMovement.isDiagonalMovement(this, dstCol, dstRow)
                || PawnMovement.isHorizontalMovement(this, dstCol, dstRow)
                || PawnMovement.isVerticalMovement(this, dstCol, dstRow);
    }

    @Override
    protected void move(int dstCol, int dstRow) {
        if (Board.validatePosition(dstCol, dstRow) && isOmniDirectionalMove(dstCol, dstRow)) {
            // TODO do a recursive move to detect any obstacle
        }
    }

}
