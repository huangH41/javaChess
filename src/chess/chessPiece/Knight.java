package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;

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
     * @param dstRow destination row
     * @param dstCol destination column
     * @return true if queen moves in L-shaped style direction at any angle
     */
    private boolean isLetterLMovement(int dstRow, int dstCol) {
        int relativeCol = dstCol - this.getPosition().getColumn();
        int relativeRow = dstRow - this.getPosition().getRow();
        return (Math.abs(relativeCol) == 2 && Math.abs(relativeRow) == 1) || (Math.abs(relativeCol) == 1 && Math.abs(relativeRow) == 2);
    }

    @Override
    protected void move(int dstRow, int dstCol) {
        if (Board.isBoardValidPosition(dstCol, dstRow) && isLetterLMovement(dstCol, dstRow)) {
            int relativeCol = dstCol - this.getPosition().getColumn();
            int relativeRow = dstRow - this.getPosition().getRow();

            // example: if (board[currRow+relativeRow][currCol+relativeCol] != null) { "do movement" }
            this.setPosition(new BoardPosition(dstCol, dstRow));
        }
    }


}
