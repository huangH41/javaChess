package chess.base;

import chess.chessPiece.ChessPiece;

// TODO Consider to somehow merge this class functionality with the board class later
// this is a temporary class to avoid breaking the board class functionality
public class BoardPlot {

    private Board board;
    private Plot[][] boardPlots = new Plot[8][8];

    public BoardPlot(Board board){
        this.board = board;
        newBoardPlot();
        initializePlotGuardStatus(board);
    }

    public Plot getPlot(BoardPosition position){
        return this.boardPlots[position.getRow() - 1][position.getColumn() - 1];
    }

    /**
     * assign guarded status to true based on chess color and add the total number of piece that
     * are guarding a plot
     *
     * @param boardPlot         boardPlot to set the plot guarded status
     * @param guardedPosition   Plot position to set as guarded by chess piece color
     * @param pieceColor        piece color that are guarding the plot
     */
    public static void setGuardedByColor(BoardPlot boardPlot, BoardPosition guardedPosition, ChessPieceColor pieceColor){
        Plot plot = boardPlot.getPlot(guardedPosition);
        if(pieceColor == ChessPieceColor.WHITE){
            plot.setGuardedByWhite(true);
        } else {
            plot.setGuardedByBlack(true);
        }
    }

    public void initializePlotGuardStatus(Board board){
        for (int row = 8; row >= 1; row--){
            for (int column = 1; column <= 8; column++){
                ChessPiece chessPiece = board.getPiece(new BoardPosition(row, column));
                // TODO to print guarded area, specify the piece you want to check here
                if(chessPiece != null && chessPiece.getPieceRank() == ChessPieceRank.ROOK) chessPiece.markGuardedPlot(this, board);
            }
        }
    }

    public void newBoardPlot(){
        for (int row = 8; row >= 1; row--){
            for (int column = 1; column <= 8; column++){
                boardPlots[row - 1][column - 1] = new Plot(new BoardPosition(row, column));
            }
        }
    }
}
