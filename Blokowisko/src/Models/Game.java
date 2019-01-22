package Models;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Game extends JFrame implements Serializable {
    private Container container;
    private JPanel gameBoard;                        //Panel z plansza
    private JPanel control;                          //Panel z prawej strony
    private JPanel menu;                             //Panel z dolu
    private JPanel header;                           //Panel z gory

    //Pozycja wybranego przycisku
    private int buttonX;                             //Pozycja x aktualnie wybranego przycisku
    private int buttonY;                             //Pozycja x aktualnie wybranego przycisku

    private Board currBoard = new Board(11);    //Plansza w postaci int[][]
    private JButton[][] boardButtons;                //Plansza w postaci JButton / bez licznikow
    private JLabel[][] boardLabels;                  //Liczniki w postaci JLabel

    public Game() {
        container = getContentPane();
        gameBoard = new JPanel();
        control = new JPanel();
        menu = new JPanel();
        header = new JPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public JPanel getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(JPanel gameBoard) {
        this.gameBoard = gameBoard;
    }

    public JPanel getControl() {
        return control;
    }

    public void setControl(JPanel control) {
        this.control = control;
    }

    public JPanel getMenu() {
        return menu;
    }

    public void setMenu(JPanel menu) {
        this.menu = menu;
    }

    public int getButtonX() {
        return buttonX;
    }

    public void setButtonX(int buttonX) {
        this.buttonX = buttonX;
    }

    public int getButtonY() {
        return buttonY;
    }

    public void setButtonY(int buttonY) {
        this.buttonY = buttonY;
    }

    public Board getCurrBoard() {
        return currBoard;
    }

    public void setCurrBoard(Board currBoard) {
        this.currBoard = currBoard;
    }

    public JButton[][] getBoardButtons() {
        return boardButtons;
    }

    public void setBoardButtons(JButton[][] boardButtons) {
        this.boardButtons = boardButtons;
    }

    public void setBoardButtonsVal(int x, int y, JButton val) {
        this.boardButtons[x][y] = val;
    }

    public JLabel[][] getBoardLabels() {
        return boardLabels;
    }

    public void setBoardLabels(JLabel[][] boardLabels) {
        this.boardLabels = boardLabels;
    }

    public void setBoardLabelsVal(int x, int y, JLabel val) {
        this.boardLabels[x][y] = val;
    }

    public JPanel getHeader() {
        return header;
    }

    public void setHeader(JPanel header) {
        this.header = header;
    }
}
