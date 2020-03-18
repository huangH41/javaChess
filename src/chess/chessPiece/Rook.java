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
    protected void move(int dstRow, int dstCol) throws Exception {
        if (Board.isBoardValidPosition(dstRow, dstCol) && (PieceMovement.isHorizontalMovement(this, dstRow, dstCol)
                || PieceMovement.isVerticalMovement(this, dstRow, dstCol))
                && !PieceMovement.isDiagonalMovement(this, dstRow, dstCol)) {
            // TODO search for horizontal/vertical obstacle
            // if obstacle are same-colored king and not moved, performCastlingMove(king);
            if (PieceMovement.isHorizontalMovement(this, dstRow, dstCol)) {
                // TODO Check obstacle horizontally
                for (int i = 0; i < PieceMovement.getRelativeColDistance(this, dstCol); i++) {
                    // example: if (board[currRow][currCol+i] != null) { "do movement" }

                }
            } else if (PieceMovement.isVerticalMovement(this, dstRow, dstCol)) {
                // TODO Check obstacle vertically
                for (int i = 0; i < PieceMovement.getRelativeRowDistance(this, dstCol); i++) {
                    // example: if (board[currRow+i][currCol] != null) { "do movement" }

                }
            }
        }
    }
}
