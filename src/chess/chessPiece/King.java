package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

import java.util.Vector;

public class King extends ChessPiece {

    private boolean checkState = false;
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
     * Do castling move for a king and a rook, with direction of queen-side castling (Ex-Cx) or king-side castling (Ex-Gx).
     * Make sure that both of the king and rook hasn't been moved before before perform castling move!
     *
     * @param board             the board to perform castling move
     * @param queenSidePosition selects rook at queen side or king side?
     */
    public void performCastlingMove(Board board, boolean queenSidePosition) {
        int cornerColumnPosition = queenSidePosition ? BoardPosition.MIN_INDEX : BoardPosition.MAX_INDEX;
        King king = this;
        Rook rook = (Rook) board.getPiece(new BoardPosition(getChessColor().getStartPosition(), cornerColumnPosition));

        if (!king.hasMovedOnce() && !rook.hasMovedOnce()) {
            try {
                board.movePieceBy(king, 0, (queenSidePosition ? -2 : 2));
                board.movePieceBy(rook, 0, (queenSidePosition ? 3 : -2));
            } catch (Exception e) {
                throw new InvalidMoveException(String.format("%s king and rook", board.getCurrentColor().toString()));
            }
        }
    }

    public boolean isChecked() {
        return checkState;
    }

    public void setCheckState(boolean checkState) {
        this.checkState = checkState;
    }

    @Override
    public void move(BoardPosition dstPosition, Board board) {
        // TODO: Masih diperdebatkan apakah KingCheckState pada bagian method ini dapat dipindahkan atau tidak?
        if (isCastlable(board, dstPosition) && KingCheckState.isKingSafeAfterMovement(board, this.getPosition(), dstPosition)) {
            doCastling(board, dstPosition);
        } else if (Board.isBoardValidPosition(dstPosition) && isValidMove(board, dstPosition)) {
            movePiece(board, dstPosition);
        } else {
            throw new InvalidMoveException(getProperties(), dstPosition);
        }
    }

    private void doCastling(Board board, BoardPosition dstPosition) {
        this.unmarkGuardedPlot(board.getBoardPlot(), board);

        if (dstPosition.getColumn() == 3) {
            performCastlingMove(board, true);
        } else if (dstPosition.getColumn() == 7) {
            performCastlingMove(board, false);
        }
        this.markGuardedPlot(board.getBoardPlot(), board);
    }

    private boolean isCastlable(Board board, BoardPosition dstPosition) {
        if(!this.hasMovedOnce() && dstPosition.getRow() == getValidCastlingRow()) {
            return isValidPositionsToCastling(board, dstPosition, getCastlingDirection(dstPosition))
                    && isValidRookToCastling(board, getCastlingDirection(dstPosition));
        } else {
            return false;
        }
    }

    /**
     * Validate if rook is located on it's initial position and haven't moved as the requirment to perform castling
     *
     * @param board Current game board
     * @param dstPosDirection The direction of the king movement to perform castling
     * @return
     */
    private boolean isValidRookToCastling(Board board, MovementDirection dstPosDirection) {
        BoardPosition rookInitialPos = new BoardPosition(getValidCastlingRow(), dstPosDirection == MovementDirection.LEFT ? 1 : 8);
        ChessPiece targetPosChessPiece = board.getPiece(rookInitialPos);

        return targetPosChessPiece.getPieceRank() == ChessPieceRank.ROOK && !targetPosChessPiece.hasMovedOnce();
    }

    /**
     * Validate if the king and rook is in valid position to do castling, also validate if the king and rook position
     * after castling is available for them
     *
     * @param board Current game board
     * @param dstPosition The destination position king will go to
     * @param dstPosDirection The direction of the king movement to perform castling
     * @return
     */
    private boolean isValidPositionsToCastling(Board board, BoardPosition dstPosition, MovementDirection dstPosDirection) {
        if(this.getPosition().getRow() == getValidCastlingRow() && !board.isOccupied(dstPosition)) {
            BoardPosition rookNextPosition = new BoardPosition(getValidCastlingRow(),
                    dstPosDirection == MovementDirection.LEFT ? 4 : 6);
            return !board.isOccupied(rookNextPosition);
        }
        return false;
    }

    private int getValidCastlingRow() {
        return this.getChessColor() == ChessPieceColor.WHITE ? 1 : 8;
    }

    private MovementDirection getCastlingDirection(BoardPosition dstPosition) {
        if( dstPosition.getColumn() < 5) {
            return MovementDirection.LEFT;
        } else if(dstPosition.getColumn() > 5) {
            return MovementDirection.RIGHT;
        } else {
            throw new InvalidMoveException(getProperties(), dstPosition.getRow(), dstPosition.getColumn());
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
        return (isCircularMove(dstPosition));
    }

    @Override
    protected Vector<BoardPosition> generateGuardedArea(Board board) {
        return generateCircleGuardedArea(this.getPosition());
    }

    private Vector<BoardPosition> generateCircleGuardedArea(BoardPosition currPosition) {
        Vector<BoardPosition> guardedPositions = new Vector<>();

        for (MovementDirection direction: MovementDirection.values()) {
            if(direction != MovementDirection.STATIC && isValidGuardedPosition(currPosition, direction)) {
                BoardPosition dstPosition = currPosition.moveBy(Math.abs(direction.getRowOrdinate()),
                        Math.abs(direction.getColumnOrdinate()), direction);

                if(Board.isBoardValidPosition(dstPosition)) guardedPositions.add(dstPosition);
            }
        }
        return guardedPositions;
    }

    /**
     * Count
     *
     * @param board
     * @return
     */
    public boolean hasSafeMovePath(Board board) {
        int safePath = 0;
        BoardPlot currBoardPlot = board.getBoardPlot();
        ChessPieceColor currentSideColor = this.getChessColor();
        Vector<BoardPosition> validMovePath = this.generateCircleGuardedArea(this.getPosition());

        for (BoardPosition position : validMovePath) {
            Plot plot = currBoardPlot.getPlot(position);
            if (!isKingDstPositionSafe(plot, currentSideColor)) {
                safePath++;
            }
        }
        return safePath > 0;
    }

    private boolean isKingDstPositionSafe(Plot plot, ChessPieceColor currSideColor) {
        if(currSideColor == ChessPieceColor.WHITE) {
            return plot.isGuardedByBlack();
        } else {
            return plot.isGuardedByWhite();
        }
    }

    /**
     * Check if king moves in any direction by one block
     *
     * @param dstPosition destination position
     * @return true if king moves any-directional by one block
     */
    private boolean isCircularMove(BoardPosition dstPosition) {
        return PieceMovement.getRelativeColDistance(this, dstPosition.getColumn()) <= 1
                && PieceMovement.getRelativeRowDistance(this, dstPosition.getRow()) <= 1;
    }

    /**
     * Validate next expected guard position 1 BoardPosition away from the
     * chess piece
     *
     * @param currPosition The chess piece current position
     * @param direction The move direction it will go
     * @return True if the position is valid and false if not
     */
    private boolean isValidGuardedPosition(BoardPosition currPosition, MovementDirection direction) {
        int nextRowCoordinate = currPosition.getRow() + (direction.getRowOrdinate());
        int nextColCoordinate = currPosition.getColumn() + (direction.getColumnOrdinate());

        return BoardPosition.isValidCoordinateNumber(nextRowCoordinate)
                && BoardPosition.isValidCoordinateNumber(nextColCoordinate);
    }
}
