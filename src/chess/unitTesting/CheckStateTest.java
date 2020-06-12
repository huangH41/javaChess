package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPlot;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.*;

class CheckStateTest {
    private ChessAssertor assertor = new ChessAssertor();

    @Test
    void kingCheckedByEnemy() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPiece.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);

        assertor.drawBoard(board);

        blackRook.move(new BoardPosition("A1"), board);
        assert(King.isKingUnderCheckState(board, (King) whiteKing));
    }

    @Test
    void KingNotCheckedByAlies() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece whiteRook = ChessPiece.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(whiteRook.getPosition(), whiteRook);
        BoardPlot.setBoardPlotGuardedStatus(board);
        assertor.drawBoard(board);

        whiteRook.move(new BoardPosition("A1"), board);
        assertEquals(false, King.isKingUnderCheckState(board, (King) whiteKing));
    }

    @Test
    void kingShouldOnlyMoveToSavePosition() {
        Board board = assertor.clearBoard(new Board());
        board.switchColor();

        ChessPiece whiteKing = ChessPiece.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPiece.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);
        assertor.drawBoard(board);

        assertThrows(InvalidMoveException.class, () -> whiteKing.move(new BoardPosition("E2"), board));
    }
}
