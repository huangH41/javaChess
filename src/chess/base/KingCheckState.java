package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.King;

public class KingCheckState {

    /**
     * To check if the intended king piece current position is checked by other opponent piece
     *
     * @param board Board to get the king position plot
     * @param color King piece color to validate
     * @return King check state (if the board doesn't have King, always return false)
     */
    public static boolean isKingUnderCheckState(Board board, ChessPieceColor color) {
        King king = getKing(board, color);
        if (king == null) {
            return false;
        }
        return isKingUnderCheckState(board, king, king.getPosition());
    }

    /**
     * To check the king movement validity
     *
     * @param board       Board to get the king position plot
     * @param kingPiece   King piece to validate
     * @param dstPosition The King position
     * @return King check state
     */
    private static boolean isKingUnderCheckState(Board board, King kingPiece, BoardPosition dstPosition) {
        Plot plot = board.getBoardPlot().getPlot(dstPosition);
        kingPiece.setCheckState(plot.getGuardStatus(kingPiece.getChessColor()));

        return kingPiece.isChecked();
    }

    public static boolean isStalemate(Board board, ChessPieceColor color) {
        King kingPiece = getKing(board, color);
        if (kingPiece == null) {
            return false;
        }
        return !kingPiece.hasSafeMovePath(board) && board.getCurrSideRemainingPieceCount() == 1 && !kingPiece.isChecked();
    }

    public static boolean isCheckmate(Board board, ChessPieceColor color) {
        King kingPiece = getKing(board, color);
        if (kingPiece == null) {
            return false;
        }
        return !kingPiece.hasSafeMovePath(board) && kingPiece.isChecked();
    }

    /**
     * Check if king is under check state after one piece move into a certain position
     *
     * @param board         Current game board
     * @param startPosition Start Position of the
     * @param dstPosition   Destination position of the toMovePiece
     * @return true if the king not under check state, otherwise false
     */
    public static boolean isKingSafeAfterMovement(Board board, BoardPosition startPosition, BoardPosition dstPosition) {
        Board boardCopy = BoardFactory.copyBoard(board);
        ChessPiece toMovePiece = boardCopy.getPiece(startPosition);

        toMovePiece.movePiece(boardCopy, dstPosition);
        BoardPlot.resetBoardPlotGuardStatus(boardCopy);
        ChessPieceColor color = toMovePiece.getChessColor();

        if (!isKingUnderCheckState(boardCopy, color)) {
            return true;
        } else {
            throw InvalidMoveException.unsafeKing(color);
        }
    }

    private static King getKing(Board board, ChessPieceColor color) {
        return (King) board.getNearestChessPiece(color, ChessPieceRank.KING);
    }
}