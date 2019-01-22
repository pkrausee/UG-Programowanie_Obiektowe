package Controllers;

import Models.Board;
import Models.Game;

import javax.swing.*;
import java.io.*;
import java.util.Random;

public class BoardController {
    private boolean fillSquare(Board b, int row, int col) {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        Random rand = new Random();

        for (int i = row; i < row + 3; i++)
            for (int j = col; j < col + 3; j++) {
                //Sprawdzenie ile liczb mozna wstawic na danej pozycji
                int possibleVals = 0;

                for (int val : values)
                    if (checkVal(b, val, i, j) && val != -1)
                        possibleVals++;

                if (possibleVals == 0)
                    return false;

                //Losowanie liczby
                int position = rand.nextInt(9);

                while (values[position] == -1 || !checkVal(b, values[position], i, j))
                    position = rand.nextInt(9);

                (b.getBoard())[i][j] = values[position];

                values[position] = -1;
            }

        //true jesli kwadrat 3x3 zostal stworzony poprawnie
        return true;
    }

    private boolean checkVal(Board b, int value, int row, int col) {
        int count = 0;

        int temp = (b.getBoard())[row][col];

        (b.getBoard())[row][col] = value;

        //Sprawdzenie kolumn o rzedow
        for (int i = 1; i < b.getSize() - 1; i++) {
            if ((b.getBoard())[row][i] == value && (b.getBoard())[i][col] == value)
                count += 2;
            else if ((b.getBoard())[row][i] == value)
                count++;
            else if ((b.getBoard())[i][col] == value)
                count++;
        }

        (b.getBoard())[row][col] = temp;

        //true jesli dana wartosc spełnia warunki gry
        return count < 3;
    }

