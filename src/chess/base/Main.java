package chess.base;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(BoardDrawer.drawBoard(board));
    }
}
