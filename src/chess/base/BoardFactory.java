package chess.base;

import chess.chessPiece.ChessPiece;
import chess.chessPiece.King;

public class BoardFactory {

    /**
     * Deep copy board object from existed board
     *
     * @param srcBoard Board to copy
     * @return new board copied from srcBoard
     */
    public static Board copyBoard(Board srcBoard) {
        Board copiedBoard = new Board();
        setCopiedBoardCurrentColor(srcBoard, copiedBoard);
        copyPieceBoard(srcBoard.getPieceBoard(), copiedBoard);
        BoardPlot.setBoardPlotGuardedStatus(copiedBoard);
        return copiedBoard;
    }

    private static void copyPieceBoard(ChessPiece[][] srcPieceBoard, Board dstBoard) {
        int maxIdx = BoardPosition.MAX_INDEX;
        ChessPiece[][] dstPieceBoard = dstBoard.getPieceBoard();

        for (int i = 0; i < maxIdx; i++) {
            for(int j = 0; j < maxIdx; j++) {
                ChessPiece toCopyPiece = srcPieceBoard[i][j];
                if(toCopyPiece != null) {
                    dstPieceBoard[i][j] =  setCopiedPiece(dstBoard, toCopyPiece);
                } else {
                    dstPieceBoard[i][j] =  null;
                }
            }
        }
    }

    private static ChessPiece setCopiedPiece(Board dstBoard, ChessPiece toCopyPiece) {
        if(toCopyPiece != null) {
            ChessPiece copiedPiece = ChessPieceFactory.copyChessPiece(toCopyPiece);
            if(copiedPiece.getPieceRank() == ChessPieceRank.KING) dstBoard.setKing((King) copiedPiece);
            return copiedPiece;
        }
        return null;
    }

    private static void setCopiedBoardCurrentColor(Board srcBoard, Board copiedBoard) {
        if(srcBoard.getCurrentColor() == ChessPieceColor.WHITE) {
            copiedBoard.setCurrentColor(ChessPieceColor.WHITE);
        } else {
            copiedBoard.setCurrentColor(ChessPieceColor.BLACK);
        }
    }

    /**
     * Create and place all of chess piece in board
     */
    public static void initializeChessPieces(Board board) {
        initializeWhitePieces(board);
        initializeBlackPieces(board);
    }

    private static void initializeWhitePieces(Board board) {
        ChessPiece[][] pieceBoard = board.getPieceBoard();

        board.setKing((King) ChessPieceFactory.defineWhitePiece(ChessPieceRank.KING, new BoardPosition("E1")));
        pieceBoard[0][0] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("A1"));
        pieceBoard[0][7] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.ROOK, new BoardPosition("H1"));
        pieceBoard[0][1] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KNIGHT, new BoardPosition("B1"));
        pieceBoard[0][6] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.KNIGHT, new BoardPosition("G1"));
        pieceBoard[0][2] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("C1"));
        pieceBoard[0][5] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.BISHOP, new BoardPosition("F1"));
        pieceBoard[0][3] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.QUEEN, new BoardPosition("D1"));
        pieceBoard[0][4] = board.getKing(ChessPieceColor.WHITE);

        for (int i = 0; i < 8; i++) {
            pieceBoard[1][i] = ChessPieceFactory.defineWhitePiece(ChessPieceRank.PAWN, new BoardPosition(2, i + 1));
        }
    }

    private static void initializeBlackPieces(Board board) {
        ChessPiece[][] pieceBoard = board.getPieceBoard();

        board.setKing((King) ChessPieceFactory.defineBlackPiece(ChessPieceRank.KING, new BoardPosition("E8")));
        pieceBoard[7][0] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("A8"));
        pieceBoard[7][7] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.ROOK, new BoardPosition("H8"));
        pieceBoard[7][1] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.KNIGHT, new BoardPosition("B8"));
        pieceBoard[7][6] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.KNIGHT, new BoardPosition("G8"));
        pieceBoard[7][2] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.BISHOP, new BoardPosition("C8"));
        pieceBoard[7][5] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.BISHOP, new BoardPosition("F8"));
        pieceBoard[7][3] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.QUEEN, new BoardPosition("D8"));
        pieceBoard[7][4] = board.getKing(ChessPieceColor.WHITE);

        for (int i = 0; i < 8; i++) {
            pieceBoard[6][i] = ChessPieceFactory.defineBlackPiece(ChessPieceRank.PAWN, new BoardPosition(7, i + 1));
        }
    }
}