    public void fillCounters(Board b) {
        int visibleTowers;
        int tallestTower;
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                if (i == 0 && j > 0 && j < b.getSize() - 1) {
                    //Gorna linia
                    visibleTowers = 0;
                    tallestTower = 0;
                    for (int k = 1; k < b.getSize() - 1; k++) {
                        if ((b.getBoard())[k][j] > tallestTower) {
                            tallestTower = (b.getBoard())[k][j];
                            visibleTowers++;
                        }
                    }
                    (b.getBoard())[i][j] = visibleTowers;
                } else if (j == 0 && i > 0 && i < b.getSize() - 1) {
                    //Lewa linia
                    visibleTowers = 0;
                    tallestTower = 0;
                    for (int k = 1; k < b.getSize() - 1; k++) {
                        if ((b.getBoard())[i][k] > tallestTower) {
                            tallestTower = (b.getBoard())[i][k];
                            visibleTowers++;
                        }
                    }
                    (b.getBoard())[i][j] = visibleTowers;
                } else if (j == b.getSize() - 1 && i > 0 && i < b.getSize() - 1) {
                    //Prawa linia
                    visibleTowers = 0;
                    tallestTower = 0;
                    for (int k = b.getSize() - 2; k > 0; k--) {
                        if ((b.getBoard())[i][k] > tallestTower) {
                            tallestTower = (b.getBoard())[i][k];
                            visibleTowers++;
                        }
                    }
                    (b.getBoard())[i][j] = visibleTowers;
                } else if (i == b.getSize() - 1 && j > 0 && j < b.getSize() - 1) {
                    //Dolna linia
                    visibleTowers = 0;
                    tallestTower = 0;
                    for (int k = b.getSize() - 2; k > 0; k--) {
                        if ((b.getBoard())[k][j] > tallestTower) {
                            tallestTower = (b.getBoard())[k][j];
                            visibleTowers++;
                        }
                    }
                    (b.getBoard())[i][j] = visibleTowers;
                }
            }
        }
    }

    public void setBlankValues(Board b) {
        Random gen = new Random();

        int emptyVals = gen.nextInt(10) + 40;

        for (int i = 0; i < emptyVals; i++) {
            int row = gen.nextInt(9) + 1;
            int col = gen.nextInt(9) + 1;

            while ((b.getBoard())[row][col] == 0) {
                row = gen.nextInt(9) + 1;
                col = gen.nextInt(9) + 1;
            }

            (b.getBoard())[row][col] = 0;
        }
    }

    public void cleanTab(Board b) {
        for (int i = 0; i < b.getSize(); i++)
            for (int j = 0; j < b.getSize(); j++)
                (b.getBoard())[i][j] = 0;
    }

    public void fillBoard(Board b) {
        //Optymalna kolejnosc wypelniania planszy (chyba)
        int[] pos1 = {1, 4, 7, 1, 1, 4, 4, 7, 7};
        int[] pos2 = {1, 4, 7, 4, 7, 1, 7, 1, 4};

        int guard = 0;
        while (guard != 1) {
            guard = 0;
            cleanTab(b);

            for (int i = 0; i < 9 && guard != -1; i++) {
                if (fillSquare(b, pos1[i], pos2[i]))
                    guard = 1;
                else
                    guard = -1;
            }
        }
    }

    public void saveStartingBoard(Board b) {
        for (int i = 0; i < b.getSize(); i++)
            for (int j = 0; j < b.getSize(); j++)
                b.getStartingBoard()[i][j] = b.getBoard()[i][j];
    }

    public boolean checkBoard(Board b) {
        int wrongVals = 0;

        for (int i = 1; i < b.getSize() - 1; i++)
            for (int j = 1; j < b.getSize() - 1; j++)
                if (!checkVal(b, (b.getBoard())[i][j], i, j)) {
//                    System.out.println(i + " " + j + " " + (b.getBoard())[i][j]);
                    wrongVals++;
                }

        //true jesli wszystkie wartosci sa prawidlowo ustawione
        return wrongVals == 0;
    }

    public void showBoard(Board b) {
        for (int i = 1; i < b.getSize() - 1; i++) {
            for (int j = 1; j < b.getSize() - 1; j++)
                System.out.print((b.getBoard())[i][j] + " ");
            System.out.println();
        }
    }

    public void putValue(Board b, int x, int y, int val) {
        if (b.getLastMovesPtr() < b.getLastMoves().size())
            for (int i = b.getLastMoves().size() - 1; i > b.getLastMovesPtr() - 1; i--)
                b.getLastMoves().remove(i);

        b.getLastMoves().add(new int[]{x, y, (b.getBoard())[x][y]});
        b.setLastMovesPtr(b.getLastMovesPtr() + 1);

        (b.getBoard())[x][y] = val;

//        System.out.println("Dodano wartosc " + x + ":" + y + " - " + val + " ptr - " + b.getLastMovesPtr());
    }

    public void undoMove(Board b) {
        if (b.getLastMovesPtr() > 0) {
            int index = b.getLastMovesPtr() - 1;

            int x = b.getLastMoves().get(index)[0];
            int y = b.getLastMoves().get(index)[1];
            int val = b.getLastMoves().get(index)[2];

            b.getLastMoves().get(index)[2] = b.getBoard()[x][y];

            b.getBoard()[x][y] = val;

            b.setLastMovesPtr(b.getLastMovesPtr() - 1);

//            System.out.println("Cofnieto wartosc " + x + ":" + y + " - " + val + " ptr - " + b.getLastMovesPtr());
        } else {
//            System.out.println("Brak mozliwych cofniec ");
        }
    }

    public void redoMove(Board b) {
        if (b.getLastMovesPtr() < b.getLastMoves().size()) {
            int index = b.getLastMovesPtr();

            int x = b.getLastMoves().get(index)[0];
            int y = b.getLastMoves().get(index)[1];
            int val = b.getLastMoves().get(index)[2];

            b.getLastMoves().get(index)[2] = b.getBoard()[x][y];

            b.getBoard()[x][y] = val;

            b.setLastMovesPtr(b.getLastMovesPtr() + 1);

//            System.out.println("Przywrocono wartosc " + x + ":" + y + " - " + val + " ptr - " + b.getLastMovesPtr());
        } else {
//            System.out.println("Brak mozliwych cofniec");
        }
    }

    public void saveBoard(Board b, String filename) {
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(f);
            os.writeObject(b);
            f.close();

            System.out.println("Zapisano plansze do pliku: " + filename);
        } catch (IOException ex) {
            System.out.println("Nie mozna zapisac planszy do pliku");
            ex.printStackTrace();
        }
    }

    public void saveBoardFP(Board b) {
        JFileChooser fileChooser = new JFileChooser(".");

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(fileChooser.getSelectedFile());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(b);
            }
            catch (FileNotFoundException exfnfe)
            {
                System.err.println("Nie znaleziono pliku");
                exfnfe.printStackTrace();
            }
            catch (IOException exioe)
            {
                System.err.println("IOException");
                exioe.printStackTrace();
            }
        }
    }

    public void loadBoard(Game g, String filename) {
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(f);
            g.setCurrBoard((Board) is.readObject());
            is.close();

            System.out.println("Wczytano plansze z pliku: " + filename);
        } catch (IOException ex) {
            System.out.println("Nie mozna wczytac planszy z pliku");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: ClassNotFoundException");
        }
    }

    public void loadBoardFP(Game g) {
        JFileChooser fileChooser = new JFileChooser(".");

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                FileInputStream fileInputStream = new FileInputStream(fileChooser.getSelectedFile());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                g.setCurrBoard((Board) objectInputStream.readObject());
            }
            catch (FileNotFoundException exfnfe)
            {
                System.err.println("Nie znaleziono pliku");
                exfnfe.printStackTrace();
            }
            catch (IOException exioe)
            {
                System.err.println("IOException");
                exioe.printStackTrace();
            }
            catch (ClassNotFoundException excnfe)
            {
                System.err.println("Wybrano zly plik");
                excnfe.printStackTrace();
            }
        }
    }
}
