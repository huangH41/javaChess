package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;
import chess.base.exceptions.InvalidMoveException;

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

    @Override
    public String toString() {
        return String.format("%s %s [%s]", getPieceRank(), getChessColor(), getPosition());
    }

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
        return counterPiece.getChessColor() != this.getChessColor();
    }

    public static ChessPiece getFromBoard(Board board, BoardPosition position) {
        return board.getPiece(position);
    }

    /**
     * Move a piece relatively from current position
     *
     * @param rows    rows from current position
     * @param columns columns from current position
     */
    public void moveBy(int rows, int columns) throws InvalidMoveException {
        BoardPosition newPosition = this.getPosition();
        newPosition.setPosition(this.getPosition().getRow() + rows, this.getPosition().getColumn() + columns);
        performMovement(newPosition);
    }

    /**
     * Move a piece to new position
     *
     * @param dstPosition new position
     * @throws InvalidMoveException if the movement are out-of-bound or have obstacle
     */
    public void performMovement(BoardPosition dstPosition) throws InvalidMoveException {
        if (Board.validatePosition(dstPosition)) {
            move(dstPosition);
        }
    }

    abstract protected void capture(ChessPiece[][] board, BoardPosition targetPosition);

    /**
     * Declare an abstract method that every class extended from this class to perform a movement.
     * Throws exception if any invalid move or obstacle occurred.
     *
     * @param dstPosition destination position
     */
    abstract protected void move(BoardPosition dstPosition) throws InvalidMoveException;

}
