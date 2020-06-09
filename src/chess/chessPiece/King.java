package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

public class King extends ChessPiece {

    private KingCheckState checkState = KingCheckState.SAFE;

    /**
     * Define King with defined color and position
     *
     * @param chessColor black or white?
     * @param position   initial position of king
     */
    public King(ChessPieceColor chessColor, BoardPosition position) {
        super(ChessPieceRank.KING, chessColor, position);
    }

    /**
     * To check if the intended king piece current position is checked by other opponent piece
     *
     * @param board     Board to get the king position plot
     * @param kingPiece King piece to validate
     * @return King check state
     */
    public static boolean isKingUnderCheckState(Board board, King kingPiece) {
        return isKingUnderCheckState(board, kingPiece, kingPiece.getPosition());
    }

    /**
     * To check the king movement validity
     *
     * @param board       Board to get the king position plot
     * @param kingPiece   King piece to validate
     * @param dstPosition The King position
     * @return King check state
     */
    // TODO "CHECKMATE" haven't implemented
    public static boolean isKingUnderCheckState(Board board, King kingPiece, BoardPosition dstPosition) {
        Plot plot = board.getBoardPlot().getPlot(dstPosition);
        if (kingPiece.getChessColor() == ChessPieceColor.WHITE ? plot.isGuardedByBlack() : plot.isGuardedByWhite()) {
            kingPiece.checkState = KingCheckState.CHECK;
        } else {
            kingPiece.checkState = KingCheckState.SAFE;
        }
        return kingPiece.checkState != KingCheckState.SAFE;
    }

    public void setCheck(KingCheckState checkState) {
        this.checkState = checkState;
    }

    public KingCheckState isChecked() {
        return checkState;
    }

    /**
     * Check if king moves in any direction by one block
     *
     * @param dstPosition destination position
     * @return true if king moves any-directional by one block
     */
    private boolean isSingleAnyDirectionalMove(BoardPosition dstPosition) {
        return PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) == 1
                || PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) == 1;
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) {
        boolean isCastlablePosition = board.isOccupied(new BoardPosition(this.getPosition().getRow(), 3))
                || board.isOccupied(new BoardPosition(this.getPosition().getRow(), 7));
        if (!this.isFirstMove() && isCastlablePosition) {
            this.unmarkGuardedPlot(board.getBoardPlot(), board);
            if (dstPosition.getColumn() == 3) {
                ChessMechanics.performCastlingMove(board, true);
            } else if (dstPosition.getColumn() == 7) {
                ChessMechanics.performCastlingMove(board, false);
            }
            this.markGuardedPlot(board.getBoardPlot(), board);
        } else if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            movePiece(board, dstPosition);
        } else {
            throw new InvalidMoveException(this, dstPosition);
        }
    }

    @Override
    protected boolean isCapturable(Board board, BoardPosition targetPosition) {
        return true;
    }

    @Override
    protected boolean isValidMovePath(Board board, BoardPosition dstPosition) {
        return (board.isOccupied(dstPosition) && isOpponent(board.getPiece(dstPosition))
                || !board.isOccupied(dstPosition));
    }

    @Override
    protected boolean isValidPieceMovement(BoardPosition dstPosition) {
        return (isSingleAnyDirectionalMove(dstPosition));
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {

        return null;
    }

    public enum KingCheckState {
        SAFE, CHECK, CHECKMATE
    }
}
