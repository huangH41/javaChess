package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

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

    @Override
    protected void move(int dstRow, int dstCol) throws InvalidMoveException {
        if (Board.validatePosition(dstRow, dstCol) && PieceMovement.isOmniDirectionalMove(this, dstRow, dstCol)) {
            // TODO do a recursive move to detect any obstacle
        }
    }

}
