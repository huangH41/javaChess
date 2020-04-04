package chess.base;

import chess.chessPiece.ChessPiece;

/**
 * BlankBoard is a class for temporary & experimental chess board. You can add or remove it directly using this class.
 * REMEMBER: Don't forget to cast the (Board) Object as (BlankBoard) before doing any methods in BlankBoard
 */
public class BlankBoard extends Board {
    public BlankBoard() {
        initializeBoardPlot();
    }

    public void initializeBoardPlot() {
        this.getBoardPlot().initializePlotGuardStatus(this);
    }

    public void addPiece(BoardPosition position, ChessPiece piece) {
        this.setPiece(position, piece);
        initializeBoardPlot();
    }

    public void removePiece(BoardPosition position) {
        this.setPiece(position, null);
        initializeBoardPlot();
    }
}
