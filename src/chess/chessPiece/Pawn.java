package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

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

    private boolean isRegularMovement(BoardPosition dstPosition) {
        return (PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 0 && isValidDirection(dstPosition));
    }

    private boolean isValidDirection(BoardPosition dstPosition) {
        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return dstPosition.getRow() >= this.getPosition().getRow();
        } else {
            return dstPosition.getRow() <= this.getPosition().getRow();
        }
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        return null;
    }

    /**
     * Generate coordinate of the plot that are being guarded by the pawn piece
     * @return
     */
    //TODO Really need to be refactored later
    protected Vector<BoardPosition> generateGuardedArea(){
        Vector<BoardPosition> guardedArea = new Vector<>();
        int row = (this.getChessColor() == ChessPieceColor.WHITE) ? this.getPosition().getRow() + 1 : this.getPosition().getRow() - 1;
        for(int i = 0, colMover = 1; i < 2; i++, colMover *= -1){
            int column = this.getPosition().getColumn() - colMover;
            if(column >= 1 && column <= 8){
                BoardPosition position = new BoardPosition(row, column);
                if(Board.isBoardValidPosition(position)){
                    guardedArea.add(position);
                }
            }
        }
        return guardedArea;
    }
}
