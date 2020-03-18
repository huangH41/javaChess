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
    private boolean firstMove = false;

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

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition newPosition) {
        this.position = newPosition;
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

    public boolean isFirstMove() {
        return firstMove;
    }

    public void hasMoved() {
        firstMove = true;
    }

    public boolean isOpponent(ChessPiece counterPiece) {
        return counterPiece.getChessColor() != this.getChessColor() ? true : false;
    }

    //TODO getFromBoard is not a relevant method for chessPiece Class, consider to remove later
    public static ChessPiece getFromBoard(Board board, BoardPosition position) {
        return board.getPiece(position);
    }

    /**
     * Declare an abstract method that every class extended from this class to perform a movement.
     * Throws exception if any invalid move or obstacle occurred.
     *
     * @param dstPosition destination position
     */
    abstract protected void move(BoardPosition dstPosition, Board board) throws Exception;

    /**
     *
     *
     * @param board
     * @param targetPosition
     */
    abstract protected void capture(ChessPiece[][] board, BoardPosition targetPosition);
}
