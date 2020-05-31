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
            this.unmarkGuardedPlot(board.getBoardPlot(), board);
            board.movePiece(this, dstPosition);
            this.markGuardedPlot(board.getBoardPlot(), board);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return true;
    }


    public void markGuardedPlot(BoardPlot boardPlot, Board board) {
        for (BoardPosition guardedPosition : generateGuardedArea(board)) {
            if (guardedPosition != null && Board.isBoardValidPosition(guardedPosition)) {
                BoardPlot.setGuardedByColor(boardPlot, guardedPosition, this.getChessColor());
            }
        }
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        guardedPositions.addAll(generateHorizontalGuardedArea(board, currentPosition));
        guardedPositions.addAll(generateVerticalGuardedArea(board, currentPosition));

        return guardedPositions;
    }

    private Vector<BoardPosition> generateHorizontalGuardedArea(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        if (currentPosition.getColumn() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.RIGHT));
        } if (currentPosition.getColumn() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.LEFT));
        }
        return guardedPositions;
    }

    private Vector<BoardPosition> generateVerticalGuardedArea(Board board, BoardPosition currentPosition){
        Vector<BoardPosition> guardedPositions = new Vector<>();
        if (currentPosition.getRow() < 8) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.UP));
        } if (currentPosition.getRow() > 1) {
            guardedPositions.addAll(generatePossibleGuardedPositions(board, currentPosition, MovementDirection.DOWN));
        }
        return guardedPositions;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            int colMagnitude = pointer.getColumnDegreeOrdinate();
            for (int row = currentPosition.getRow() + colMagnitude; row != dstPosition.getRow(); row += colMagnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            int rowMagnitude = pointer.getRowDegreeOrdinate();
            for (int col = currentPosition.getColumn() + rowMagnitude; col != dstPosition.getColumn(); col += rowMagnitude) {
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
