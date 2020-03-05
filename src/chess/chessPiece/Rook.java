package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.PieceMovement;
import chess.base.ChessPieceColor;

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

    // TODO Buat logic castling
    public void performCastlingMove(King k) {

    }

    @Override
    protected void move(int dstCol, int dstRow) {
        if (Board.validatePosition(dstCol, dstRow) && (PieceMovement.isHorizontalMovement(this, dstCol, dstRow)
                || PieceMovement.isVerticalMovement(this, dstCol, dstRow))) {
            // TODO search for horizontal/vertical obstacle
            // if obstacle are same-colored king and not moved, performCastlingMove(king);
        }
    }
}
