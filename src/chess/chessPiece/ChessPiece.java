package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceColor;
import chess.base.ChessPieceRank;

public abstract class ChessPiece {
    private ChessPieceRank pieceRank;
    private ChessPieceColor chessColor;
    private BoardPosition position;
    protected boolean firstMove = false;

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

    /**
     * Return this object as String by format: "Color, Rank, Chess-Position-Notation"
     *
     * @return Piece information with format "Color, Rank, Chess-Position-Notation"
     */
    @Override
    public String toString() {
        return String.format("%s %s [%s]", getChessColor(), getPieceRank(), getPosition().toString());
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

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition newPosition) {
        this.position = newPosition;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void hasMoved() {
        firstMove = true;
    }

    public boolean isOpponent(ChessPiece counterPiece) {
        return counterPiece.getChessColor() != this.getChessColor();
    }

    /**
     * Verify movement of the piece. This will verify piece obstacle <code>isValidMovePath()</code>
     * and verify piece movement style <code>isValidPieceMovement()</code>
     *
     * @param board       board to check the validity
     * @param dstPosition the new position of that piece
     * @return is able to move and has no obstacles
     */
    protected boolean isValidMove(Board board, BoardPosition dstPosition) {
        boolean validPieceMovement = isValidPieceMovement(dstPosition);
        boolean validMovePath = isValidMovePath(board, dstPosition);
        return validMovePath && validPieceMovement;
    }

    /**
     * Verify movement path of the piece. Do recursive check of the obstacle to make sure that
     * every movement steps are valid and not obstructed by other piece.
     *
     * @param board       board to check the validity
     * @param dstPosition the new position of that piece
     * @return has no obstacle after some recursive
     */
    protected abstract boolean isValidMovePath(Board board, BoardPosition dstPosition);

    /**
     * Verify piece movement style to make sure the movement are valid and not out-of-bound
     *
     * @param dstPosition the new position of that piece
     * @return correct movement style of the piece
     */
    protected abstract boolean isValidPieceMovement(BoardPosition dstPosition);

    /**
     * Declare an abstract method that every class extended from this class to perform a movement.
     * Throws exception if any invalid move or obstacle occurred.
     *
     * @param dstPosition destination position
     */
    public abstract void move(BoardPosition dstPosition, Board board);

    /**
     * Allow the capture and voids of a piece if it is an opponent. For en-passant, pawn requires to move
     * diagonally at empty board behind the opponent piece to capture them.
     *
     * @param board          board to execute the piece capturing stage
     * @param targetPosition target piece position to capture and void
     */
    abstract protected boolean isCapturable(Board board, BoardPosition targetPosition);


}
