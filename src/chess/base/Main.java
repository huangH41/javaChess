package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;

import java.util.Scanner;

public class Main {
    private Board board;
    private Scanner scan = new Scanner(System.in);

    public Main() {
        board = new Board();

    }

    public static void main(String[] args) {
        new Main().gamePhase();
//        new Main();
    }

//    private void performAssertion() {
//        board.movePiece(board.getPiece(new BoardPosition("G2")), new BoardPosition("G4"));
//        board.movePiece(board.getPiece(new BoardPosition("A7")), new BoardPosition("A6"));
//        board.movePiece(board.getPiece(new BoardPosition("G1")), new BoardPosition("F3"));
//        board.movePiece(board.getPiece(new BoardPosition("D7")), new BoardPosition("D5"));
//    }

    private void gamePhase() {
        // TODO: This is an infinite loops so this method requires an improvement for @HuangH41...
//        performAssertion();
        do {

            System.out.println(BoardDrawer.drawBoard(board));
            System.out.println("\n\nView only guarded plot");
            System.out.println(BoardDrawer.drawBoardGuardedPlot(board.getBoardPlot()));

            // Testing here
            String targetPosition = "A3";
            Plot plot = board.getBoardPlot().getPlot(new BoardPosition(targetPosition));
            System.out.println(targetPosition + " is guarded by " + plot.getGuardingWhitePieceTotal() +
                    " white piece & " + plot.getGuardingBlackPieceTotal() + " black piece| isGuarded status: " + plot.isGuarded() + "\n");
            System.out.printf("\nInput %s player [ex: A2-A3] : ", board.getCurrentColor());

            String inputtedCoordinates = scan.nextLine();
            if (inputtedCoordinates.isEmpty()) {
                continue;
            }

            String[] coordinates = inputtedCoordinates.split("-");
            System.out.println(String.format("Piece moved from %s to %s!", coordinates[0], coordinates[1]));

            try {
                ChessPiece p = board.getPiece(new BoardPosition(coordinates[0]));
                if (p == null) {
                    throw new InvalidMoveException("You tried to move an empty piece!");
                }

                if (p.getChessColor() == board.getCurrentColor()) {
                    p.move(new BoardPosition(coordinates[1]), board);
                    board.switchColor();
                } else {
                    throw new InvalidMoveException(String.format("You moved an opponent board! (%s)", p.toString()));
                }
            } catch (InvalidMoveException ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }
}
