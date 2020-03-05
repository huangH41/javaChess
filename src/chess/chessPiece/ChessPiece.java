package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.ChessPieceColor;

public abstract class ChessPiece {
    private ChessPieceRank pieceRank;
    private ChessPieceColor chessColor;
    private BoardPosition position;
    private boolean movedYet = false;

    /**
     * Define chess piece with status, color (either black or white), and position on the chess
     *
     * @param pieceRank  the chess piece name
     * @param chessColor the chess piece color as player or opponent color (either black or white)
     * @param position   the chess piece position (define new BoardPosition in row and column)
     */
    public ChessPiece(ChessPieceRank pieceRank, ChessPieceColor chessColor, BoardPosition position) {
        this.pieceRank = pieceRank;
        this.chessColor = chessColor;
        this.position = position;
    }

    public ChessPieceRank getPieceRank() {
        return pieceRank;
    }

    public void setPieceRank(ChessPieceRank pieceRank) {
        this.pieceRank = pieceRank;
    }

    public ChessPieceColor getChessColor() {
        return chessColor;
    }

    public void setChessColor(ChessPieceColor chessColor) {
        this.chessColor = chessColor;
    }

    /**
     * Check if the piece have perform it's move since game begins
     *
     * @return pawn moved state since game begins
     */
    public boolean isMovedYet() {
        return movedYet;
    }

    /**
     * By using this method, this parameter will irreversibly set the pawn moved state to true
     */
    public void setMovedYet() {
        movedYet = true;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition newPosition) {
        this.position = newPosition;
    }

    public boolean isOpponent(ChessPiece counterPiece) {
        return (counterPiece.getChessColor() == ChessPieceColor.WHITE && this.getChessColor() == ChessPieceColor.BLACK)
                || (counterPiece.getChessColor() == ChessPieceColor.BLACK && this.getChessColor() == ChessPieceColor.WHITE);
    }

    public void performMovement(int dstCol, int dstRow) {
        if (Board.validatePosition(dstCol, dstRow)) {
            move(dstCol, dstRow);
        }
    }

    protected void kill(ChessPiece chessPiece) {
        //TODO kill logic have yet to be defined
    }

    // TODO where the move() method placed?
    abstract protected void move(int dstCol, int dstRow);


}
