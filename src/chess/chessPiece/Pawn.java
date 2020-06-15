package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.base.exceptions.InvalidPromotionException;

import java.util.Vector;

public class Pawn extends ChessPiece {

    private int maxStep = 2;
    private int movementCount = 0;

    public Pawn(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.PAWN, chessColor, position);
    }

    private int getMovementCount() {
        return movementCount;
    }

    @Override
    public void alreadyMovedOnce() {
        hasMovedOnce = true;
        maxStep = 1;
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) {
        if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            boolean enPassable = isEnPassable(board, dstPosition);
            movePiece(board, dstPosition);

            if (enPassable) {
                enPassant(board, dstPosition);
            }
            movementCount++;
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (isRegularMovement(dstPosition) || isCrossMovement(dstPosition))
                && PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) <= maxStep;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return (isCapturable(board, dstPosition) || isEnPassable(board, dstPosition))
                || (isRegularMovement(dstPosition)
                && !board.isOccupied(dstPosition));
    }

    @Override
    public boolean isCapturable(Board board, BoardPosition targetPosition) {
        return board.isOccupied(targetPosition) && isOpponent(board.getPiece(targetPosition)) && isCrossMovement(targetPosition);
    }

    /**
     * Capture opponent pawn beside of your pawn by pass and capture them behind
     *
     * @param board          board to execute the piece en-passant and capturing stage
     * @param targetPosition the opponent pawn position besides your pawn
     */
    private void enPassant(Board board, BoardPosition targetPosition) {
        MovementDirection direction = (getChessColor() == ChessPieceColor.WHITE) ? MovementDirection.DOWN : MovementDirection.UP;
        BoardPosition opponentPosition = targetPosition.moveBy(1, 0, direction);
        board.setPiece(opponentPosition, null);
    }

    /**
     * Verify that your pawn moved 3 rows away from initial position able to
     * passes and capture side opponent who moves 2 rows away for first time beside your's pawn
     *
     * @param board          board to execute the piece en-passant and capturing stage
     * @param targetPosition the opponent pawn position besides your pawn
     * @return pawn ability to pass & capture opponent pawn beside your pawn
     */
    public boolean isEnPassable(Board board, BoardPosition targetPosition) {
        MovementDirection direction = (getChessColor() == ChessPieceColor.WHITE) ? MovementDirection.DOWN : MovementDirection.UP;
        MovementDirection pieceColorDirection = direction.flipDirection();

        if (!(!board.isOccupied(targetPosition)
                && getPosition().getRow() == (this.getChessColor().getStartPosition() + (pieceColorDirection.getRowOrdinate() * 4)))
                && isCrossMovement(targetPosition)) {
            return false;
        }

        ChessPiece opponent = board.getPiece(targetPosition.moveBy(1, 0, direction));
        if (opponent != null) {
            return isOpponent(opponent)
                    && (opponent instanceof Pawn)
                    && ((Pawn) opponent).movementCount == 1
                    && opponent.getFirstMoveAt() == board.getNumOfTurns() - 1;
        } else {
            return false;
        }
    }

    /**
     * Promotes pawn into Rook, Knight, Bishop, or Queen
     *
     * @param upgradedRank upgraded pawn parameter as Rook, Knight, Bishop, or Queen
     * @return Rook, Knight, Bishop, or Queen
     */
    public ChessPiece promote(ChessPieceRank upgradedRank) {
        if (!isPawnPromotable(true) || !upgradedRank.isPromotable()) {
            throw new InvalidPromotionException("Invalid promotion: can not promote pawn to king or pawn itself!");
        }

        if (this.getChessColor() == ChessPieceColor.WHITE) {
            return ChessPieceFactory.defineWhitePiece(upgradedRank, this.getPosition());
        } else return ChessPieceFactory.defineBlackPiece(upgradedRank, this.getPosition());
    }

    /**
     * Verify that the pawn is promotable at correct row
     * @return promotable at enemy base row
     */

    public boolean isPawnPromotable(boolean atBase) {
        int direction = (getChessColor().getMovementDirectionOrdinate());
        int promotableRowPosition = 9 - getChessColor().getStartPosition();
        return getPosition().getRow() + (atBase ? 0 : direction) == promotableRowPosition;
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

    //TODO Really need to be refactored later
    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        Vector<BoardPosition> guardedArea = new Vector<>();
        int row = (this.getChessColor() == ChessPieceColor.WHITE) ? this.getPosition().getRow() + 1 : this.getPosition().getRow() - 1;
        if (row < 1 || row > 8) {
            return null;
        }

        for (int i = 0, colMover = 1; i < 2; i++, colMover *= -1) {
            int column = this.getPosition().getColumn() - colMover;
            if (column >= 1 && column <= 8) {
                BoardPosition position = new BoardPosition(row, column);
                if (Board.isBoardValidPosition(position)) {
                    guardedArea.add(position);
                }
            }
        }
        return guardedArea;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Pawn copy = new Pawn(this.getChessColor(), this.getPosition());
        copyProperties(copy);
        copy.movementCount = movementCount;
        copy.maxStep = maxStep;
        return copy;
    }
}
