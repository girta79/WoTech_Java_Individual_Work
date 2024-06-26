/* Create a simple tic-tac-toe game. Depending on your skills and knowledge.
Game grid should be 3x3. It should be possible for the user to put values in the grid by typing row number and column number.

Easy: Ask user for row and column and write in the two dimensional array a value "1" in the correct place.
Check whether or not the row chosen by user contains all 1.
If all elements in row contain 1, then let player know they won*/


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] grid = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        boolean winningGame = false;

        while (!winningGame) {
            printGrid(grid);
            System.out.print("Enter row (0, 1 or 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1 or 2): ");
            int column = scanner.nextInt();

            if (grid[row][column] == 0) {
                grid[row][column] = 1;
            } else {
                System.out.println("Cell occupied! Try again!");
                continue;
            }

            if (checkRow(grid, row)) {
                System.out.println("Congratulations! You won!");
                winningGame = true;
            }
        }
        printGrid(grid);
        scanner.close();
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkRow(int[][] grid, int row) {
        for (int j = 0; j < grid[row].length; j++) {
            if (grid[row][j] != 1) {
                return false;
            }
        }
        return true;
    }
}


/* Medium: Ask for row and column and write in the two dimensional array a value "1" or "2" in the correct place, depending on which players turn it is.
Switch the turn after every move.
Check whether or not the row chosen by user contains all 1, or all 2.
Check whether or not the column chosen by user contains all 1, or all 2.
(1 represents X, 2 represents O, 0 represents empty) */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] grid = new int[3][3];
        Scanner scanner = new Scanner(System.in);
        boolean winningGame = false;
        int currentPlayer = 1; // 1 = first player
        
        while (!winningGame) {
            printGrid(grid);
            System.out.println("Player " + currentPlayer + " turn");
            System.out.print("Enter row (0, 1 or 2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0, 1 or 2): ");
            int column = scanner.nextInt();
            
            if (grid[row][column] == 0) {
                grid[row][column] = currentPlayer;
            } else {
                System.out.println("Cell occupied! Try again!");
                continue;
            }

            if (checkRow(grid, row, currentPlayer) || checkColumn(grid, column, currentPlayer)) {
                printGrid(grid);
                System.out.println("Congratulations! Player " + currentPlayer + " won!");
                winningGame = true;
            }

            currentPlayer = (currentPlayer == 1) ? 2 : 1; // switch player, 2 = second player
        }
        scanner.close();
    }

    public static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean checkRow(int[][] grid, int row, int player) {
        for (int j = 0; j < grid[row].length; j++) {
            if (grid[row][j] != player) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkColumn(int[][] grid, int column, int player) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][column] != player) {
                return false;
            }
        }
        return true;
    }
}
