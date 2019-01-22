package Controllers;

import Models.Game;
import Models.InfoBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class GameController implements Serializable {
    private Game g;

    public GameController(Game g) {
        this.g = g;
    }

    public void setGame() {
        //Generowanie planszy
        BoardController boardControler = new BoardController();
        boardControler.fillBoard(g.getCurrBoard());
        boardControler.fillCounters(g.getCurrBoard());
        boardControler.showBoard(g.getCurrBoard());
        System.out.println("-----------------");
        System.out.println(boardControler.checkBoard(g.getCurrBoard()) ? "Wygenerowano plansze " + (g.getCurrBoard().getSize() - 2) + "x" + (g.getCurrBoard().getSize() - 2) : "Error");
        System.out.println("-----------------");

        boardControler.setBlankValues(g.getCurrBoard());
        boardControler.showBoard(g.getCurrBoard());
        boardControler.saveStartingBoard(g.getCurrBoard());
        System.out.println("-----------------");
        System.out.println("Wyzerowano losowe pola");
        System.out.println("-----------------");

        g.setGameBoard(getBoardAsJPanel());

        //JButton po prawej stronie
        g.getControl().setLayout(new GridLayout(3, 3, 10, 10));
        g.getControl().setBorder(BorderFactory.createEmptyBorder(200, 15, 200, 15));
        for (int i = 1; i < 10; i++) {
            JButton temp = new JButton(String.valueOf(i));
            temp.addActionListener(new SetButtonValue(i));

            g.getControl().add(temp);
        }

        //JButton w dolnej czesci okienka
        g.getHeader().add(new JButton("SPRAWDZ") {{
            addActionListener(new CheckBoard());
        }});

        g.getHeader().add(new JButton("COFNIJ") {{
            addActionListener(new UndoMove());
        }});

        g.getHeader().add(new JButton("PRZYWROC") {{
            addActionListener(new RedoMove());
        }});

        g.getMenu().add(new JButton("ZAPISZ") {{
            addActionListener(new SaveBoard());
        }});

        g.getMenu().add(new JButton("WCZYTAJ") {{
            addActionListener(new LoadBoard());
        }});

        g.getMenu().add(new JButton("JAK GRAC") {{
            addActionListener(new HowToPlay());
        }});

        //Dodanie elementow do cointainer'a
        g.getContainer().setLayout(new BorderLayout());
        g.getContainer().add(g.getGameBoard(), BorderLayout.CENTER);
        g.getContainer().add(g.getControl(), BorderLayout.EAST);
        g.getContainer().add(g.getMenu(), BorderLayout.SOUTH);
        g.getContainer().add(g.getHeader(), BorderLayout.NORTH);

        g.setSize(800, 600);
        g.setLocationRelativeTo(null);
        g.setVisible(true);
    }

    private JPanel getBoardAsJPanel() {
        JPanel gameBoard = new JPanel();

        int size = g.getCurrBoard().getSize();
        int[][] board = g.getCurrBoard().getBoard();

        gameBoard.setLayout(new GridLayout(size, size));
        gameBoard.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        g.setBoardButtons(new JButton[size - 1][size - 1]);
        g.setBoardLabels(new JLabel[4][size - 2]);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != 0 && j != 0 && i != size - 1 && j != size - 1) {
                    //Wnetrze planszy
                    if (board[i][j] != 0) {
                        //Wypelnione wartosci
                        g.setBoardButtonsVal(i, j, new JButton(String.valueOf(board[i][j])) {{
                            setEnabled(false);
                        }});

                        gameBoard.add((g.getBoardButtons())[i][j]);
                    } else {
                        //Wartosci do wypelnienia
                        g.setBoardButtonsVal(i, j, new JButton() {{
                            setBackground(Color.GRAY);
                        }});

                        ((g.getBoardButtons())[i][j]).addActionListener(new ButtonPosition(i, j));

                        gameBoard.add((g.getBoardButtons())[i][j]);
                    }
                } else if (board[i][j] == 0) {
                    //Rogi planszy
                    gameBoard.add(new JLabel());
                } else {
                    //Liczniki
                    if (i == 0) {
                        g.setBoardLabelsVal(0, j - 1, new JLabel(String.valueOf(board[i][j]), SwingConstants.CENTER) {{
                            setForeground(Color.BLACK);
                        }});
                        gameBoard.add((g.getBoardLabels())[0][j - 1]);
                    } else if (j == 0) {
                        g.setBoardLabelsVal(1, i - 1, new JLabel(String.valueOf(board[i][j]), SwingConstants.CENTER) {{
                            setForeground(Color.BLACK);
                        }});
                        gameBoard.add((g.getBoardLabels())[1][i - 1]);
                    } else if (j == size - 1) {
                        g.setBoardLabelsVal(2, i - 1, new JLabel(String.valueOf(board[i][j]), SwingConstants.CENTER) {{
                            setForeground(Color.BLACK);
                        }});
                        gameBoard.add((g.getBoardLabels())[2][i - 1]);
                    } else {
                        g.setBoardLabelsVal(3, j - 1, new JLabel(String.valueOf(board[i][j]), SwingConstants.CENTER) {{
                            setForeground(Color.BLACK);
                        }});
                        gameBoard.add((g.getBoardLabels())[3][j - 1]);
                    }
                }
            }
        }

        return gameBoard;
    }

    public class CheckBoard implements ActionListener {
        //Okienko informujace czy plansza została poprawnie uzupełniona
        private InfoBox checkBoardBox;

        CheckBoard() {
            String info = "";
            String text = "";

            checkBoardBox = new InfoBox(info, text);
            checkBoardBox.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

            InfoBoxController ibc = new InfoBoxController();
            ibc.setInfoBox(checkBoardBox);

            checkBoardBox.getText1().setFont(new Font("NewTimesRoman", Font.BOLD, 12));
            checkBoardBox.getText2().setFont(new Font("NewTimesRoman", Font.BOLD, 12));
            checkBoardBox.setSize(300, 150);
        }

        public void actionPerformed(ActionEvent e) {
            BoardController bc = new BoardController();

            if (bc.checkBoard(g.getCurrBoard())) {
                checkBoardBox.getText1().setText("<html><center>\n" +
                        "<font color=green><b>Gratulacje!</b></font>\n" +
                        "<font color=green>Plansza została poprawnie uzupełniona</font>\n" +
                        "</html></center>\n");
                checkBoardBox.getText2().setText("<html><center>\n" +
                        "Dziekuje za gre.<br>\n" +
                        "Autor: Paweł Krause\n" +
                        "</html></center>\n");

                checkBoardBox.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            } else {
                checkBoardBox.getText1().setText("<html><center>\n" +
                        "<font color=red>Plansza nie została poprawnie uzupełniona</font>\n" +
                        "</html></center>\n");
                checkBoardBox.getText2().setText("<html><center>\n" +
//                        "Błędne pola zostały zaznaczone na czerwono.\n" +
                        "</html></center>\n");

                checkBoardBox.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            }

            checkBoardBox.setVisible(true);
        }
    }

    class ButtonPosition implements ActionListener {
        private int x;
        private int y;

        ButtonPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void actionPerformed(ActionEvent e) {
            g.setButtonX(x);
            g.setButtonY(y);
        }
    }

    class SetButtonValue implements ActionListener {
        private int value;

        SetButtonValue(int value) {
            this.value = value;
        }

        public void actionPerformed(ActionEvent e) {
            BoardController bc = new BoardController();

            bc.putValue(g.getCurrBoard(), g.getButtonX(), g.getButtonY(), value);
            (g.getBoardButtons())[g.getButtonX()][g.getButtonY()].setText(String.valueOf(value));
        }
    }

    class UndoMove implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BoardController bc = new BoardController();

            bc.undoMove(g.getCurrBoard());

            //Aktualizacja wartosci w JButton
            if (g.getCurrBoard().getLastMoves().size() > 0) {
                int x = g.getCurrBoard().getLastMoves().get(g.getCurrBoard().getLastMovesPtr())[0];
                int y = g.getCurrBoard().getLastMoves().get(g.getCurrBoard().getLastMovesPtr())[1];

                if ((g.getCurrBoard().getBoard())[x][y] != 0)
                    (g.getBoardButtons())[x][y].setText(String.valueOf((g.getCurrBoard().getBoard())[x][y]));
                else
                    (g.getBoardButtons())[x][y].setText("");
            }
        }
    }

    class RedoMove implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BoardController bc = new BoardController();

            bc.redoMove(g.getCurrBoard());

            //Aktualizacja wartosci w JButton
            if (g.getCurrBoard().getLastMoves().size() > 0) {
                int x = g.getCurrBoard().getLastMoves().get(g.getCurrBoard().getLastMovesPtr() - 1)[0];
                int y = g.getCurrBoard().getLastMoves().get(g.getCurrBoard().getLastMovesPtr() - 1)[1];

                if ((g.getCurrBoard().getBoard())[x][y] != 0)
                    (g.getBoardButtons())[x][y].setText(String.valueOf((g.getCurrBoard().getBoard())[x][y]));
                else
                    (g.getBoardButtons())[x][y].setText("");
            }
        }
    }

    class SaveBoard implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BoardController boardController = new BoardController();

