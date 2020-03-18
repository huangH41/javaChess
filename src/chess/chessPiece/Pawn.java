package chess.chessPiece;

import chess.base.*;

public class Pawn extends ChessPiece {

    private int maxStep = 2;

    public Pawn(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.PAWN, chessColor, position);
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) throws Exception {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board ,dstPosition)) {

            if (isFirstMove()) {
                maxStep = 1;
                hasMoved();
            }

            board.movePiece(this, dstPosition);
        } else {
            throw new IllegalArgumentException("Invalid move!");
        }
    }

    private boolean isValidMove(Board board, BoardPosition dstPosition) {
        return isValidPieceMovement(dstPosition) && isValidMovePath(board, dstPosition) ? true : false;
    }

    private boolean isValidMovePath(Board board, BoardPosition dstPosition){
        return hasPieceObstacle(board, dstPosition);
    }

    private boolean isValidPieceMovement(BoardPosition dstPosition) {
        if(PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) <= maxStep
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 0) return true;

        return false;
    }

    public boolean hasPieceObstacle(Board board, BoardPosition dstPosition) {
        ChessPieceColor pieceColor = this.getChessColor();
        if(pieceColor == ChessPieceColor.WHITE){
            return isWhitePieceObstacle(board, dstPosition);
        } else {
            return isBlackPieceObstacle(board, dstPosition);
        }
    }

    //TODO Think other more relevan name
    private boolean isWhitePieceObstacle(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        for(int row = currentPosition.getRow() - 1; row >= dstPosition.getRow(); row--){
            currentPosition.setRow(row);
            if(!board.isUnoccupied(currentPosition)) return false;
        }
        return true;
    }

    private boolean isBlackPieceObstacle(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        for(int row = currentPosition.getRow() + 1; row <= dstPosition.getRow(); row++){
            currentPosition.setRow(row);
            if(!board.isUnoccupied(currentPosition)) return false;
        }
        return true;
    }

    @Override
    public void capture(ChessPiece[][] board, BoardPosition targetPosition){

    }

    public ChessPiece promote(ChessPieceRank upgradedRank) {
        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return defineWhitePawn(upgradedRank, this.getPosition());
        } else return defineBlackPawn(upgradedRank, this.getPosition());
    }

}
