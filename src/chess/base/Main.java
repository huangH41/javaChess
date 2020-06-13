package chess.base;

import chess.base.exceptions.InvalidMoveException;
import chess.base.exceptions.IllegalNotationException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;

import java.util.Scanner;

public class Main {
    private final Board board;
    private final Gameplay gameplay;
    private final Scanner scan = new Scanner(System.in);

    public Main() {
        board = new Board();
        gameplay = new Gameplay();
        gamePhase();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void gamePhase() {
        BoardPlot.resetBoardPlotGuardStatus(board);

        drawBoard();
        String inputtedCoordinates = performUserInputs();

        try {
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
            gameplay.verifyKingSafetyState(board);
        } catch (InvalidMoveException | IllegalNotationException ex) {
            System.err.println(ex.getMessage());
        }

        gamePhase();
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
            System.out.printf("Input %s player [ex: A2-A3] : ", board.getCurrentColor());
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
        } else if (piece.getChessColor() != board.getCurrentColor()) {
            throw new InvalidMoveException(String.format("You moved an opponent board! (%s)", piece.toString()));
        }
        return piece;
    }

    private void movePiece(BoardPosition source, BoardPosition destination, ChessPieceRank promotionRank) {
        ChessPiece piece = getValidChessPiece(source);
        piece.move(destination, board);

        Pawn currentPawn = gameplay.getPawnToPromote(destination, piece, promotionRank);
        if (currentPawn != null && currentPawn.isPawnPromotable()) {
            piece = currentPawn.promote(promotionRank);
            board.setPiece(piece.getPosition(), piece);
        }
    }
}
