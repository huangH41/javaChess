package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.base.ChessPiece;
import chess.chessPiece.King;
import chess.base.KingCheckState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckStateTest extends ChessPieceTestEssentials {

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
        assert(KingCheckState.isKingUnderCheckState(board, ChessPieceColor.WHITE));
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
        assert(!KingCheckState.isKingUnderCheckState(board, ChessPieceColor.WHITE));
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
        assertor.drawGuardBoard(board);
        assertor.changeTurn(board);

        assert(KingCheckState.isCheckmate(board, ChessPieceColor.WHITE));
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

        assert(KingCheckState.isStalemate(board, board.getCurrentColor()));
    }
}
