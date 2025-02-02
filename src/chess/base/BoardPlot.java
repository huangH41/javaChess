package chess.base;

/**
 * This class maintains board plotting functionality where was a spin-off class to avoid breaking the board class functionality
 */
public class BoardPlot {

    private final Board board;
    private final Plot[][] boardPlots = new Plot[8][8];

    public BoardPlot(Board board) {
        this.board = board;
        newBoardPlot();
    }

    /**
     * Set all guarded status on the BoardPlot
     *
     * @param board board to set
     */
    public static void setBoardPlotGuardedStatus(Board board) {
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece chessPiece = board.getPiece(new BoardPosition(row, column));
                if (chessPiece != null) chessPiece.markGuardedPlot(board.getBoardPlot(), board);
            }
        }
    }

    /**
     * Unset all guarded status on the BoardPlot
     *
     * @param board the current Board associated with the BoardPlot
     */
    public static void unsetBoardPlotGuardedStatus(Board board) {
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece chessPiece = board.getPiece(new BoardPosition(row, column));
                if (chessPiece != null) chessPiece.unmarkGuardedPlot(board.getBoardPlot(), board);
            }
        }
    }

    public static void resetBoardPlotGuardStatus(Board board) {
        unsetBoardPlotGuardedStatus(board);
        setBoardPlotGuardedStatus(board);
    }

    /**
     * set guarded status to true based on chess color and add the total number of piece that
     * are guarding boardPlot guardedPosition
     *
     * @param boardPlot       boardPlot to set the plot guarded status
     * @param guardedPosition Plot position to set as guarded by chess piece color
     * @param pieceColor      piece color that are guarding the plot
     */
    public static void setGuardedByColor(BoardPlot boardPlot, BoardPosition guardedPosition, ChessPieceColor pieceColor) {
        Plot plot = boardPlot.getPlot(guardedPosition);
        plot.setGuardStatus(pieceColor, true);
    }

    /**
     * unset guarded status to false based on chess color and substract the total number of piece that
     * are guarding boardPlot guardedPosition
     *
     * @param boardPlot       boardPlot to set the plot guarded status
     * @param guardedPosition Plot position to set as guarded by chess piece color
     * @param pieceColor      piece color that are guarding the plot
     */
    public static void unsetGuardedByColor(BoardPlot boardPlot, BoardPosition guardedPosition, ChessPieceColor pieceColor) {
        Plot plot = boardPlot.getPlot(guardedPosition);
        plot.unsetGuardStatus(pieceColor);
    }

    public Plot getPlot(BoardPosition position) {
        return this.boardPlots[position.getRow() - 1][position.getColumn() - 1];
    }

    public void newBoardPlot() {
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                boardPlots[row - 1][column - 1] = new Plot(new BoardPosition(row, column));
            }
        }
    }
}
