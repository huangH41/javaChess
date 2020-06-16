package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingCheckStatusTest extends ChessPieceTestEssentials {

    @Test
    void kingMoveToSafeLocationOnly() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);

        System.out.println("Initial Board and guard board");
        assertor.drawBoard(board);
        assertor.drawGuardBoard(board);

        assertThrows(InvalidMoveException.class, () -> whiteKing.move(new BoardPosition("E2"), board));
    }

    @Test
    void pawnNormalMoveEnsureKingSafety() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E2"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A2"));
        ChessPiece whitePawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("D2"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(blackRook.getPosition(), blackRook);
        board.setPiece(whitePawn.getPosition(), whitePawn);
        BoardPlot.setBoardPlotGuardedStatus(board);

        System.out.println("Initial Board and guard board");
        assertor.drawBoard(board);
        assertor.drawGuardBoard(board);

        assertThrows(InvalidMoveException.class, () -> whitePawn.move(new BoardPosition("D3"), board));
    }

    @Test
    void whiteKingLeftCastlingEnsureKingSafety() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece whiteRook = ChessPieceFactory.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("C5"));
        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(whiteRook.getPosition(), whiteRook);
        board.setPiece(blackRook.getPosition(), blackRook);
        BoardPlot.setBoardPlotGuardedStatus(board);

        System.out.println("Initial Board");
        assertor.drawBoard(board);
        System.out.println("\nInitial Guarded Board");
        assertor.drawGuardBoard(board);
        assertThrows(InvalidMoveException.class, () -> whiteKing.move(new BoardPosition("C1"), board));
    }

    @Test
    void pawnEnPassantEnsureKingSafety() {
        Board board = assertor.clearBoard(new Board());

        ChessPiece whiteKing = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1"));
        ChessPiece whitePawn = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition("E5"));
        ChessPiece blackRook = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("E8"));
        ChessPiece blackPawn = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition("D7"));

        board.setPiece(whiteKing.getPosition(), whiteKing);
        board.setPiece(whitePawn.getPosition(), whitePawn);
        board.setPiece(blackRook.getPosition(), blackRook);
        board.setPiece(blackPawn.getPosition(), blackPawn);
        switchTurn(board);

        System.out.println("Initial Board");
        assertor.drawBoard(board);
        System.out.println("\nInitial Guarded Board");
        assertor.drawGuardBoard(board);

        blackPawn.move(new BoardPosition("D5"), board);
        switchTurn(board);

        assertThrows(InvalidMoveException.class, () -> whitePawn.move(new BoardPosition("D6"), board));
    }

    private void switchTurn(Board board) {
        board.switchColor();
        BoardPlot.resetBoardPlotGuardStatus(board);
    }

}