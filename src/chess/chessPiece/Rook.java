package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

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
        return true;
    }


    public void markGuardedPlot(BoardPlot boardPlot, Board board) {
        for (BoardPosition guardedPosition: generateGuardedArea(board)) {
            if(guardedPosition != null && Board.isBoardValidPosition(guardedPosition)){
                BoardPlot.setGuardedByColor(boardPlot, guardedPosition, this.getChessColor());
            }
        }
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        guardedPositions.addAll(generateTopBottomDirectionMove(board, currentPosition));
        guardedPositions.addAll(generateLeftRightDirectionMove(board, currentPosition));

        return guardedPositions;
    }

    //TODO generate lest right move function is duplicate with generate top bottom move
    private Vector<BoardPosition> generateLeftRightDirectionMove(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        int magnitude;

        //TODO Duplicate code, Need to get refactored later
        if(currentPosition.getColumn() < 8){
            magnitude = PieceMovement.doSingleRelativeColumnMovement(this, new BoardPosition(currentPosition.getRow(),8));
            for (int col = currentPosition.getColumn() + magnitude; col <= 8; col += magnitude) {
                if (board.isOccupied(currentPosition)){
                    guardedPositions.add(new BoardPosition(currentPosition.getRow(), col));
                    break;
                } else {
                    guardedPositions.add(new BoardPosition(currentPosition.getRow(), col));
                }
            }
        }

        if(currentPosition.getColumn() > 1){
            magnitude = PieceMovement.doSingleRelativeColumnMovement(this, new BoardPosition(currentPosition.getRow(),1));
            for (int col = currentPosition.getColumn() + magnitude; col >= 1; col += magnitude) {
                if (board.isOccupied(currentPosition)){
                    guardedPositions.add(new BoardPosition(currentPosition.getRow(), col));
                    break;
                } else {
                    guardedPositions.add(new BoardPosition(currentPosition.getRow(), col));
                }
            }
        }
        return guardedPositions;
    }

    private Vector<BoardPosition> generateTopBottomDirectionMove(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        int magnitude;

        //TODO Duplicate code, Need to get refactored later
        if(currentPosition.getRow() < 8){
            magnitude = PieceMovement.doSingleRelativeRowMovement(this, new BoardPosition(8, currentPosition.getColumn()));
            for (int row = currentPosition.getRow() + magnitude; row <= 8; row += magnitude) {
                if (board.isOccupied(currentPosition)){
                    guardedPositions.add(new BoardPosition(row, currentPosition.getColumn()));
                    break;
                } else {
                    guardedPositions.add(new BoardPosition(row, currentPosition.getColumn()));
                }
            }
        }

        if(currentPosition.getRow() > 1){
            magnitude = PieceMovement.doSingleRelativeRowMovement(this, new BoardPosition(1, currentPosition.getColumn()));
            for (int row = currentPosition.getRow() + magnitude; row >= 1; row += magnitude) {
                if (board.isOccupied(currentPosition)){
                    guardedPositions.add(new BoardPosition(row, currentPosition.getColumn()));
                    break;
                } else {
                    guardedPositions.add(new BoardPosition(row, currentPosition.getColumn()));
                }
            }
        }
        return guardedPositions;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        int magnitude;

        if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            magnitude = PieceMovement.doSingleRelativeRowMovement(this, dstPosition);
            for (int row = currentPosition.getRow() + magnitude; row != dstPosition.getRow(); row += magnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            magnitude = PieceMovement.doSingleRelativeColumnMovement(this, dstPosition);
            for (int col = currentPosition.getColumn() + magnitude; col != dstPosition.getColumn(); col += magnitude) {
                currentPosition.setColumn(col);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else {
            return false;
        }

        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isHorizontalMovement(this, dstPosition)
                || PieceMovement.isVerticalMovement(this, dstPosition))
                && !PieceMovement.isDiagonalMovement(this, dstPosition);
    }
}
