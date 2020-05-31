package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

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
            movePiece(board, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return (board.isOccupied(targetPosition) && isOpponent(board.getPiece(targetPosition)));
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return (isCapturable(board, dstPosition) || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.isLetterLMovement(this, dstPosition));
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedPositions = new Vector<>();
        guardedPositions.addAll(generateTopBottomDirectionMove(this.getPosition()));
        guardedPositions.addAll(generateLeftRightDirectionMove(this.getPosition()));
        return guardedPositions;
    }

    //TODO Method below maybe is a duplicate code, refactor later
    private Vector<BoardPosition> generateTopBottomDirectionMove(BoardPosition currentPosition){
        Vector<BoardPosition> guardedPosition = new Vector<>();
        int row, col, colMover, x;
        for(x = 0, colMover = 1; x < 4; x++, colMover *= -1){
            row = (x < 2) ? currentPosition.getRow() + 2 : currentPosition.getRow() - 2;
            if(BoardPosition.isValidCoordinateNumber(row)){
                col = currentPosition.getColumn() - colMover;
                guardedPosition.add((BoardPosition.isValidCoordinateNumber(col)) ? new BoardPosition(row, col) : null);
            }
        }
        return guardedPosition;
    }

    private Vector<BoardPosition> generateLeftRightDirectionMove(BoardPosition currentPosition){
        Vector<BoardPosition> guardedPosition = new Vector<>();
        int row, col, rowMover, x;
        for(x = 0, rowMover = 1; x < 4; x++, rowMover *= -1){
            col = (x < 2) ? currentPosition.getColumn() - 2 : currentPosition.getColumn() + 2;
            if(BoardPosition.isValidCoordinateNumber(col)){
                row = currentPosition.getRow() - rowMover;
                guardedPosition.add((BoardPosition.isValidCoordinateNumber(row)) ? new BoardPosition(row, col) : null);
            }
        }
        return guardedPosition;
    }

}
