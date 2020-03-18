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
    protected void move(int dstRow, int dstCol) {
        if (Board.isBoardValidPosition(dstCol, dstRow) && isLetterLMovement(dstCol, dstRow)) {
            int relativeCol = dstCol - this.getPosition().getColumn();
            int relativeRow = dstRow - this.getPosition().getRow();

            // example: if (board[currRow+relativeRow][currCol+relativeCol] != null) { "do movement" }
            this.setPosition(new BoardPosition(dstRow, dstCol));
        } else {
            throw new InvalidMoveException("Knight");
        }
    }


}
