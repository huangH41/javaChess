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

    @Test
    void kingCheckmate() {
        Board board = assertor.clearBoard(new Board());
        board.switchColor();

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        ChessPiece blackQueen = ChessPieceFactory.defineBlackPiece(ChessPieceRank.QUEEN, new BoardPosition("B2"));

        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        board.setPiece(blackQueen.getPosition(), blackQueen);
        BoardPlot.setBoardPlotGuardedStatus(board);

        assertor.drawBoard(board);

        assertor.movePiece(board, blackQueen, "B1");
        assertor.changeTurn(board);

        assertEquals(true, KingCheckState.isCheckmate(board, (King) whiteKing));
    }

    @Test
    void stalemate() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("A1"));
        ChessPiece blackRook1 = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("C2"));
        ChessPiece blackRook2 = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("B3"));

        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook1.getPosition(), blackRook1);
        board.setPiece(blackRook2.getPosition(), blackRook2);
        BoardPlot.setBoardPlotGuardedStatus(board);

        assertor.drawBoard(board);

        assertEquals(true, KingCheckState.isStalemate(board, board.getKing(board.getCurrentColor())));
    }
}
