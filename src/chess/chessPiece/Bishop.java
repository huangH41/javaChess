package chess.chessPiece;

import chess.base.*;


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
    protected void move(int dstCol, int dstRow) {
        if (Board.validatePosition(dstCol, dstRow) && (PieceMovement.isDiagonalMovement(this, dstCol, dstRow))) {
            // TODO search for horizontal/vertical obstacle
            // if obstacle are same-colored king and not moved, performCastlingMove(king);
        }
    }


}
