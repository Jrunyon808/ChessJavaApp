import Board.Board;
import Gui.Gui;

import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Board board = Board.generateBoard();
        Gui table = new Gui();
    }
}
