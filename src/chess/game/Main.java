package chess.game;

import chess.base.*;
import chess.base.exceptions.InvalidMoveException;
import chess.base.exceptions.IllegalNotationException;
import chess.base.ChessPiece;
import chess.chessPiece.Pawn;

import java.util.Scanner;

public class Main {
    private final Board board;
    private final Gameplay gameplay;
    private final Scanner scan = new Scanner(System.in);

    public Main() {
        board = new Board();
        gameplay = new Gameplay();
    }

    public Board getBoard() {
        return board;
    }

    public static void newGame(Main main) throws IllegalAccessException {
        if (main == null) {
            throw new IllegalAccessException("No game available");
        }
        main.gamePhase();
    }

    public static void main(String[] args) throws IllegalAccessException {
        newGame(new Main());
    }

    private void gamePhase() {
        boolean continued = true;
        do {
            BoardPlot.resetBoardPlotGuardStatus(board);

            drawBoard();
            String inputtedCoordinates = performUserInputs();

            try {
                continued = executeUserInputs(inputtedCoordinates);
                if (!continued) {
                    break;
                }
            } catch (InvalidMoveException | IllegalNotationException ex) {
                System.err.println(ex.getMessage());
            }
        } while (continued);
    }

    public boolean executeUserInputs(String inputtedCoordinates) throws InvalidMoveException, IllegalNotationException {
        if (!gameplay.verifyUserInputs(inputtedCoordinates)){
            throw IllegalNotationException.userInputMismatch();
        }

        ChessPieceRank promotionRank = gameplay.getPromotionRank(inputtedCoordinates);
        String[] coordinates = gameplay.getUserInputtedCoordinates(inputtedCoordinates);
        BoardPosition source = new BoardPosition(coordinates[0]);
        BoardPosition destination = new BoardPosition(coordinates[1]);

        movePiece(source, destination, promotionRank);

        System.out.println(String.format("Piece moved from %s to %s!", source, destination));

        board.switchColor();
        BoardPlot.resetBoardPlotGuardStatus(board);
        gameplay.verifyKingSafetyState(board, (board.getCurrentColor()));

        return !Gameplay.isGameEnded(board, (board.getCurrentColor()));
    }

    private void drawBoard() {
        System.out.println("\n\n\n\n\n\n\n");
        System.out.println(BoardDrawer.drawBoardGuardedPlot(board.getBoardPlot()));
        System.out.println("\n\n\n");
        System.out.println(BoardDrawer.drawBoard(board));
    }

    private String performUserInputs() {
        String input = "";
        do {
            System.out.printf("Input %s player [ex: A2-A3]: ", board.getCurrentColor());
            try {
                input = scan.nextLine();
            } catch (Exception ex) {
                continue;
            }
        } while (input.isEmpty());
        return input;
    }

    private ChessPiece getValidChessPiece(BoardPosition source) {
        ChessPiece piece = board.getPiece(source);
        if (piece == null) {
            throw new InvalidMoveException("You tried to move an empty piece!");
        } if (piece.getChessColor() != board.getCurrentColor()) {
            throw new InvalidMoveException(String.format("You moved an opponent board! (%s)", piece.toString()));
        }
        return piece;
    }

    private void movePiece(BoardPosition source, BoardPosition destination, ChessPieceRank promotionRank) {
        ChessPiece piece = getValidChessPiece(source);
        Pawn currentPawn = gameplay.getPawnToPromote(destination, piece, promotionRank);

        piece.move(destination, board);

        if (currentPawn != null && currentPawn.isPawnPromotable(true) && promotionRank != null) {
            piece = currentPawn.promote(promotionRank);
            board.setPiece(piece.getPosition(), piece);
        }
    }
}
