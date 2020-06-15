package chess.base;

import chess.base.exceptions.IllegalNotationException;
import chess.base.exceptions.InvalidMoveException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.KingCheckState;
import chess.chessPiece.Pawn;
import chess.chessPiece.King;

public class Gameplay {
    public Pawn getPawnToPromote(BoardPosition position, ChessPiece piece, ChessPieceRank promotionRank) {
        if (piece == null || promotionRank == null) {
            return null;
        } if (piece instanceof Pawn && verifyPawnPromotable(position, (Pawn) piece, promotionRank)) {
            return (Pawn) piece;
        } return null;
    }

    private boolean verifyPawnPromotable(BoardPosition targetCoordinate, Pawn currentPawn, ChessPieceRank newRank) throws InvalidMoveException {
        if (!currentPawn.isPawnPromotable() && (targetCoordinate.getRow() != 8) && newRank != null) {
            throw new InvalidMoveException("Your pawn is far away from promotion!");
        } else if (currentPawn.isPawnPromotable() && (targetCoordinate.getRow() == 8) && newRank == null) {
            throw new InvalidMoveException("Your pawn must be promoted to higher-tier pieces!");
        } return true;
    }

    public void verifyKingSafetyState(Board board, King king) {
        if(KingCheckState.isKingUnderCheckState(board, king)) {
            if(board.getCurrentColor() == ChessPieceColor.WHITE) {
                System.out.println("White King is getting check!");
            } else {
                System.out.println("Black King is getting check!");
            }
        }
    }

    public boolean verifyUserInputs(String inputtedCoordinates) {
        if (inputtedCoordinates.isEmpty() || !(inputtedCoordinates.length() >= 5 && inputtedCoordinates.length() <= 6)
                || inputtedCoordinates.split("-").length != 2) {
            throw IllegalNotationException.userInputMismatch();
        } else if (inputtedCoordinates.matches("^[A-H][1-8][-][A-H][1-8][RNBQ]?$")) {
            return true;
        } throw IllegalNotationException.userInputMismatch();
    }

    public String[] getUserInputtedCoordinates(String inputtedCoordinates) {
        if (verifyUserInputs(inputtedCoordinates)) {
            return inputtedCoordinates.substring(0, 5).split("-");
        } else {
            throw IllegalNotationException.userInputMismatch();
        }
    }

    public ChessPieceRank getPromotionRank(String inputtedCoordinates) {
        if (inputtedCoordinates.length() == 6 && inputtedCoordinates.matches("^[A-H][1-8][-][A-H][1-8][RNBQ]$")) {
            return ChessPieceRank.getPieceRankByInitial(inputtedCoordinates.charAt(5));
        } throw IllegalNotationException.userInputMismatch(true);
    }

    public static boolean isGameEnded(Board board, King kingPiece) {
        if(KingCheckState.isStalemate(board, kingPiece)) {
            System.out.println(String.format("Stalemate!! %s don't have any legal move", kingPiece));
            System.out.println("Game result: draw");
            return true;
        } else if(KingCheckState.isCheckmate(board, kingPiece)) {
            System.out.println(String.format("%s is checkmated by opponent", kingPiece));
            System.out.println(String.format("Game result: %s win the game", board.getCurrentColor() == ChessPieceColor.WHITE
                    ? ChessPieceColor.BLACK : ChessPieceColor.WHITE));
            return true;
        }
        return false;
    }
}
