package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;

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

    /**
     * Check if knight moves in L-shaped style direction at any angle
     *
     * @param dstCol destination column
     * @param dstRow destination row
     * @return true if queen moves in L-shaped style direction at any angle
     */
    private boolean isLetterLMovement(int dstCol, int dstRow) {
        int relativeCol = dstCol - this.getPosition().getColumn();
        int relativeRow = dstRow - this.getPosition().getRow();
        return (Math.abs(relativeCol) == 2 && Math.abs(relativeRow) == 1) || (Math.abs(relativeCol) == 1 && Math.abs(relativeRow) == 2);
    }

    @Override
    protected void move(int dstCol, int dstRow) {
        if (Board.validatePosition(dstCol, dstRow) && isLetterLMovement(dstCol, dstRow)) {
            this.setPosition(new BoardPosition(dstCol, dstRow));
        }
    }


}
