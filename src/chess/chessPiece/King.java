package chess.chessPiece;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.base.PieceMovement;
import chess.base.ChessPieceColor;

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
	private boolean isMonoOmniDirectionalMove(int dstCol, int dstRow) {
		return PieceMovement.getRelativeColDistance(this, dstCol) == 1
				|| PieceMovement.getRelativeRowDistance(this, dstRow) == 1;
	}

	@Override
	protected void move(int dstCol, int dstRow) {
		if (Board.validatePosition(dstCol, dstRow) && isMonoOmniDirectionalMove(dstCol, dstRow)) {
			// TODO do a recursive move to detect any obstacle and possible attacked by enemy pawn
		}
	}

	public enum KingCheckState {
		SAFE, CHECK, CHECKMATE
	}

}
