package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Rook;

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
            System.out.printf("Input %s player [ex: A2-A3] : ", board.getCurrentColor());
            String inputtedCoordinates = scan.nextLine();
            String[] coordinates = inputtedCoordinates.split("-");
            System.out.println(String.format("Piece moved frm %s to %s!", coordinates[0], coordinates[1]));

            try {
                ChessPiece p = board.getPiece(new BoardPosition(coordinates[0]));
                if (p.getChessColor() == board.getCurrentColor()) {
                    p.move(new BoardPosition(coordinates[1]), board);
                    board.switchColor();
                } else {
                    throw new InvalidMoveException(String.format("You moved an opponent board! (%s)", p.toString()));
                }
            } catch (InvalidMoveException ex) {
                System.err.println(ex.toString());
            }
        } while (true);
    }
}
