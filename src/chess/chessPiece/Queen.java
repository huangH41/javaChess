package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

public class Queen extends ChessPiece {

    /**
     * Define Queen with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of queen
     */
    public Queen(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.QUEEN, chessColor, position);
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

    @Override
    public void markGuardedPlot(BoardPlot boardPlot, Board board) {
        for (BoardPosition guardedPosition : generateGuardedArea(board)) {
            if (guardedPosition != null && Board.isBoardValidPosition(guardedPosition)) {
                BoardPlot.setGuardedByColor(boardPlot, guardedPosition, this.getChessColor());
            }
        }
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedPositions = new Vector<>();
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        guardedPositions.addAll(generateLeftRightDirectionMove(board, currentPosition));
        guardedPositions.addAll(generateTopBottomDirectionMove(board, currentPosition));
        guardedPositions.addAll(generateDiagonalDirectionMove(board, currentPosition));

        return guardedPositions;
    }

    //TODO generate lest right move function is duplicate with generate top bottom move
    private Vector<BoardPosition> generateLeftRightDirectionMove(Board board, BoardPosition currentPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

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
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());
        MovementOrdinate pointer = new MovementOrdinate(currentPosition, dstPosition);

        int colMagnitude = pointer.getColumnDegreeOrdinate();
        int rowMagnitude = pointer.getRowDegreeOrdinate();

        if (PieceMovement.isDiagonalMovement(this, dstPosition)) {
            for (int row = currentPosition.getRow() + rowMagnitude, col = currentPosition.getColumn() + colMagnitude;
                 row != dstPosition.getRow(); row += rowMagnitude, col += colMagnitude) {
                currentPosition.setPosition(row, col);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isVerticalMovement(this, dstPosition)) {
            for (int row = currentPosition.getRow() + rowMagnitude; row != dstPosition.getRow(); row += rowMagnitude) {
                currentPosition.setRow(row);
                if (board.isOccupied(currentPosition)) return false;
            }
        } else if (PieceMovement.isHorizontalMovement(this, dstPosition)) {
            for (int col = currentPosition.getColumn() + colMagnitude; col != dstPosition.getColumn(); col += colMagnitude) {
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
        return (PieceMovement.isAnyDirectionalMove(this, dstPosition));
    }

}
