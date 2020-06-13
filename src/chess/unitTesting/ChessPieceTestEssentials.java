package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceFactory;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;

abstract class ChessPieceTestEssentials {
    protected static final ChessAssertor assertor = new ChessAssertor();

    private static final BoardPosition centerBoard = new BoardPosition("D4");

    void basicMove(ChessPieceRank rank, String targetPosition) {
        Board board = assertor.clearBoard(new Board());

        ChessPiece piece = ChessPieceFactory.defineWhitePiece(rank, centerBoard);
        board.setPiece(piece.getPosition(), piece);

        assertor.drawBoard(board);
        assertor.movePiece(board, piece, targetPosition);
        assert (assertor.isExpectedPiece(board, new BoardPosition(targetPosition), rank));
    }

    boolean mustThrowException(boolean methods) {
        try {
            if (methods) {
                return false;
            } return false;
        } catch (Exception e) {
            return true;
        }
    }
}
