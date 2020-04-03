package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;
import chess.chessPiece.Rook;

/**
 * This class controls the chess game mechanics. This class controls the board,
 * piece specialisation, king safety, checkmate, stalemate, and piece movement.
 */
public class ChessMechanics {

    private Board board;

    /**
     * Check if the king condition are safe
     *
     * @param king king to verify it's safety
     * @return king safety status (true if SAFE, false if CHECK or CHECKMATE)
     */
    public static boolean isKingChecked(King king) {
        return !(king.isChecked() == King.KingCheckState.SAFE);
    }

    /**
     * Simulate a piece movement. Useful for king safety checking.
     *
     * @param piece    piece to perform simulated movement
     * @param position simulated movement position.
     * @return valid move?
     */
    private static boolean performSimulateMove(ChessPiece piece, BoardPosition position) {
        return false;
    }

    /**
     * Do castling move for a king and a rook, with direction of queen-side castling (Ex-Cx) or king-side castling (Ex-Gx).
     * Make sure that both of the king and rook hasn't been moved before before perform castling move!
     *
     * @param board             the board to perform castling move
     * @param queenSidePosition selects rook at queen side or king side?
     */
    public static void performCastlingMove(Board board, boolean queenSidePosition) {
        int cornerColumnPosition = queenSidePosition ? BoardPosition.MIN_INDEX : BoardPosition.MAX_INDEX;
        King king = (board.getCurrentColor() == ChessPieceColor.WHITE) ?
                board.getWhiteKing() : board.getBlackKing();
        Rook rook = (Rook) ((board.getCurrentColor() == ChessPieceColor.WHITE) ?
                board.getPiece(new BoardPosition(BoardPosition.WHITE_SIDE, cornerColumnPosition)) :
                board.getPiece(new BoardPosition(BoardPosition.BLACK_SIDE, cornerColumnPosition)));

        if (!king.isFirstMove() && !rook.isFirstMove()) {
            try {
                board.movePieceBy(king, 0, (queenSidePosition ? -2 : 2));
                board.movePieceBy(rook, 0, (queenSidePosition ? 3 : -2));
            } catch (Exception e) {
                throw new InvalidMoveException(String.format("%s king and rook", board.getCurrentColor().toString()));
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
