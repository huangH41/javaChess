package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardFactory;
import chess.game.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

class MainTest extends ChessPieceTestEssentials {
    private final InputStream system = System.in;

    @Test
    void regularMoves() {
        Main m = new Main();
        try {
            moveAndEvaluate(m, "A1-A2", false);
            moveAndEvaluate(m, "A2-A3", true);
            moveAndEvaluate(m, "B2-B3", false);
            moveAndEvaluate(m, "E7-E5", true);
            moveAndEvaluate(m, "E4-E5", false);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            Assert.fail("Unable to start the game!");
        }
    }

    @Test
    void whitePromotionMoves() {
        Main m = new Main();
        try {
            moveAndEvaluate(m, "H2-H4R", false);
            moveAndEvaluate(m, "H2-H4Y", false);
            moveAndEvaluate(m, "H2-H4", true);
            moveAndEvaluate(m, "E7-E5", true);
            moveAndEvaluate(m, "H4-H5", true);
            moveAndEvaluate(m, "G7-G6", true);
            moveAndEvaluate(m, "F2-F4", true);
            moveAndEvaluate(m, "H7-H6", true);
            moveAndEvaluate(m, "H5-G6", true);
            moveAndEvaluate(m, "H6-H5", true);
            moveAndEvaluate(m, "H1-H5", true);
            moveAndEvaluate(m, "G8-F6", true);
            moveAndEvaluate(m, "G6-G7", true);
            moveAndEvaluate(m, "F8-B4", true);
            moveAndEvaluate(m, "H5-F5", true);
            moveAndEvaluate(m, "E5-F4", true);
            moveAndEvaluate(m, "G7-G8", false);
            moveAndEvaluate(m, "G7-G8K", false);
            moveAndEvaluate(m, "G7-G8P", false);
            moveAndEvaluate(m, "G7-G8R", true);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            Assert.fail("Unable to start the game!");
        }
    }

    @Test
    void blackPromotionMoves() {
        Main m = new Main();
        try {
            moveAndEvaluate(m, "G2-G3", true);
            moveAndEvaluate(m, "C7-C5", true);
            moveAndEvaluate(m, "D2-D4", true);
            moveAndEvaluate(m, "D7-D5", true);
            moveAndEvaluate(m, "D4-C5", true);
            moveAndEvaluate(m, "D5-D4", true);
            moveAndEvaluate(m, "C2-C4", true);
            moveAndEvaluate(m, "D4-C3", true);
            moveAndEvaluate(m, "D1-B3", true);
            moveAndEvaluate(m, "D8-D7R", false);
            moveAndEvaluate(m, "D8-D7", true);
            moveAndEvaluate(m, "C1-G5", true);
            moveAndEvaluate(m, "C3-C2Q", false);
            moveAndEvaluate(m, "C3-C2Y", false);
            moveAndEvaluate(m, "C3-C2", true);
            moveAndEvaluate(m, "B1-D2", true);
            moveAndEvaluate(m, "C2-C1", false);
            moveAndEvaluate(m, "C2-C1K", false);
            moveAndEvaluate(m, "C2-C1P", false);
            moveAndEvaluate(m, "C2-C1Q", true);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            Assert.fail("Unable to start the game!");
        }
    }

    private void moveAndEvaluate(Main m, String coordinates, boolean status) throws IllegalAccessException {
        assertThat(execute(m, coordinates), status, "Pawn moved properly");
    }

    private boolean execute(Main m, String coordinates) {
        boolean isMoving = false;
        try {
            Board currBoard = BoardFactory.copyBoard(m.getBoard());
            System.out.println("Current piece color: " + m.getBoard().getCurrentColor());
            System.out.println("-> Input coordinate: " + coordinates);

            assertor.drawBoard(m.getBoard());
            m.executeUserInputs(coordinates);
            assertor.drawBoard(m.getBoard());

            isMoving = (!m.getBoard().equals(currBoard));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("---------------------");
        }
        return isMoving;
    }
}

