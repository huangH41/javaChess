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
        initializePlotGuardStatus();
    }

    public Plot getPlot(BoardPosition position){
        return this.boardPlots[position.getRow() - 1][position.getColumn() - 1];
    }

    public void initializePlotGuardStatus(){
        for (int row = 8; row >= 1; row--){
            for (int column = 1; column <= 8; column++){
                ChessPiece chessPiece = board.getPiece(new BoardPosition(row, column));
                if(chessPiece != null) chessPiece.markGuardedPlot(this);
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