//            boardController.saveBoard(g.getCurrBoard(), "Zapis");
            boardController.saveBoardFP(g.getCurrBoard());
        }
    }

    class LoadBoard implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            BoardController boardController = new BoardController();

//            boardController.loadBoard(g, "Zapis");
            boardController.loadBoardFP(g);

            //Aktualizacja planszy
            int size = g.getCurrBoard().getSize();
            int[][] board = g.getCurrBoard().getBoard();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (i != 0 && j != 0 && i != size - 1 && j != size - 1) {
                        if (board[i][j] != 0) {
                            g.getBoardButtons()[i][j].setText(String.valueOf(board[i][j]));

                            if (g.getCurrBoard().getStartingBoard()[i][j] != 0) {
                                g.getBoardButtons()[i][j].setEnabled(false);
                                g.getBoardButtons()[i][j].setBackground(Color.WHITE);
                            } else {
                                g.getBoardButtons()[i][j].setEnabled(true);
                                g.getBoardButtons()[i][j].setBackground(Color.GRAY);
                            }
                        } else {
                            if (board[i][j] == 0)
                                g.getBoardButtons()[i][j].setText(null);

                            g.getBoardButtons()[i][j].addActionListener(new ButtonPosition(i, j));
                            g.getBoardButtons()[i][j].setBackground(Color.GRAY);
                            g.getBoardButtons()[i][j].setEnabled(true);
                        }
                    } else if (board[i][j] != 0) {
                        if (i == 0) {
                            g.getBoardLabels()[0][j - 1].setText(String.valueOf(board[i][j]));
                            g.getBoardLabels()[0][j - 1].setForeground(Color.BLACK);
                        } else if (j == 0) {
                            g.getBoardLabels()[1][i - 1].setText(String.valueOf(board[i][j]));
                            g.getBoardLabels()[1][i - 1].setForeground(Color.BLACK);
                        } else if (j == size - 1) {
                            g.getBoardLabels()[2][i - 1].setText(String.valueOf(board[i][j]));
                            g.getBoardLabels()[2][i - 1].setForeground(Color.BLACK);
                        } else {
                            g.getBoardLabels()[3][j - 1].setText(String.valueOf(board[i][j]));
                            g.getBoardLabels()[3][j - 1].setForeground(Color.BLACK);
                        }
                    }
                }
            }
        }
    }

    class HowToPlay implements ActionListener {
        private InfoBox howToPlayBox;

        HowToPlay() {
            String gameRules = "<html><center>" +
                    "Wyobraźmy sobie, że mamy 49 bloków - prostopadłościanów o identycznych\n" +
                    " kwadratowych podstawach i siedmiu różnych wysokościach (po siedem bloków każdej wysokości).\n" +
                    "Mamy także kwadratowe pole podzielone na 49 małych kwadratów odpowiadających wielkością podstawom bloków.\n" +
                    "Wszystkie bloki stawiamy podstawami na małych kwadratach, wypełniając nimi w ten sposób ściśle cały duży kwadrat.\n" +
                    "Łamigłówka polega na odtworzeniu ustawienia bloków, czyli oznaczeniu na diagramie wysokości każdego\n" +
                    " z nich - od najniższych (wysokość 1) do najwyższych (wysokość 7). Liczby przy brzegu kwadratu wskazują,\n" +
                    "ile bloków można zobaczyć, patrząc w danym rzędzie lub kolumnie zgodnie ze wskazaniem strzałki\n" +
                    "(pozostałe są zasłonięte przez wyższe bloki). Ponadto wiadomo, że w każdym rzędzie i w każdej\n" +
                    "kolumnie stoi siedem bloków o różnych wysokościach.\n" +
                    "</center></html>";
            String howTo = "<html><center>" +
                    "Aby wprowadzić wartość do danego kwadratu należy \n" +
                    "kliknąć na niego a następnie na wartość która\n" +
                    "chcemy do niego wprowadzić w boku po prawej.\n" +
                    "</center></html>";

            howToPlayBox = new InfoBox(gameRules, howTo);

            InfoBoxController ibc = new InfoBoxController();
            ibc.setInfoBox(howToPlayBox);

            howToPlayBox.getText1().setFont(new Font("NewTimesRoman", Font.BOLD, 12));
            howToPlayBox.getText2().setFont(new Font("NewTimesRoman", Font.BOLD, 12));
            howToPlayBox.getText2().setPreferredSize(new Dimension(400, 500));
            howToPlayBox.setSize(600, 400);
        }

        public void actionPerformed(ActionEvent e) {
            howToPlayBox.setVisible(true);
        }
    }
}
