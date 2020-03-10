package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

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
    public void move(int dstRow, int dstCol) throws InvalidMoveException {
        if (Board.validatePosition(dstRow, dstCol)
                && PieceMovement.getRelativeRowDistance(this, dstRow) <= maxStep
                && PieceMovement.getRelativeColDistance(this, dstCol) == 0) {
            // TODO do a logic that the targeted position isn't have a piece (even opponent or not)

            this.setPosition(new BoardPosition(dstRow, dstCol));
        } else {
            throw new InvalidMoveException(this, dstRow, dstCol);
        }

        if (isFirstMove()) {
            maxStep = 1;
            setMovedYet();
        }
    }

    /**
     * Upgrades the pawn to rook, knight, bishop, or queen
     *
     * @param upgradedRank the new rank (Rook, Knight, Bishop, or Queen)
     * @return upgraded pawn as rook, knight, bishop, or queen
     */
    public ChessPiece promote(ChessPieceRank upgradedRank) {
        if (upgradedRank == ChessPieceRank.PAWN || upgradedRank == ChessPieceRank.KING) {
            throw new IllegalStateException("Can not promote pawn to king or pawn itself!");
        }

        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return defineWhitePawn(upgradedRank, this.getPosition());
        } else return defineBlackPawn(upgradedRank, this.getPosition());
    }

}
