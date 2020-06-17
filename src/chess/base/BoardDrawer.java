package chess.base;

import chess.chessPiece.ChessPiece;

public class BoardDrawer {

    private BoardDrawer() {
    }

    public static String drawBoard(Board board) {
        StringBuilder sb = new StringBuilder();
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                ChessPiece piece = board.getPiece(new BoardPosition(row, column));

                char plot = (piece != null) ?
                        piece.getPieceRank().getInitialPieceRank(piece.getChessColor()) : drawPatternColor(row, column);

                sb.append(plot).append(" ");
            }
            sb.append(row).append("\n");
        }
        for (int column = 0; column < 8; column++) {
            sb.append((char) ('A' + column)).append(" ");
        }
        return sb.toString();
    }

    private static char drawPatternColor(int row, int column) {
        return ((row % 2 == 0 && column % 2 == 0) || (row % 2 == 1 && column % 2 == 1)) ? '+' : '-';
    }


    // Temporary only, used to draw the guarded plot
    public static String drawBoardGuardedPlot(BoardPlot boardPlot) {
        StringBuilder sb = new StringBuilder();
        for (int row = 8; row >= 1; row--) {
            for (int column = 1; column <= 8; column++) {
                BoardPosition position = new BoardPosition(row, column);
                char status = (boardPlot.getPlot(position).getGuardStatus()) ?
                        'G' : drawPatternColor(row, column);

                sb.append(status).append(" ");
            }
            sb.append(row).append("\n");
        }
        for (int column = 0; column < 8; column++) {
            sb.append((char) ('A' + column)).append(" ");
        }
        return sb.toString();
    }
}
