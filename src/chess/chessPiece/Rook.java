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
        guardedPositions.addAll(generateTopBottomDirectionMove(board, currentPosition));
        guardedPositions.addAll(generateLeftRightDirectionMove(board, currentPosition));

        return guardedPositions;
    }

    //TODO generate lest right move function is duplicate with generate top bottom move
    private Vector<BoardPosition> generateLeftRightDirectionMove(Board board, BoardPosition currentPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        //TODO Duplicate code, Need to get refactored later
        BoardPosition position;
        if (currentPosition.getColumn() < 8) {
            for (int col = currentPosition.getColumn() + 1; col <= 8; col++) {
                position = new BoardPosition(currentPosition.getRow(), col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }

        if (currentPosition.getColumn() > 1) {
            for (int col = currentPosition.getColumn() - 1; col >= 1; col--) {
                position = new BoardPosition(currentPosition.getRow(), col);
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }
        return guardedPositions;
    }

    private Vector<BoardPosition> generateTopBottomDirectionMove(Board board, BoardPosition currentPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        //TODO Duplicate code, Need to get refactored later
        BoardPosition position;
        if (currentPosition.getRow() < 8) {
            for (int row = currentPosition.getRow() + 1; row <= 8; row++) {
                position = new BoardPosition(row, currentPosition.getColumn());
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
                }
            }
        }

        if (currentPosition.getRow() > 1) {
            for (int row = currentPosition.getRow() - 1; row >= 1; row--) {
                position = new BoardPosition(row, currentPosition.getColumn());
                guardedPositions.add(position);
                if (board.isOccupied(position)) {
                    break;
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
