package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;


public class Bishop extends ChessPiece {

    /**
     * Define Bishop with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of bishop
     */
    public Bishop(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.BISHOP, chessColor, position);
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
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        guardedPositions.addAll(generateDiagonalDirectionMove(board, currentPosition));

        return guardedPositions;
    }

    //TODO generate lest right move function is duplicate with generate top bottom move
    private Vector<BoardPosition> generateDiagonalDirectionMove(Board board, BoardPosition currentPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        BoardPosition position;
        if (currentPosition.getColumn() < 8 || currentPosition.getRow() < 8) {
            for (int row = currentPosition.getRow() + 1, col = currentPosition.getColumn() + 1;
                 row <= 8 && col <= 8; row++, col++) {
                position = new BoardPosition(row, col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }

        if (currentPosition.getColumn() > 1 || currentPosition.getRow() < 8) {
            for (int row = currentPosition.getRow() + 1, col = currentPosition.getColumn() - 1;
                 row <= 8 && col >= 1; row++, col--) {
                position = new BoardPosition(row, col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }

        if (currentPosition.getColumn() < 8 || currentPosition.getRow() > 1) {
            for (int row = currentPosition.getRow() - 1, col = currentPosition.getColumn() + 1;
                 row >= 1 && col <= 8; row--, col++) {
                position = new BoardPosition(row, col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }

        if (currentPosition.getColumn() > 1 || currentPosition.getRow() > 1) {
            for (int row = currentPosition.getRow() - 1, col = currentPosition.getColumn() - 1;
                 row >= 1 && col >= 1; row--, col--) {
                position = new BoardPosition(row, col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }
        return guardedPositions;
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return true;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        int colMagnitude = pointer.getColumnDegreeOrdinate();
        int rowMagnitude = pointer.getRowDegreeOrdinate();

        for (int row = currentPosition.getRow() + rowMagnitude, col = currentPosition.getColumn() + colMagnitude;
             row != dstPosition.getRow(); row += rowMagnitude, col += colMagnitude) {
            currentPosition.setPosition(row, col);
            if (board.isOccupied(currentPosition)) return false;
        }
        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isDiagonalMovement(this, dstPosition));
    }


}
