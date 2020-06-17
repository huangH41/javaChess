package chess.base;

public class ChessPieceProperties {
    private final ChessPieceColor pieceColor;
    private final ChessPieceRank pieceRank;
    private final BoardPosition piecePosition;

    /**
     * Show format for Chess Piece information
     * @param pieceColor     color of piece
     * @param pieceRank      rank of piece
     * @param piecePosition  piece position
     */
    public ChessPieceProperties(ChessPieceColor pieceColor, ChessPieceRank pieceRank, BoardPosition piecePosition) {
        this.pieceColor = pieceColor;
        this.pieceRank = pieceRank;
        this.piecePosition = piecePosition;
    }

    @Override
    public String toString() {
        return String.format("%s %s [%s]", getPieceColor(), getPieceRank(), getPiecePosition());
    }

    public String getPieceName() {
        return String.format("%s %s", getPieceColor(), getPieceRank());
    }

    public ChessPieceColor getPieceColor() {
        return pieceColor;
    }

    public ChessPieceRank getPieceRank() {
        return pieceRank;
    }

    public BoardPosition getPiecePosition() {
        return piecePosition;
    }
}
