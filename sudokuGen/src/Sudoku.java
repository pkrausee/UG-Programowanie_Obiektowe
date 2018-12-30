import java.util.Random;


class Board {
    private int[][] board;
    private int size;

    Board() {
        this.size = 9;

        board = new int[size][size];
    }

    public void fillBoard() {
        int guard = 0;
        while (guard != 1) {
            guard = 0;
            cleanTab();

            for (int i = 0; i < size && guard != -1; i += 3) {
                for (int j = 0; j < size && guard != -1; j += 3) {
                    if (fillSquare(i, j))
                        guard = 1;
                    else
                        guard = -1;
                }
            }
        }
    }

    public void fillBoardOptimal() {
        //Only for 9x9 size

        //Optimal order (i guess..)
        int[] pos1 = {0, 3, 6, 0, 0, 3, 3, 6, 6};
        int[] pos2 = {0, 3, 6, 3, 6, 0, 6, 0, 3};

        int guard = 0;
        while (guard != 1) {
            guard = 0;
            cleanTab();

            for (int i = 0; i < size && guard != -1; i++) {
                if (fillSquare(pos1[i], pos2[i]))
                    guard = 1;
                else
                    guard = -1;
            }
        }
    }

    private boolean fillSquare(int row, int col) {
        int[] vals = new int[9];

        for (int i = 0; i < 9; i++)
            vals[i] = i + 1;

        Random rand = new Random();

        for (int i = row; i < row + 3; i++)
            for (int j = col; j < col + 3; j++) {
                //Check if there is any possible value
                int possibleVals = 0;

                for (int val : vals)
                    if (checkVal(val, i, j) && val != -1)
                        possibleVals++;

                if (possibleVals == 0)
                    return false;

                //Draw value
                int position = rand.nextInt(9);

                while (vals[position] == -1 || !checkVal(vals[position], i, j))
                    position = rand.nextInt(9);

                board[i][j] = vals[position];

                vals[position] = -1;
            }

        //True if 3x3 square was filled successfully
        return true;
    }

    private boolean checkVal(int value, int row, int col) {
        int count = 0;

        int temp = board[row][col];

        board[row][col] = value;

        for (int i = 0; i < size; i++) {
            if (board[row][i] == value && board[i][col] == value)
                count += 2;
            else if (board[row][i] == value)
                count++;
            else if (board[i][col] == value)
                count++;
        }

        board[row][col] = temp;

        //True if value doesnt repeat
        return count < 3;
    }

    private void cleanTab() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = 0;
    }

    public boolean checkBoard() {
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (!checkVal(board[i][j], i, j))
                    return false;

        //True if board is filled correctly
        return true;
    }

    public void showBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}


class Sudoku {
    public static void main(String[] args) {

        Board b = new Board();

        b.fillBoard();

        b.showBoard();

        System.out.println(b.checkBoard() ? "\nBoard was generated correctly" : "\nBoard wasnt generated correctly");

    }
}