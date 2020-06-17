package chess.base;

import chess.base.exceptions.InvalidMoveException;

public class Board {

    private final ChessPiece[][] pieceBoard = new ChessPiece[8][8];

    private ChessPieceColor currentColor = ChessPieceColor.WHITE;
    private final BoardPlot boardPlot;
    private int numOfTurns = 0;

    public Board() {
        BoardFactory.initializeChessPieces(this);
        boardPlot = new BoardPlot(this);
        numOfTurns = 0;
    }

    public BoardPlot getBoardPlot() {
        return boardPlot;
    }

    public ChessPiece[][] getPieceBoard() {
        return this.pieceBoard;
    }

    public ChessPiece getNearestChessPiece(ChessPieceColor color, ChessPieceRank rank) {
        for (ChessPiece[] chessPieceRow : pieceBoard) {
            for (ChessPiece chessPiece : chessPieceRow) {
                if (chessPiece != null && chessPiece.getChessColor() == color && chessPiece.getPieceRank() == rank) {
                    return chessPiece;
                }
            }
        }
        return null;
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

    public ChessPiece getPiece(BoardPosition position) {
        return pieceBoard[position.getRow() - 1][position.getColumn() - 1];
    }

    public void setPiece(BoardPosition position, ChessPiece piece) {
        this.pieceBoard[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }

    public void nextTurn() {
        this.numOfTurns++;
    }

    public boolean isOccupied(BoardPosition position) {
        return getPiece(position) != null;
    }

    public static boolean isBoardValidPosition(BoardPosition targetPosition) {
        if (targetPosition.getRow() < 1 || targetPosition.getRow() > 8) {
            return false;
        } else return targetPosition.getColumn() >= 1 && targetPosition.getColumn() <= 8;
    }

    public int getCurrSideRemainingPieceCount() {
        int currSidePieceTotal = 0;
        for (ChessPiece[] chessPieceRow : pieceBoard) {
            for (ChessPiece chessPiece : chessPieceRow) {
                if (chessPiece != null && chessPiece.getChessColor() == currentColor) {
                    currSidePieceTotal++;
                }
            }
        }

        return currSidePieceTotal;
    }

    /**
     * Move a piece to destination position
     *
     * @param piece       piece to move from
     * @param dstPosition destination position
     */
    public void movePiece(ChessPiece piece, BoardPosition dstPosition) {
        BoardPosition oldPosition = piece.getPosition();
        toNewPosition(piece, dstPosition);
        removeOldPosition(oldPosition);
    }

    /**
     * Move a piece relatively from current position
     *
     * @param rowsFromCurrent    rows from current position
     * @param columnsFromCurrent columns from current position
     */
    public void movePieceBy(ChessPiece piece, int rowsFromCurrent, int columnsFromCurrent) throws InvalidMoveException {
        BoardPosition newPosition = new BoardPosition(piece.getPosition().getRow() + rowsFromCurrent,
                piece.getPosition().getColumn() + columnsFromCurrent);
        movePiece(piece, newPosition);
    }

    private void toNewPosition(ChessPiece piece, BoardPosition dstPosition) {
        piece.setPosition(dstPosition);
        setPiece(dstPosition, piece);
    }

    private void removeOldPosition(BoardPosition oldPosition) {
        setPiece(oldPosition, null);
    }

}
