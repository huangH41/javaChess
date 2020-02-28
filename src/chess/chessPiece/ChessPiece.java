package chess.chessPiece;

public abstract class ChessPiece {
    private String chessName, chessColor;
    private int coorX, coorY;

    public ChessPiece(String chessName, String chessColor, int coorX, int coorY) {
        this.chessName = chessName;
        this.chessColor = chessColor;
    }

    public String getChessName() {
        return chessName;
    }

    public void setChessName(String chessName) {
        this.chessName = chessName;
    }

    public String getChessColor() {
        return chessColor;
    }

    public void setChessColor(String chessColor) {
        this.chessColor = chessColor;
    }
    
    public int getCoorX() {
		return coorX;
	}

	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}

	public int getCoorY() {
		return coorY;
	}

	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}

	protected void kill(ChessPiece chessPiece) {
        //TODO kill logic have yet to be defined
    }

    abstract protected void move(int dstCoorX, int dstCoorY);
}
