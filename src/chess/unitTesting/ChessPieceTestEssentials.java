package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceFactory;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;
import org.junit.Assert;

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

    /**
     * Assert that the boolean comparison must expect the qualified status
     * @param compare the boolean comparison to test/qualify
     * @param expected true/false to expected in comparison
     */
    void assertThat(boolean compare, boolean expected) {
        assert (compare == expected);
    }

    /**
     * Assert that the boolean comparison must expect the qualified status (with message to classify test)
     * @param message message to clarify test
     * @param compare the boolean comparison to test/qualify
     * @param expected true/false to expected in comparison
     */
    void assertThat(boolean compare, boolean expected, String message) {
        Assert.assertEquals(message, expected, compare);
    }
}
