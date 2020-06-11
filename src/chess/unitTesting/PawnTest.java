package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;
import chess.chessPiece.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

    private static final ChessAssertor assertor = new ChessAssertor();

    @Test
    void testRegularMove() throws IllegalAccessException {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4", true);
        Pawn p = (Pawn) board.getPiece(new BoardPosition("C4"));
        assert(board.isOccupied(new BoardPosition("C4")));
        assert(!board.isOccupied(new BoardPosition("C2")));
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 1);
        assertor.drawBoard(board);

        assertor.movePiece(board, "C4", "C5", true);
        assert ((int) ChessAssertor.accessPrivateMethodValuers(p, "getMovementCount") == 2);
        assertor.drawBoard(board);
    }

    @Test
    void doNotReverse() {
        Board board = new Board();
        assertor.drawBoard(board);

        assertor.movePiece(board, "C2", "C4", true);

        assertThrows(InvalidMoveException.class, () -> {
            assertor.movePiece(board, "C4", "C3", true);
        }).printStackTrace();
    }


    /**
     * A very simple test to test pawn ability to do a regular move, cross move, and en-passant simultaneously
     * @throws IllegalAccessException if happened, these test would failed automatically!
     */
    @Test
    void zigzaggerScenario() throws IllegalAccessException {
        Board board = new Board();
        Pawn whitePawn = (Pawn) board.getPiece(new BoardPosition("B2"));

        assertor.drawBoard(board);
        assertor.movePiece(board, "B2", "B4", true);
        assertor.movePiece(board, "C7", "C5", true);
        assertor.movePiece(board, "B4", "C5");
        assert(board.getPiece(new BoardPosition("C5")).equals(whitePawn));

        assertor.movePiece(board, "B7", "B5");
        assert((int) ChessAssertor.accessPrivateMethodValuers(
                board.getPiece(new BoardPosition("B5")), "getMovementCount") == 1);
        assert(whitePawn.isEnPassable(board, new BoardPosition("B6")));

        assertor.movePiece(board, "C5", "B6");
        assert(!board.isOccupied(new BoardPosition("B5")));

        Pawn blackPawnFirstMover = (Pawn) board.getPiece(new BoardPosition("A7"));
        assertor.movePiece(board, "A7", "B6");
        assert(board.getPiece(new BoardPosition("B6")).equals(blackPawnFirstMover));
    }

    @Test
    void promote() {
        Board board = new Board();
        ChessPiece whitePawn = board.getPiece(new BoardPosition("H2"));

        assertor.movePiece(board, "H2", "H4", true);
        assertor.movePiece(board, "E7", "E5", true);
        assertor.movePiece(board, "H4", "H5", true);
        assertor.movePiece(board, "G7", "G5", true);
        assertor.movePiece(board, "F2", "F4", true);
        assertor.movePiece(board, "H7", "H6");

        assert(((Pawn) whitePawn).isEnPassable(board, new BoardPosition("G6")));
        assertor.movePiece(board, "H5", "G6");
        assert(board.getPiece(new BoardPosition("G6")).equals(whitePawn));

        assertor.movePiece(board, "H6", "H5", true);
        assertor.movePiece(board, "H1", "H5");
        assertor.movePiece(board, "G8", "F6", true);
        assertor.movePiece(board, "G6", "G7", true);
        assertor.movePiece(board, "F8", "B4", true);

        // TODO: CHECK STATE if H5 -> E5 (use Black-Castling for these scenario
        assertor.movePiece(board, "H5", "F5");

        assertor.movePiece(board, "E5", "F4", true);

        // promote
        assertor.movePiece(board, "G7", "G8");
        assert(board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assert(((Pawn) whitePawn).isPawnPromotable());

        whitePawn = ((Pawn) whitePawn).promote(ChessPieceRank.ROOK);

        // TODO: Place these line below at promoting pawn as higher-tier piece (at gameplay)
        board.setPiece(new BoardPosition("G8"), whitePawn);

        assert(whitePawn instanceof Rook);
        assert(board.getPiece(new BoardPosition("G8")).equals(whitePawn));
        assert(board.getPiece(new BoardPosition("G8")).getPieceRank().equals(ChessPieceRank.ROOK));

        assertor.drawBoard(board);
    }
}