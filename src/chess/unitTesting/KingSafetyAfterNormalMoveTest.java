package chess.unitTesting;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;
import chess.chessPiece.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO Recommended to use a parameterized test to reduce code redundance
class KingSafetyAfterNormalMoveTest {
    private ChessAssertor assertor = new ChessAssertor();

    @Test
    void PawnNormalMoveShouldEnsureKingSafety() {
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
//        whitePawm.move(new BoardPosition("D3"), board);

        assertThrows(InvalidMoveException.class, () -> whitePawn.move(new BoardPosition("D3"), board));
    }
}