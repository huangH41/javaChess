package chess.unitTesting;

import chess.base.*;
import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;
import chess.chessPiece.KingCheckState;

import java.lang.reflect.Method;

class ChessAssertor {

    private static final int PREVIEW_DELAY = 2000;
    private static final int NON_PREVIEW_DELAY = 100;

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
     * Validate if the targeted position contain expected chess piece rank
     *
     * @param board          Current game board
     * @param targetPosition Position to validate
     * @param rank           Expected chess piece rank
     * @return True if the chess piece equals expected chess piece and false if no chess piece found
     * or don't have expected rank
     */
    public boolean isExpectedPiece(Board board, BoardPosition targetPosition, ChessPieceRank rank) {
        ChessPiece targetLocationChessPiece = board.getPiece(targetPosition);
        return targetLocationChessPiece != null && targetLocationChessPiece.getPieceRank() == rank;
    }

    /**
     * Validate if the targeted position contain expected chess piece color
     *
     * @param board          Current game board
     * @param targetPosition Position to validate
     * @param color          Expected chess piece color
     * @return True if the chess piece equals expected chess piece color and false when else nothing
     * * matched with that criteria
     */
    public boolean isExpectedPiece(Board board, BoardPosition targetPosition, ChessPieceColor color) {
        ChessPiece targetLocationChessPiece = board.getPiece(targetPosition);
        return targetLocationChessPiece != null
                && targetLocationChessPiece.getChessColor() == color;
    }

    /**
     * Validate if the targeted position contain expected chess piece rank & color
     *
     * @param board          Current game board
     * @param targetPosition Position to validate
     * @param rank           Expected chess piece rank
     * @param color          Expected chess piece color
     * @return True if the chess piece equals expected chess piece rank & color and false when else nothing
     * matched with that criteria
     */
    public boolean isExpectedPiece(Board board, BoardPosition targetPosition, ChessPieceRank rank, ChessPieceColor color) {
        ChessPiece targetLocationChessPiece = board.getPiece(targetPosition);
        return targetLocationChessPiece != null
                && targetLocationChessPiece.getPieceRank() == rank
                && targetLocationChessPiece.getChessColor() == color;
    }

    /**
     * Move piece & display board for preview after movement. If you want to skip preview, add false to fourth parameter.
     *
     * @param board          Board to display
     * @param piece          Piece to move
     * @param targetPosition Target position for piece movement
     */
    public void movePiece(Board board, ChessPiece piece, String targetPosition) {
        movePiece(board, piece.getPosition().toString(), targetPosition, false);
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
     * @param showPreview    show preview?
     */
    public void movePiece(Board board, String startPosition, String targetPosition, boolean showPreview) {
        ChessPiece piece = board.getPiece(new BoardPosition(startPosition));
        piece.move(new BoardPosition(targetPosition), board);

        System.out.println();
        drawBoard(board);
        System.out.println(String.format("\nPerform move from %s to %s...", startPosition, targetPosition));
        wait(showPreview ? PREVIEW_DELAY : NON_PREVIEW_DELAY);
        System.out.println("\n");
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
     * Perform all essential task needed when changing turn
     *
     * @param board Current game board
     */
    public void changeTurn(Board board) {
        board.switchColor();
        BoardPlot.resetBoardPlotGuardStatus(board);
        KingCheckState.isKingUnderCheckState(board, board.getKing(board.getCurrentColor()));

        if(Gameplay.isGameEnded(board, board.getKing(board.getCurrentColor()))) return;
    }
}
