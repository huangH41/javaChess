package chess.base;

import chess.base.exceptions.IllegalNotationException;
import chess.chessPiece.*;

public class ChessPieceFactory {
    public static ChessPiece newPiece(ChessPieceColor color, ChessPieceRank rank, BoardPosition position) throws IllegalNotationException {
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
                throw IllegalNotationException.rank();
        }
    }

    //TODO this method still doing shallow copy, create new objeck to deep copy each attribute
    public static ChessPiece copyChessPiece(ChessPiece chessPiece) {
        ChessPiece copiedPiece = ChessPieceFactory.newPiece(
                chessPiece.getChessColor(),
                chessPiece.getPieceRank(),
                new BoardPosition(chessPiece.getPosition().toString()));

        if(chessPiece.getPieceRank() == ChessPieceRank.KING) setCopiedKingCheckState((King) copiedPiece, (King) chessPiece);
        return copiedPiece;
    }

    private static void setCopiedKingCheckState(King newKing, King toCopyKing) {
        newKing.setCheckState(toCopyKing.isChecked());
    }


    public static ChessPiece defineWhitePiece(ChessPieceRank rank, BoardPosition position) {
        return newPiece(ChessPieceColor.WHITE, rank, position);
    }

    public static ChessPiece defineBlackPiece(ChessPieceRank rank, BoardPosition position) {
        return newPiece(ChessPieceColor.BLACK, rank, position);
    }
}
