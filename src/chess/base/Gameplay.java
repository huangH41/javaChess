package chess.base;

import chess.base.exceptions.IllegalNotationException;
import chess.base.exceptions.InvalidMoveException;
import chess.base.exceptions.InvalidPromotionException;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.Pawn;

public class Gameplay {
    public boolean verifyUserInputs(String inputtedCoordinates) throws InvalidMoveException, IllegalNotationException {
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

    public Pawn getPawnToPromote(BoardPosition position, ChessPiece piece, ChessPieceRank promotionRank) {
        if (promotionRank == null && position.getRow() != (9 - piece.getChessColor().getStartPosition())) {
            return null;
        } if (piece instanceof Pawn && verifyPawnPromotable(position, (Pawn) piece, promotionRank)) {
            return (Pawn) piece;
        } return null;
    }

    public ChessPieceRank getPromotionRank(String inputtedCoordinates) throws IllegalNotationException {
        if (inputtedCoordinates.length() == 6 && inputtedCoordinates.matches("^[A-H][1-8][-][A-H][1-8][RNBQ]$")) {
            return ChessPieceRank.getPieceRankByInitial(inputtedCoordinates.charAt(5));
        } else if (inputtedCoordinates.length() == 5 && inputtedCoordinates.matches("^[A-H][1-8][-][A-H][1-8]$")) {
            return null;
        } throw IllegalNotationException.userInputMismatch(true);
    }

    private boolean verifyPawnPromotable(BoardPosition targetCoordinate, Pawn currentPawn, ChessPieceRank newRank) throws InvalidMoveException {
        int opponentBaseRow = 9 - currentPawn.getChessColor().getStartPosition();
        if (!currentPawn.isPawnPromotable(false) && (targetCoordinate.getRow() != opponentBaseRow) && (newRank != null)) {
            throw InvalidPromotionException.notPromotable();
        } else if (currentPawn.isPawnPromotable(false) && (targetCoordinate.getRow() == opponentBaseRow) && newRank == null) {
            throw InvalidPromotionException.notPromotedYet();
        } return true;
    }

    public void verifyKingSafetyState(Board board) {
        if (board.isKingUnderCheckState(board.getBlackKing())) {
            System.out.println("Black King is getting check!");
        }
        if (board.isKingUnderCheckState(board.getWhiteKing())) {
            System.out.println("White King is getting check!");
        }
    }
}
