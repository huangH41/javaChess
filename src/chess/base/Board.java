package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

public class Board {
    //private BoardPosition position;
    private final ChessPiece[][] pieceBoard = new ChessPiece[8][8];
    private King blackKing, whiteKing;
    private ChessPieceColor currentColor = ChessPieceColor.WHITE;

    public Board() {
        initializeChessPiece();
    }

    public static boolean validatePosition(int row, int col) {
        if (row < 1 || row > 8) {
            return false;
        } else return col >= 1 && col <= 8;
    }

    public ChessPiece[][] getPieceBoard() {
        return pieceBoard;
    }

    public King getBlackKing() {
        return blackKing;
    }

    public void setBlackKing(King blackKing) {
        this.blackKing = blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    public void setWhiteKing(King whiteKing) {
        this.whiteKing = whiteKing;
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

    /**
     * Create and place all of chess piece in board
     */
    public void initializeChessPiece() {
        // let start from white_rook_left
        whiteKing = (King) ChessPiece.defineWhitePawn(ChessPieceRank.KING, new BoardPosition(0, 3));
        pieceBoard[0][0] = ChessPiece.defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition(0, 0));
        pieceBoard[0][7] = ChessPiece.defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition(0, 7));
        pieceBoard[0][1] = ChessPiece.defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition(0, 1));
        pieceBoard[0][6] = ChessPiece.defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition(0, 6));
        pieceBoard[0][2] = ChessPiece.defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition(0, 2));
        pieceBoard[0][5] = ChessPiece.defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition(0, 5));
        pieceBoard[0][4] = ChessPiece.defineWhitePawn(ChessPieceRank.QUEEN, new BoardPosition(0, 4));
        pieceBoard[0][3] = whiteKing;
        for (int i = 0; i < 7; i++) {
            pieceBoard[1][i] = ChessPiece.defineWhitePawn(ChessPieceRank.PAWN, new BoardPosition(1, i));
        }

        // for black piece
        blackKing = (King) ChessPiece.defineBlackPawn(ChessPieceRank.KING, new BoardPosition(7, 3));
        pieceBoard[7][0] = ChessPiece.defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition(7, 0));
        pieceBoard[7][7] = ChessPiece.defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition(7, 7));
        pieceBoard[7][1] = ChessPiece.defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition(7, 1));
        pieceBoard[7][6] = ChessPiece.defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition(7, 6));
        pieceBoard[7][2] = ChessPiece.defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition(7, 2));
        pieceBoard[7][5] = ChessPiece.defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition(7, 5));
        pieceBoard[7][4] = ChessPiece.defineBlackPawn(ChessPieceRank.QUEEN, new BoardPosition(7, 4));
        pieceBoard[7][3] = blackKing;
        for (int i = 0; i < 7; i++) {
            pieceBoard[6][i] = ChessPiece.defineBlackPawn(ChessPieceRank.PAWN, new BoardPosition(6, i));
        }
    }

    public void movePiece(ChessPiece piece, BoardPosition destPosition) throws InvalidMoveException {
        if (Board.validatePosition(destPosition.getRow(), destPosition.getColumn())) {
            throw new IndexOutOfBoundsException("Can not move piece outside chess board!");
        }

        ChessPiece pieceAtDestination = pieceBoard[destPosition.getRow()][destPosition.getColumn()];
        int oldRow = piece.getPosition().getRow(), oldColumn = piece.getPosition().getColumn();

        if (pieceAtDestination != null) {
            if (piece.isOpponent(pieceAtDestination)) {
                // log attack

            }
            piece.performMovement(destPosition.getRow(), destPosition.getColumn());
            pieceBoard[destPosition.getRow()][destPosition.getColumn()] = piece;
        }
        pieceBoard[oldRow][oldColumn] = null;
    }

    public ChessPiece getPiece(BoardPosition position) {
        return pieceBoard[position.getRow()][position.getColumn()];
    }

    public boolean isEmptyAtPosition(BoardPosition position) {
        return getPiece(position) == null;
    }

    public void placePiece(ChessPiece piece, BoardPosition position) {
        int row = position.getRow(), column = position.getColumn();
        piece.setPosition(new BoardPosition(row, column));
        pieceBoard[row][column] = piece;
    }
}
