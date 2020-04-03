package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

public class Pawn extends ChessPiece {

    private int maxStep = 2;

    public Pawn(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.PAWN, chessColor, position);
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            if (!isFirstMove()) hasMoved();
            board.movePiece(this, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (isRegularMovement(dstPosition) || isCrossMovement(dstPosition)) && PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) <= maxStep;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return isCapturable(board, dstPosition) || (isRegularMovement(dstPosition) && !board.isOccupied(dstPosition));
    }

    @Override
    public boolean isCapturable(Board board, BoardPosition targetPosition) {
        return board.isOccupied(targetPosition) && isOpponent(board.getPiece(targetPosition)) && isCrossMovement(targetPosition);
    }

    @Override
    public void hasMoved(){
        firstMove = true;
        maxStep = 1;
    }

    public ChessPiece promote(ChessPieceRank upgradedRank) {
        if (upgradedRank == ChessPieceRank.PAWN || upgradedRank == ChessPieceRank.KING) {
            throw new IllegalStateException("Can not promote pawn to king or pawn itself!");
        }

        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return defineWhitePawn(upgradedRank, this.getPosition());
        } else return defineBlackPawn(upgradedRank, this.getPosition());
    }

    /**
     * Return true if the pawn movement are went to capture opponent pieces
     *
     * @param dstPosition target position of pawn
     * @return true if pawn movement decided to capture opponent pieces
     */
    private boolean isCrossMovement(BoardPosition dstPosition) {
        return (PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1);
    }

    private boolean isRegularMovement(BoardPosition dstPosition){
        return (PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 0 && isValidDirection(dstPosition));
    }

    private boolean isValidDirection(BoardPosition dstPostiion){
        if(this.getChessColor() == ChessPieceColor.WHITE){
            return dstPostiion.getRow() < this.getPosition().getRow() ? false : true;
        } else {
            return dstPostiion.getRow() > this.getPosition().getRow() ? false : true;
        }
    }

    private void markGuardedPlot(BoardPlot[][] boardPlot){

    }
}
