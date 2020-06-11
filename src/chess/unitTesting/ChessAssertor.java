package chess.unitTesting;

import chess.base.Board;
import chess.base.BoardDrawer;
import chess.base.BoardPosition;
import chess.base.ChessPieceRank;
import chess.chessPiece.ChessPiece;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ChessAssertor {

    private static final int PREVIEW_DELAY = 2000;

    /**
     * Clear all chess piece in the board
     *
     * @param board Current game board
     * @return empty board
     */
    public Board clearBoard(Board board) {
        for (int row = 7; row >= 0; row--) {
            for (int column = 0; column <= 7; column++) {
                board.getPieceBoard()[row][column] = null;
            }
        }

        return board;
    }

    /**
     * Validate if the targeted position contain expected chess piece
     *
     * @param board Current game board
     * @param targetPosition Position to validate
     * @param rank Expected chess piece rank
     * @return True if the chess piece equals expected chess piece and false if no chess piece found
     * or don't have expected rank
     */
    public boolean isExpectedPiece(Board board, BoardPosition targetPosition, ChessPieceRank rank) {
        ChessPiece targetLocationChessPiece = board.getPiece(targetPosition);
        if(targetLocationChessPiece != null && targetLocationChessPiece.getPieceRank() == rank){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move piece & display board for preview after movement. If you want to skip preview, add false to fourth parameter.
     *
     * @param board          Board to display
     * @param startPosition  Locate a piece at startPosition
     * @param targetPosition Target position for piece movement
     */
    public void movePiece(Board board, String startPosition, String targetPosition) {
        movePiece(board, startPosition, targetPosition, false);
    }

    /**
     * Move piece & display board for preview. There has an option for fast-forward to skip preview.
     *
     * @param board          Board to plot and display
     * @param startPosition  Locate a piece at startPosition
     * @param targetPosition Target position for piece movement
     * @param skipPreview    Skip preview?
     */
    public void movePiece(Board board, String startPosition, String targetPosition, boolean skipPreview) {
        ChessPiece piece = board.getPiece(new BoardPosition(startPosition));
        piece.move(new BoardPosition(targetPosition), board);

        System.out.println();
        drawBoard(board);
        System.out.println(String.format("\nPerform move from %s to %s...", startPosition, targetPosition));
        wait(skipPreview ? 500 : PREVIEW_DELAY);
        System.out.println("\n\n");
    }

    /**
     * Display board guard plot.
     *
     * @param board Board to display it's guard plot
     */
    public void previewGuardedPlot(Board board) {
        drawGuardBoard(board);
        System.out.println("\nDisplay board guard plot after a piece moves...");
        wait(PREVIEW_DELAY);
    }

    public void drawBoard(Board board) {
        System.out.println(BoardDrawer.drawBoard(board));
    }

    public void drawGuardBoard(Board board) {
        System.out.println(BoardDrawer.drawBoardGuardedPlot(board.getBoardPlot()));
    }

    /**
     * Access private method valuers (return values) of a Object.
     *
     * @param object        Object to access private method valuers
     * @param privateMethod Name of private/inaccessible methods
     * @param args          Parameters within private method to access
     * @return Value of an object's private method valuers
     * @throws IllegalAccessException if the object's private methods are inaccessible either invalid parameter, private method names, or wrong accessors.
     */
    @SuppressWarnings("JavaReflectionInvocation")
    static Object accessPrivateMethodValuers(Object object, String privateMethod, Object... args) throws IllegalAccessException {
        try {
            Method method = object.getClass().getDeclaredMethod(privateMethod);
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (Exception ex) {
            throw new IllegalAccessException("Unable to access private method!\nReferences:" + ex.toString());
        }
    }

    private static void wait(int interval) {
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ex) {
        }
    }
}
