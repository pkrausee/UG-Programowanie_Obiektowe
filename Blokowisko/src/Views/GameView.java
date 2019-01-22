package Views;

import Controllers.GameController;
import Models.Game;

import javax.swing.*;

public class GameView extends JFrame {
    public static void main(String[] args) {
        Game g = new Game();
        GameController gameController = new GameController(g);
        gameController.setGame();
    }
}
