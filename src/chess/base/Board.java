package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

public class Board {

    private ChessPiece[][] pieceBoard = new ChessPiece[8][8];

    private King blackKing, whiteKing;
    private ChessPieceColor currentColor = ChessPieceColor.WHITE;
    private BoardPlot boardPlot;

    public Board() {
        initializeChessPiece();
        boardPlot = new BoardPlot(this);
    }

    public BoardPlot getBoardPlot() {
        return boardPlot;
    }

    public static boolean isBoardValidPosition(BoardPosition targetPosition) {
        if (targetPosition.getRow() < 1 || targetPosition.getRow() > 8) {
            return false;
        } else return targetPosition.getColumn() >= 1 && targetPosition.getColumn() <= 8;
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

    public static boolean hasChessPiece(ChessPiece[][] pieceBoard, BoardPosition targetPosition) {
        return pieceBoard[targetPosition.getRow()][targetPosition.getColumn()] != null;
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
        whiteKing = (King) ChessPiece.defineWhitePawn(ChessPieceRank.KING, new BoardPosition("E1"));
        pieceBoard[0][0] = ChessPiece.defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition("A1"));
        pieceBoard[0][7] = ChessPiece.defineWhitePawn(ChessPieceRank.ROOK, new BoardPosition("H1"));
        pieceBoard[0][1] = ChessPiece.defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition("B1"));
        pieceBoard[0][6] = ChessPiece.defineWhitePawn(ChessPieceRank.KNIGHT, new BoardPosition("G1"));
        pieceBoard[0][2] = ChessPiece.defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition("C1"));
        pieceBoard[0][5] = ChessPiece.defineWhitePawn(ChessPieceRank.BISHOP, new BoardPosition("F1"));
        pieceBoard[0][3] = ChessPiece.defineWhitePawn(ChessPieceRank.QUEEN, new BoardPosition("D1"));
        pieceBoard[0][4] = whiteKing;
        for (int i = 0; i < 8; i++) {
            pieceBoard[1][i] = ChessPiece.defineWhitePawn(ChessPieceRank.PAWN, new BoardPosition(2, i + 1));
        }

        // for black piece
        blackKing = (King) ChessPiece.defineBlackPawn(ChessPieceRank.KING, new BoardPosition("E8"));
        pieceBoard[7][0] = ChessPiece.defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition("A8"));
        pieceBoard[7][7] = ChessPiece.defineBlackPawn(ChessPieceRank.ROOK, new BoardPosition("H8"));
        pieceBoard[7][1] = ChessPiece.defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition("B8"));
        pieceBoard[7][6] = ChessPiece.defineBlackPawn(ChessPieceRank.KNIGHT, new BoardPosition("G8"));
        pieceBoard[7][2] = ChessPiece.defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition("C8"));
        pieceBoard[7][5] = ChessPiece.defineBlackPawn(ChessPieceRank.BISHOP, new BoardPosition("F8"));
        pieceBoard[7][3] = ChessPiece.defineBlackPawn(ChessPieceRank.QUEEN, new BoardPosition("D8"));
        pieceBoard[7][4] = blackKing;
        for (int i = 0; i < 8; i++) {
            pieceBoard[6][i] = ChessPiece.defineBlackPawn(ChessPieceRank.PAWN, new BoardPosition(7, i + 1));
        }
    }

    public void movePiece(ChessPiece piece, BoardPosition dstPosition) {
        BoardPosition oldPosition = piece.getPosition();
        toNewPosition(piece, dstPosition);
        removeOldPosition(oldPosition);

        if (!piece.isFirstMove()) {
            piece.hasMoved();
        }
    }

    /**
     * Move a piece relatively from current position
     *
     * @param rowsFromCurrent    rows from current position
     * @param columnsFromCurrent columns from current position
     */
    public void movePieceBy(ChessPiece piece, int rowsFromCurrent, int columnsFromCurrent) throws InvalidMoveException {
        BoardPosition newPosition = piece.getPosition();
        newPosition.setRow(newPosition.getRow() + rowsFromCurrent);
        newPosition.setColumn(newPosition.getColumn() + columnsFromCurrent);
        movePiece(piece, newPosition);
    }

    private void toNewPosition(ChessPiece piece, BoardPosition dstPosition) {
        piece.setPosition(dstPosition);
        setPiece(dstPosition, piece);
    }

    private void removeOldPosition(BoardPosition oldPosition) {
        setPiece(oldPosition, null);
    }

    public ChessPiece getPiece(BoardPosition position) {
        return pieceBoard[position.getRow() - 1][position.getColumn() - 1];
    }

    public void setPiece(BoardPosition position, ChessPiece piece) {
        this.pieceBoard[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    public boolean isOccupied(BoardPosition position) {
        return getPiece(position) != null;
    }

}
