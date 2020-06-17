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
        return getPieceFullName();
    }

    /**
     * Get piece full name that contains piece color, piece rank, and piece position
     * @return
     */
    public String getPieceFullName() {
        return getFullPieceName(getPieceColor(), getPieceRank(), getPiecePosition());
    }

    public String getPieceName() {
        return getPieceName(getPieceColor(), getPieceRank());
    }

    public static String getFullPieceName(ChessPieceColor pieceColor, ChessPieceRank pieceRank, BoardPosition piecePosition) {
        return String.format("%s %s [%s]", pieceColor, pieceRank, piecePosition);
    }

    public static String getPieceName(ChessPieceColor pieceColor, ChessPieceRank pieceRank) {
        return String.format("%s %s", pieceColor, pieceRank);
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
