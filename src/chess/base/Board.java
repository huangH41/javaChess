package chess.base;

import chess.chessPiece.*;
import chess.base.ChessPieceColor;

public class Board {
    private BoardPosition position;

    // TODO BUAT 2D Array Variable berisikan chessPiece
    private ChessPiece[][] pieceBoard = new ChessPiece[8][8];

    public Board() {
        initializeChessPiece();
    }

    public static boolean validatePosition(int col, int row) {
        if (row < 1 || row > 8) {
            return false;
        } else return col >= 1 && col <= 8;
    }

    // TODO BUAT initializeChessPiece()
    public void initializeChessPiece() {
        // let start from white_rook_left
        pieceBoard[0][0] = defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition(0, 0));
        pieceBoard[0][7] = defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition(0, 7));
        pieceBoard[0][1] = defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition(0, 1));
        pieceBoard[0][6] = defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition(0, 6));
        pieceBoard[0][2] = defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition(0, 2));
        pieceBoard[0][5] = defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition(0, 5));
        pieceBoard[0][3] = defineWhitePawn(ChessPieceRank.KING, new BoardPosition(0, 3));
        pieceBoard[0][4] = defineWhitePawn(ChessPieceRank.QUEEN, new BoardPosition(0, 4));
        for (int i = 0; i < 7; i++) {
            pieceBoard[1][i] = defineWhitePawn(ChessPieceRank.PAWN, new BoardPosition(1, i));
        }

        pieceBoard[7][0] = defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition(7, 0));
        pieceBoard[7][7] = defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition(7, 7));
        pieceBoard[7][1] = defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition(7, 1));
        pieceBoard[7][6] = defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition(7, 6));
        pieceBoard[7][2] = defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition(7, 2));
        pieceBoard[7][5] = defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition(7, 5));
        pieceBoard[7][3] = defineBlackPawn(ChessPieceRank.KING, new BoardPosition(7, 3));
        pieceBoard[7][4] = defineBlackPawn(ChessPieceRank.QUEEN, new BoardPosition(7, 4));
        for (int i = 0; i < 7; i++) {
            pieceBoard[6][i] = defineBlackPawn(ChessPieceRank.PAWN, new BoardPosition(6, i));
        }
    }

    public void movePiece(ChessPiece piece, BoardPosition destPosition) {
        ChessPiece pieceAtDestination = pieceBoard[destPosition.getRow()][destPosition.getColumn()];
        int oldRow = piece.getPosition().getRow(), oldColumn = piece.getPosition().getColumn();

        if (pieceAtDestination != null) {
            if (piece.isOpponent(pieceAtDestination)) {
                // log attack

            }
            pieceBoard[destPosition.getRow()][destPosition.getColumn()] = piece;
            piece.setPosition(destPosition);
            // do piece move
        }
        pieceBoard[oldRow][oldColumn] = null;
    }

    public ChessPiece defineWhitePawn(ChessPieceRank rank, BoardPosition position) {
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

    public ChessPiece defineBlackPawn(ChessPieceRank rank, BoardPosition position) {
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

    public void placePiece(ChessPiece piece, BoardPosition position) {
        int row = position.getRow(), column = position.getColumn();
        piece.setPosition(new BoardPosition(row, column));
        pieceBoard[row][column] = piece;
    }
}
