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

    /**
     * Check guard status of the piece
     * @return is guarded either by black or white
     */
    public boolean getGuardStatus() {
        return isGuardedByBlack() || isGuardedByWhite();
    }

    /**
     * Check guard status of the piece by opponent piece
     * @return is guarded either by black (if white) or white (if black)
     */
    public boolean getGuardStatus(ChessPieceColor color) {
        return color == ChessPieceColor.WHITE ? guardedByBlack : guardedByWhite;
    }

    public void setGuardStatus(ChessPieceColor color, boolean status) {
        if (color == ChessPieceColor.WHITE) {
            setGuardedByWhite(status);
        } else {
            setGuardedByBlack(status);
        }
    }

    public void unsetGuardStatus(ChessPieceColor color) {
        if (color == ChessPieceColor.WHITE) {
            unsetGuardedByWhite();
        } else {
            unsetGuardedByBlack();
        }
    }

    public boolean isGuardedByWhite() {
        return guardedByWhite;
    }

    private void setGuardedByWhite(boolean guardedByWhite) {
        this.guardedByWhite = guardedByWhite;
        guardingWhitePieceTotal++;
    }

    private void unsetGuardedByWhite() {
        guardingWhitePieceTotal--;
        if(guardingWhitePieceTotal == 0) this.guardedByWhite = false;
    }

    public boolean isGuardedByBlack() {
        return guardedByBlack;
    }

    private void setGuardedByBlack(boolean guardedByBlack) {
        this.guardedByBlack = guardedByBlack;
        guardingBlackPieceTotal++;
    }

    private void unsetGuardedByBlack() {
        guardingBlackPieceTotal--;
        if(guardingBlackPieceTotal == 0) this.guardedByBlack = false;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void setPosition(BoardPosition position) {
        this.position = position;
    }
}