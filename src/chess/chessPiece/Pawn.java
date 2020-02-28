package chess.chessPiece;

public class Pawn extends ChessPiece {
	private Boolean firstMove = true;
	private int maxStep = 2;
	public Pawn(String chessName, String chessColor, int coorX, int coorY) {
		super(chessName, chessColor, coorX, coorY);
	}
	
	@Override
	public void move(int dstCoorX, int dstCoorY) {
		if(!firstMove){
			maxStep = 1;
		}
	}
	
}
