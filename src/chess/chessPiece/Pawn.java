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
            if (isFirstMove()) {
                maxStep = 1;
                hasMoved();
            }

            board.movePiece(this, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return hasPieceObstacle(board, dstPosition) || isCrossCapturable(board, dstPosition);
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) <= maxStep
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 0);
    }

    public boolean hasPieceObstacle(Board board, BoardPosition dstPosition) {
        ChessPieceColor pieceColor = this.getChessColor();
        if (pieceColor == ChessPieceColor.WHITE) {
            return isAnyWhitePieceObstacles(board, dstPosition);
        } else {
            return isAnyBlackPieceObstacles(board, dstPosition);
        }
    }

    // TODO: isOccupiedByWhitePiece & isOccupiedByBlackPiece is possibly a duplicate code!
    private boolean isAnyWhitePieceObstacles(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        if (PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1
                && isOpponent(board.getPiece(dstPosition))) {
            return true;
        }

        for (int row = currentPosition.getRow() - 1; row >= dstPosition.getRow(); row--) {
            currentPosition.setRow(row);
            if (board.isOccupied(currentPosition)) return false;
        }
        return true;
    }

    private boolean isAnyBlackPieceObstacles(Board board, BoardPosition dstPosition) {
        BoardPosition currentPosition = new BoardPosition(this.getPosition().getRow(), this.getPosition().getColumn());

        if (PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1
                && isOpponent(board.getPiece(dstPosition))) {
            return true;
        }

        for (int row = currentPosition.getRow() + 1; row <= dstPosition.getRow(); row++) {
            currentPosition.setRow(row);
            if (board.isOccupied(currentPosition)) return false;
        }
        return true;
    }

    /**
     * Return true if the pawn movement are went to capture opponent pieces
     *
     * @param board       board to check
     * @param dstPosition target position of pawn
     * @return true if pawn movement decided to capture opponent pieces
     */
    private boolean isCrossCapturable(Board board, BoardPosition dstPosition) {
        return (PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1
                && PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1
                && board.isOccupied(dstPosition)
                && isOpponent(board.getPiece(dstPosition)));
    }

    @Override
    public void capture(Board board, BoardPosition targetPosition) {
        //TODO adding capture logic, with en-passant tactic implemented later
    }

    public ChessPiece promote(ChessPieceRank upgradedRank) {
        if (upgradedRank == ChessPieceRank.PAWN || upgradedRank == ChessPieceRank.KING) {
            throw new IllegalStateException("Can not promote pawn to king or pawn itself!");
        }

        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return defineWhitePawn(upgradedRank, this.getPosition());
        } else return defineBlackPawn(upgradedRank, this.getPosition());
    }

}
