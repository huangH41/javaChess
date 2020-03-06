package chess.chessPiece;

import chess.base.*;

public class King extends ChessPiece {

	private KingCheckState checkState = KingCheckState.SAFE;

	/**
	 * Define King with defined color and position
	 *
	 * @param chessColor black or white?
	 * @param position   initial position of king
	 */
	public King(ChessPieceColor chessColor, BoardPosition position) {
		super(ChessPieceRank.KING, chessColor, position);
	}

	// TODO buat method untuk cek status "CHECK" dan "CHECKMATE"
	public void verifyCheckState() {
		// if any movement caused this to check or checkmate

	}

	public void setCheck(KingCheckState checkState) {
		this.checkState = checkState;
	}

	public KingCheckState isChecked() {
		return checkState;
	}

	/**
	 * Check if king moves in any direction by one block
	 *
	 * @param dstCol destination column
	 * @param dstRow destination row
	 * @return true if king moves any-directional by one block
	 */
	private boolean isMonoOmniDirectionalMove(int dstRow, int dstCol) {
		return PieceMovement.getRelativeColDistance(this, dstCol) == 1
				|| PieceMovement.getRelativeRowDistance(this, dstRow) == 1;
	}

	@Override
	protected void move(int dstRow, int dstCol) {
		if (Board.validatePosition(dstRow, dstCol) && isMonoOmniDirectionalMove(dstCol, dstRow)) {
			// TODO do a simulation move or CHECK test before the king moves
		}
	}

	public enum KingCheckState {
		SAFE, CHECK, CHECKMATE
	}

}
