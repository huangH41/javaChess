package chess.unitTesting;

import chess.base.BoardPosition;
import chess.base.Gameplay;
import chess.base.exceptions.IllegalNotationException;
import org.junit.jupiter.api.Test;

class GameplayTest extends ChessPieceTestEssentials {
    @Test
    void verifyUserInputs() {
        assert (verifyUserInputs("A1-A8"));
        assert (!verifyUserInputs("AZA"));
        assert (!verifyUserInputs("A1 A8"));
        assert (!verifyUserInputs("A1-98"));
        assert (!verifyUserInputs("a1-a8"));
        assert (!verifyUserInputs("a1-A8"));
        assert (!verifyUserInputs("a1 A8"));
        assert (!verifyUserInputs("YY-A8"));
        assert (!verifyUserInputs("AA-A8"));
        assert (!verifyUserInputs("AA-AC"));
        assert (!verifyUserInputs("A1-A9"));
        assert (!verifyUserInputs("9A-A9"));
        assert (!verifyUserInputs("A1-A9"));
        assert (verifyUserInputs("A1-A8R"));
        assert (verifyUserInputs("A1-A8B"));
        assert (verifyUserInputs("A1-A8Q"));
        assert (verifyUserInputs("A1-A8N"));
        assert (!verifyUserInputs("A1-A8K"));
        assert (!verifyUserInputs("A1-A8P"));
        assert (!verifyUserInputs("A1-A88"));
        assert (!verifyUserInputs("A1Y-A8"));
        assert (!verifyUserInputs("A1-A8YY"));
        assert (!verifyUserInputs("A1-A8RR"));
        assert (!verifyUserInputs("ASOYYY"));
        assert (!verifyUserInputs("A1-M8Z"));
    }

    @Test
    void getPromotionRank() {
        assert (!getPromotionRank("A1-A8"));
        assert (!getPromotionRank("A1-A8P"));
        assert (getPromotionRank("A1-A8R"));
        assert (getPromotionRank("A1-A8B"));
        assert (getPromotionRank("A1-A8N"));
        assert (getPromotionRank("A1-A8Q"));
        assert (!getPromotionRank("A1-A8K"));
        assert (!getPromotionRank("A1-A9R"));
        assert (!getPromotionRank("A1-A8Z"));
        assert (!getPromotionRank("A1-A8Zaaa"));
    }

    @Test
    void getUserInputtedCoordinates() {
        assert (getUserInputtedCoordinates("A3-B2"));
        assert (getUserInputtedCoordinates("E3-C2"));
        assert (!getUserInputtedCoordinates("A3-7H"));
        assert (!getUserInputtedCoordinates("A3-H7A"));
        assert (getUserInputtedCoordinates("A3-H5Q"));
        assert (getUserInputtedCoordinates("A3-H7"));
        assert (!getUserInputtedCoordinates("3A-7H"));
        assert (!getUserInputtedCoordinates("A9-H4"));
        assert (!getUserInputtedCoordinates("A3-79"));
        assert (!getUserInputtedCoordinates("A3-ZH"));
        assert (!getUserInputtedCoordinates("A3-Z7"));
        assert (!getUserInputtedCoordinates("A3-O"));
        assert (!getUserInputtedCoordinates("A3 E2"));
    }

    private boolean getUserInputtedCoordinates(String inputtedCoordinates) {
        if (verifyUserInputs(inputtedCoordinates)) {
            String[] splits =  inputtedCoordinates.substring(0, 5).split("-");
            assert (inputtedCoordinates.substring(0, 5).equals(String.format("%s-%s", splits[0], splits[1])));
            assert (validateCoordinate(splits[0]));
            assert (validateCoordinate(splits[1]));
            return true;
        } else {
            return false;
        }
    }

    private boolean validateCoordinate(String coordinate) {
        try {
            BoardPosition position = new BoardPosition(coordinate);
            return position.toString().equals(coordinate);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean getPromotionRank(String inputtedCoordinates) {
        Gameplay play = new Gameplay();
        try {
            return play.getPromotionRank(inputtedCoordinates) != null;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verifyUserInputs(String inputs) {
        Gameplay play = new Gameplay();
        try {
            return play.verifyUserInputs(inputs);
        } catch (Exception e) {
            return false;
        }
    }
}
