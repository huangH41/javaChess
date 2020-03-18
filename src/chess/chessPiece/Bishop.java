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
    protected void move(int dstRow, int dstCol) throws Exception {
        if (Board.isBoardValidPosition(dstRow, dstCol) && (PieceMovement.isDiagonalMovement(this, dstRow, dstCol))) {
            // TODO Check obstacle diagonally
            for (int i = 0; i < PieceMovement.getRelativeColDistance(this, dstCol); i++) {
                // example: if (board[currRow+i][currCol+i] != null) { "do movement" }

            }
        }
    }


}
