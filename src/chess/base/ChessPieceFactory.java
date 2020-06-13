package chess.base;

import chess.chessPiece.*;

public class ChessPieceFactory {
    public static ChessPiece newPiece(ChessPieceColor color, ChessPieceRank rank, BoardPosition position) throws IllegalArgumentException {
        switch (rank) {
            case KING:
                return new King(color, position);
            case QUEEN:
                return new Queen(color, position);
            case BISHOP:
                return new Bishop(color, position);
            case KNIGHT:
                return new Knight(color, position);
            case ROOK:
                return new Rook(color, position);
            case PAWN:
                return new Pawn(color, position);
            default:
                throw new IllegalArgumentException("Invalid piece rank!");
        }
    }

    //TODO this method still doing shallow copy, create new objeck to deep copy each attribute
    public static ChessPiece copyChessPiece(ChessPiece chessPiece) {
        return ChessPieceFactory.newPiece(
                chessPiece.getChessColor(),
                chessPiece.getPieceRank(),
                new BoardPosition(chessPiece.getPosition().toString()));
    }

    public static ChessPiece defineWhitePiece(ChessPieceRank rank, BoardPosition position) {
        return newPiece(ChessPieceColor.WHITE, rank, position);
    }

    public static ChessPiece defineBlackPiece(ChessPieceRank rank, BoardPosition position) {
        return newPiece(ChessPieceColor.BLACK, rank, position);
    }
}
