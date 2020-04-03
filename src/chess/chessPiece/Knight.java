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
    public void move(BoardPosition dstPosition, Board board) {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            board.movePiece(this, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return (board.isOccupied(targetPosition) && isOpponent(board.getPiece(targetPosition)));
    }

    @Override
    public void markGuardedPlot(BoardPlot boardPlot) {
        for (BoardPosition guardedPosition: generateGuardedArea()) {
            if(guardedPosition != null && Board.isBoardValidPosition(guardedPosition)){
                setGuardedByColor(boardPlot, guardedPosition);
            }
        }
    }

    @Override
    public void unmarkGuardedPlot(BoardPlot boardPlot) {

    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return (isCapturable(board, dstPosition) || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isLetterLMovement(this, dstPosition));
    }

    private void setGuardedByColor(BoardPlot boardPlot, BoardPosition guardedPosition){
        Plot plot = boardPlot.getPlot(guardedPosition);
        if(this.getChessColor() == ChessPieceColor.WHITE){
            plot.setGuardedByWhite(true);
            plot.addGuardingWhitePiece();
        } else {
            plot.setGuardedByBlack(true);
            plot.addGuardingBlackPiece();
        }
    }

    private BoardPosition[] generateGuardedArea(){
        BoardPosition[] guardedPositions = new BoardPosition[8];
        //generate 1st movement variant (Top & Bottom)
        System.arraycopy(generateTopBottomDirectionMove(this.getPosition()),0, guardedPositions, 0, 4);
        System.arraycopy(generateLeftRightDirectionMove(this.getPosition()),0, guardedPositions, 4, 4);
        return  guardedPositions;
    }

    //TODO Method below maybe is a duplicate code, refactor later
    private BoardPosition[] generateTopBottomDirectionMove(BoardPosition currentPosition){
        BoardPosition[] guardedPosition = new BoardPosition[4];
        int row, col, colMover, x;
        for(x = 0, colMover = 1; x < 4; x++, colMover *= -1){
            row = (x < 2) ? currentPosition.getRow() + 2 : currentPosition.getRow() - 2;
            if(BoardPosition.isValidCoordinateNumber(row)){
                col = currentPosition.getColumn() - colMover;
                guardedPosition[x] = (BoardPosition.isValidCoordinateNumber(col)) ? new BoardPosition(row, col) : null;
            }
        }
        return guardedPosition;
    }

    private BoardPosition[] generateLeftRightDirectionMove(BoardPosition currentPosition){
        BoardPosition[] guardedPosition = new BoardPosition[4];
        int row, col, rowMover, x;
        for(x = 0, rowMover = 1; x < 4; x++, rowMover *= -1){
            col = (x < 2) ? currentPosition.getColumn() - 2 : currentPosition.getColumn() + 2;
            if(BoardPosition.isValidCoordinateNumber(col)){
                row = currentPosition.getRow() - rowMover;
                guardedPosition[x] = (BoardPosition.isValidCoordinateNumber(row)) ? new BoardPosition(row, col) : null;
            }
        }
        return guardedPosition;
    }

}
