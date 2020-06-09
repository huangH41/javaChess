package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

import java.util.Scanner;

public class Main {
    private final Board board;
    private final Scanner scan = new Scanner(System.in);

    public Main() {
        board = new Board();
    }

    public static void main(String[] args) {
        new Main().gamePhase();
    }

    private void gamePhase() {
        do {
            BoardPlot.unsetBoardPlotGuardedStatus(board);
            BoardPlot.setBoardPlotGuardedStatus(board);

            System.out.println("\n\n\n\n\n\n\n");
            System.out.println(BoardDrawer.drawBoardGuardedPlot(board.getBoardPlot()));
            System.out.println("\n\n\n");
            System.out.println(BoardDrawer.drawBoard(board));
            System.out.printf("Input %s player [ex: A2-A3] : ", board.getCurrentColor());

            String inputtedCoordinates = scan.nextLine();
            if (inputtedCoordinates.isEmpty()) {
                continue;
            }

            try {
                String[] coordinates = inputtedCoordinates.split("-");
                if (coordinates.length != 2) {
                    throw new InvalidMoveException("Invalid movement notation!");
                }
                System.out.println(String.format("Piece moved from %s to %s!", coordinates[0], coordinates[1]));

                ChessPiece piece = board.getPiece(new BoardPosition(coordinates[0]));
                if (piece == null) {
                    throw new InvalidMoveException("You tried to move an empty piece!");
                }

                if (piece.getChessColor() == board.getCurrentColor()) {
                    piece.move(new BoardPosition(coordinates[1]), board);
                    board.switchColor();

                    if (King.isKingUnderCheckState(board, board.getBlackKing())) {
                        System.out.println("Black King is getting check!");
                    }
                    if (King.isKingUnderCheckState(board, board.getWhiteKing())) {
                        System.out.println("White King is getting check!");
                    }
                } else {
                    throw new InvalidMoveException(String.format("You moved an opponent board! (%s)", piece.toString()));
                }
            } catch (InvalidMoveException ex) {
                System.err.println(ex.getMessage());
            }
        } while (true);
    }
}
