package datastructures.graphs.backtracking;

import java.util.Arrays;

public class NQueenPlacementTest {
    public static void main(String[] args) {
        int board[][] = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        int size = board.length;

        placeQueen(board, size, -1);
    }

    private static void placeQueen(int[][] board, int size, int currRow) {
        if (currRow == size - 1) {
            printBoard(board, size);
        }else {
            for(int col = 0; col <= size-1; col++) {
                int row = currRow + 1;
                if(queenCanBePlacedAt(board, size, row, col)) {
                    board[row][col] = 1;
                    placeQueen(board, size, row);
                    board[row][col] = 0;
                }
            }
        }
        return;
    }

    private static boolean queenCanBePlacedAt(int[][] board, int size, int row, int col) {
        // Check upper left diagonal
        for(int r = row-1, c = col+1; r>=0 && c<=size-1; r--,c++) {
            if(board[r][c] == 1) {
                return false;
            }
        }

        // Check upper left diagonal
        for(int r = row-1, c = col-1; r>=0 && c>=0; r--,c--) {
            if(board[r][c] == 1) {
                return false;
            }
        }

        //Check same col and above rows
        for(int r = row-1; r>=0; r--){
            if(board[r][col] == 1) {
                return false;
            }
        }

        return true;
    }

    private static void printBoard(int[][] board, int size) {
        System.out.println("Queen's placement on the board as follows: ");
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}
