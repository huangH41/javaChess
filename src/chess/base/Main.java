package chess.base;

import chess.chessPiece.ChessPiece;

import java.util.Scanner;

public class Main {
    private Board board;
    private Scanner scan = new Scanner(System.in);

    public Main() {
        board = new Board();
    }

    public static void main(String[] args) {
        new Main().gamePhase();
    }

    private void gamePhase() {
        // TODO: This is an infinite loops so this method requires an improvement for @HuangH41...
        do {
            System.out.println(BoardDrawer.drawBoard(board));
            System.out.print("Input [ex: A2-A3] : ");
            String inputtedCoordinates = scan.nextLine();
            String[] coordinates = inputtedCoordinates.split("-");
            System.out.println(String.format("Piece moved frm %s to %s!", coordinates[0], coordinates[1]));
            ChessPiece p = board.getPiece(new BoardPosition(coordinates[0]));
            board.movePiece(p, new BoardPosition(coordinates[1]));
        } while (true);
    }
}
