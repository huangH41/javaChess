package chess.base;

// TODO Consider to somehow merge this class functionality with the board class later
// this is a temporary class to avoid breaking the board class functionality
public class BoardPlot {

    private Board board;
    private BoardPlot[][] boardPlots = new BoardPlot[8][8];
    private boolean guardedByWhite = false, guardedByBlack = false;

    public BoardPlot(Board board){
        this.board = board;
    }

    public boolean isGuardedByWhite() {
        return guardedByWhite;
    }

    public BoardPlot setGuardedByWhite(boolean guardedByWhite) {
        this.guardedByWhite = guardedByWhite;
        return this;
    }

    public boolean isGuardedByBlack() {
        return guardedByBlack;
    }

    public BoardPlot setGuardedByBlack(boolean guardedByBlack) {
        this.guardedByBlack = guardedByBlack;
        return this;
    }


}
