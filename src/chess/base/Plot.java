package chess.base;

public class Plot {
    private boolean guardedByWhite, guardedByBlack;
    private BoardPosition position;
    private int guardingWhitePieceTotal = 0;
    private int guardingBlackPieceTotal = 0;

    public Plot(BoardPosition position){
        this.position = position;
        guardedByBlack = guardedByWhite = false;
    }

    public boolean isGuardedByWhite() {
        return guardedByWhite;
    }

    public void setGuardedByWhite(boolean guardedByWhite) {
        this.guardedByWhite = guardedByWhite;
    }

    public boolean isGuardedByBlack() {
        return guardedByBlack;
    }

    public void setGuardedByBlack(boolean guardedByBlack) {
        this.guardedByBlack = guardedByBlack;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition position) {
        this.position = position;
    }

    public void addGuardingWhitePiece(){
        this.guardingWhitePieceTotal++;
    }

    public int getGuardingWhitePieceTotal() {
        return guardingWhitePieceTotal;
    }

    public void addGuardingBlackPiece(){
        this.guardingBlackPieceTotal++;
    }

    public int getGuardingBlackPieceTotal() {
        return guardingBlackPieceTotal;
    }

    //TODO Temporary method, alter or delete it later
    // used to print the view guarded only board
    public boolean isGuarded(Plot plot){
        return (plot.isGuardedByBlack() || plot.isGuardedByWhite()) ? true : false;
    }
}