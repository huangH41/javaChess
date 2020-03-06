package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;

public abstract class ChessPiece {
    private ChessPieceRank pieceRank;
    private ChessPieceColor chessColor;
    private BoardPosition position;
    private boolean isFirstMove = true;

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

    /**
     * Required for obtaining position from
     */
    protected Board assignedBoard;

    public static ChessPiece defineWhitePawn(ChessPieceRank rank, BoardPosition position) {
        switch (rank) {
            case KING:
                return new King(ChessPieceColor.WHITE, position);
            case QUEEN:
                return new Queen(ChessPieceColor.WHITE, position);
            case BISHOP:
                return new Bishop(ChessPieceColor.WHITE, position);
            case KNIGHT:
                return new Knight(ChessPieceColor.WHITE, position);
            case ROOK:
                return new Rook(ChessPieceColor.WHITE, position);
            case PAWN:
                return new Pawn(ChessPieceColor.WHITE, position);
            default:
                throw new IllegalArgumentException("Invalid piece rank!");
        }
    }

    public static ChessPiece defineBlackPawn(ChessPieceRank rank, BoardPosition position) {
        switch (rank) {
            case KING:
                return new King(ChessPieceColor.BLACK, position);
            case QUEEN:
                return new Queen(ChessPieceColor.BLACK, position);
            case BISHOP:
                return new Bishop(ChessPieceColor.BLACK, position);
            case KNIGHT:
                return new Knight(ChessPieceColor.BLACK, position);
            case ROOK:
                return new Rook(ChessPieceColor.BLACK, position);
            case PAWN:
                return new Pawn(ChessPieceColor.BLACK, position);
            default:
                throw new IllegalArgumentException("Invalid piece rank!");
        }
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
    public boolean isFirstMove() {
        return isFirstMove;
    }

    /**
     * By using this method, this parameter will irreversibly set the pawn moved state to true
     */
    public void setMovedYet() {
        isFirstMove = true;
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

    public static ChessPiece getFromBoard(Board board, BoardPosition position) {
        return board.getPiece(position);
    }

    public void performMovement(int dstRow, int dstCol) throws Exception {
        if (Board.validatePosition(dstRow, dstCol)) {
            move(dstRow, dstCol);
        }
    }

    protected void capture(ChessPiece chessPiece) {
        //TODO kill logic have yet to be defined (it need to obtain a board and board position too..)
    }

    /**
     * Declare an abstract method that every class extended from this class to perform a movement.
     * Throws exception if any invalid move or obstacle occurred.
     *
     * @param dstCol the destination column
     * @param dstRow the destination row
     */
    abstract protected void move(int dstRow, int dstCol) throws Exception;

}
