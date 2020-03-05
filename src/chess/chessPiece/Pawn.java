package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.PieceMovement;
import chess.base.ChessPieceColor;

public class Pawn extends ChessPiece {
    /**
     * Maximum step for Pawn for first move is 2, else is 1
     */
    private int maxStep = 2;

    /**
     * Define Pawn with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of pawn
     */
    public Pawn(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.PAWN, chessColor, position);
    }

    @Override
    public void move(int dstCol, int dstRow) {
        if (!isFirstMove()) {
            maxStep = 1;
            setMovedYet();
        }

        if (Board.validatePosition(dstCol, dstRow)
                && PieceMovement.getRelativeColDistance(this, dstCol) == 0
                && PieceMovement.getRelativeRowDistance(this, dstRow) <= maxStep) {
            this.setPosition(new BoardPosition(dstCol, dstRow));
        } else {
            throw new IllegalArgumentException("Invalid move!");
        }
    }

}
