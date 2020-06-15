package chess.chessPiece;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;

public enum KingCheckState {
    SAFE, CHECK, CHECKMATE;

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
            kingPiece.setCheckState(CHECK);
        } else {
            kingPiece.setCheckState(SAFE);
        }
        return kingPiece.isChecked() != SAFE;
    }

    public static boolean isStalemate(Board board, King kingPiece) {
        if(!kingPiece.hasSafeMovePath(board) && board.getCurrSideRemainingPieceCount() == 1) {
            return true;
        }
        return false;
    }

    public static boolean isCheckmate(Board board, King kingPiece) {
        if(!kingPiece.hasSafeMovePath(board) && kingPiece.isChecked() == CHECK) {
            return true;
        }
        return false;
    }

    /**
     * Check if king is under check state after one piece move into a certain position
     *
     * @param board Current game board
     * @param startPosition Start Position of the
     * @param dstPosition Destination position of the toMovePiece
     * @return true if the king not under check state, otherwise false
     */
    public static boolean isKingSafeAfterMovement(Board board, BoardPosition startPosition, BoardPosition dstPosition) {
        Board boardCopy = BoardFactory.copyBoard(board);
        ChessPiece toMovePiece = boardCopy.getPiece(startPosition);

        King currSideKing = toMovePiece.getChessColor() == ChessPieceColor.WHITE ?
                boardCopy.getWhiteKing() : boardCopy.getBlackKing();

        toMovePiece.movePiece(boardCopy, dstPosition);
        BoardPlot.resetBoardPlotGuardStatus(boardCopy);

        if(!isKingUnderCheckState(boardCopy, currSideKing)) {
            return true;
        } else {
            throw new InvalidMoveException(String.format("Invalid Move!! %s will be checked", currSideKing));
        }
    }
}