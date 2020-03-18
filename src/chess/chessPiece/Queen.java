package chess.chessPiece;

import chess.base.*;

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
    private boolean isOmniDirectionalMove(int dstRow, int dstCol) {
        return PieceMovement.isDiagonalMovement(this, dstRow, dstCol)
                || PieceMovement.isHorizontalMovement(this, dstRow, dstCol)
                || PieceMovement.isVerticalMovement(this, dstRow, dstCol);
    }

    @Override
    protected void move(int dstRow, int dstCol) {
        if (Board.isBoardValidPosition(dstRow, dstCol) && isOmniDirectionalMove(dstRow, dstCol)) {
            // TODO do a recursive move to detect any obstacle
        }
    }

}
