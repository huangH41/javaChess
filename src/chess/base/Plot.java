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
        guardingWhitePieceTotal++;
    }

    public void unsetGuardedByWhite(boolean guardedByWhite) {
        guardingWhitePieceTotal--;
        if(guardingWhitePieceTotal == 0) this.guardedByWhite = false;
    }

    public boolean isGuardedByBlack() {
        return guardedByBlack;
    }

    public void setGuardedByBlack(boolean guardedByBlack) {
        this.guardedByBlack = guardedByBlack;
        guardingBlackPieceTotal++;
    }

    public void unsetGuardedByBlack(boolean guardedByBlack) {
        guardingBlackPieceTotal--;
        if(guardingBlackPieceTotal == 0) this.guardedByBlack = false;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition position) {
        this.position = position;
    }

    public int getGuardingWhitePieceTotal() {
        return guardingWhitePieceTotal;
    }

    public int getGuardingBlackPieceTotal() {
        return guardingBlackPieceTotal;
    }

    //TODO Temporary method, alter or delete it later
    // used to print the view guarded only board
    public boolean isGuarded(){
        return (this.isGuardedByBlack() || this.isGuardedByWhite()) ? true : false;
    }
}