package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

public class Board {

    private final ChessPiece[][] pieceBoard = new ChessPiece[8][8];

    private King blackKing, whiteKing;
    private ChessPieceColor currentColor = ChessPieceColor.WHITE;
    private BoardPlot boardPlot;
    private int numOfTurns = 0;

    public Board() {
        initializeChessPieces();
        boardPlot = new BoardPlot(this);
        numOfTurns = 0;
    }

    public BoardPlot getBoardPlot() {
        return boardPlot;
    }

    public ChessPiece[][] getPieceBoard() {
        return this.pieceBoard;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public void setKing(King king) {
        if(king.getChessColor() == ChessPieceColor.WHITE) {
            whiteKing = king;
        } else {
            blackKing = king;
        }
    }

    public King getKing(ChessPieceColor color) {
        return color == ChessPieceColor.WHITE ? getWhiteKing() : getBlackKing();
    }

    public ChessPieceColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessPieceColor currentColor) {
        this.currentColor = currentColor;
    }

    public void switchColor() {
        if (this.getCurrentColor() == ChessPieceColor.WHITE) {
            this.setCurrentColor(ChessPieceColor.BLACK);
        } else {
            this.setCurrentColor(ChessPieceColor.WHITE);
        }
    }

    public ChessPiece getPiece(BoardPosition position) {
        return pieceBoard[position.getRow() - 1][position.getColumn() - 1];
    }

    public void setPiece(BoardPosition position, ChessPiece piece) {
        this.pieceBoard[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    public static boolean isBoardValidPosition(BoardPosition targetPosition) {
        if (targetPosition.getRow() < 1 || targetPosition.getRow() > 8) {
            return false;
        } else return targetPosition.getColumn() >= 1 && targetPosition.getColumn() <= 8;
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }

    public void setNumOfTurns(int numOfTurns) {
        this.numOfTurns = numOfTurns;
    }

    /**
     * Move a piece to destination position
     *
     * @param piece       piece to move from
     * @param dstPosition destination position
     */
    public void movePiece(ChessPiece piece, BoardPosition dstPosition) {
        BoardPosition oldPosition = piece.getPosition();
        toNewPosition(piece, dstPosition);
        removeOldPosition(oldPosition);

        if (!piece.hasMovedOnce()) {
            piece.AlreadyMovedOnce();
        }
    }

    /**
     * Move a piece relatively from current position
     *
     * @param rowsFromCurrent    rows from current position
     * @param columnsFromCurrent columns from current position
     */
    public void movePieceBy(ChessPiece piece, int rowsFromCurrent, int columnsFromCurrent) throws InvalidMoveException {
        BoardPosition newPosition = new BoardPosition(piece.getPosition().getRow() + rowsFromCurrent,
                piece.getPosition().getColumn() + columnsFromCurrent);
        movePiece(piece, newPosition);
    }

    private void toNewPosition(ChessPiece piece, BoardPosition dstPosition) {
        piece.setPosition(dstPosition);
        setPiece(dstPosition, piece);
    }

    private void removeOldPosition(BoardPosition oldPosition) {
        setPiece(oldPosition, null);
    }

    public boolean isOccupied(BoardPosition position) {
        return getPiece(position) != null;
    }

}
