package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
    private int[][] board;                      //Aktualna plansza gry razem z licznikami
    private int[][] startingBoard;              //Poczatkowe ulozenie planszy / potrzebne by dobrze ustawic JButton przy wczytywaniu
    private int size;                           //Wielkosc planszy

    private ArrayList<int[]> lastMoves;         //Wszystkie wykonane ruchy gracza na planszy
    private int lastMovesPtr;                   //Aktualne polozenie wskaznika ostatnich ruchow

    public Board(int size) {
        this.size = size;

        this.board = new int[size][size];
        this.startingBoard = new int[size][size];

        //Lista przechowuje zmienione wartosci - [0] - poz x | [1] - poz y | [2] - wartosc
        lastMoves = new ArrayList<>();
        lastMovesPtr = 0;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<int[]> getLastMoves() {
        return lastMoves;
    }

    public void setLastMoves(ArrayList<int[]> lastMoves) {
        this.lastMoves = lastMoves;
    }

    public int getLastMovesPtr() {
        return lastMovesPtr;
    }

    public void setLastMovesPtr(int lastMovesPtr) {
        this.lastMovesPtr = lastMovesPtr;
    }

    public int[][] getStartingBoard() {
        return startingBoard;
    }

    public void setStartingBoard(int[][] startingBoard) {
        this.startingBoard = startingBoard;
    }
}
