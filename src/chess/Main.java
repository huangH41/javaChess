package chess;

import chess.base.Board;
import chess.base.BoardPosition;
import chess.chessPiece.ChessPiece;

public class Main {

	public Main() {
		//int[][] chessBoard = new int[8][8];
		Board board = new Board();
		ChessPiece[][] pieceBoard = board.getPieceBoard();

		// use this
		ChessPiece piece = pieceBoard[1][3];

		// or better this
		ChessPiece newPiece = board.getPiece(new BoardPosition(1, 3));

	}

    public static void main(String[] args) {
    	
    }
}
