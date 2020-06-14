package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;
import chess.chessPiece.KingCheckState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckStateTest {
    private ChessAssertor assertor = new ChessAssertor();

    @Test
    void kingCheckedByEnemy() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);

        assertor.drawBoard(board);

        blackRook.move(new BoardPosition("A1"), board);
        assert(KingCheckState.isKingUnderCheckState(board, (King) whiteKing));
    }

    @Test
    void KingNotCheckedByAlies() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece whiteRook = ChessPieceFactory.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(whiteRook.getPosition(), whiteRook);
        BoardPlot.setBoardPlotGuardedStatus(board);
        assertor.drawBoard(board);

        whiteRook.move(new BoardPosition("A1"), board);
        assertEquals(false, KingCheckState.isKingUnderCheckState(board, (King) whiteKing));
    }

    @Test
    void kingShouldOnlyMoveToSavePosition() {
        Board board = assertor.clearBoard(new Board());
        board.switchColor();

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);
        assertor.drawBoard(board);

        assertThrows(InvalidMoveException.class, () -> whiteKing.move(new BoardPosition("E2"), board));
    }
}
