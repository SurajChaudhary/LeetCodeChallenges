package datastructures.graphs.undirected;

import java.util.Arrays;

public class NQueenProblemTest {
    public static void main(String[] args) {
        int[][] board = new int[4][4];
        int size = 4;
        solveNQueen(board, size, -1);
        /*for(int col = 0; col < size; col++) {
            board[0][col] = 1;
            if(solveNQueen(board, size, 0)) {
                return;
            }else {
                board[0][col] = 0;
            }
        }*/
    }

    private static boolean solveNQueen(int[][] board, int size, int row) {
        if(row == size-1) {
            printBoard(board, size);
            return true;
        } else {
            for(int col = 0; col < size; col++) {
                int newRow = row+1;
                if(validMove(newRow, col, board, size)) {
                    board[newRow][col] = 1;
                    if (solveNQueen(board, size, row+1)) {
                        return true;
                    }
                    board[newRow][col] = 0;
                }
            }
            return false;
        }

    }

    private static boolean validMove(int newRow, int col, int[][] board, int size) {
        // Check for same column in upper rows
        for(int row = 0; row < newRow; row++) {
            if(board[row][col] == 1) {
                return false;
            }
        }

        // Check for left diagonal for upper half
        for(int r = newRow, c = col; r>=0 && c>=0; r--, c--) {
            if (board[r][c] == 1) {
                return false;
            }
        }

        // Check for left diagonal for upper half
        for(int r = newRow, c = col; r>=0 && c<size; r--, c++) {
            if (board[r][c] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(int[][] board, int size) {
        for(int row = 0; row < size; row++) {
            System.out.println(Arrays.toString(board[row]));
        }
    }
}
