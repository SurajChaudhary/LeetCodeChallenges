package datastructures.graphs.backtracking;

import java.util.Arrays;

public class KnightTourTest {
    public static void main(String[] args) {
        int size = 8;
        boolean[][] board = new boolean[size][size];
        for (boolean[] arr : board) Arrays.fill(arr, false);
        int[][] visited = new int[size][size];
        for (int[] arr : visited) Arrays.fill(arr, 0);

        boolean canTour = tour(board, visited, size);
        if(canTour) {
            printBoard(visited);
        }else {
            System.out.println("Knight could not reach to all cells successfully!!!");
        }
    }

    private static boolean tour(boolean[][] board, int[][] visited, int size) {
        int cellCount = 0;
        int[] rowVector = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] colVector = {-1, 1, -2, 2, -2, 2, -1,1};
        if(tourUtil(board, visited,-1,-1,size,cellCount,rowVector,colVector)) {
            return true;
        }else {
            return false;
        }
    }

    private static boolean tourUtil(
            boolean[][] board,
            int[][] visited,
            int currRow,
            int currCol,
            int size,
            int cellCount,
            int[] rowVector,
            int[] colVector) {
        if(cellCount == (size*size)) {
            return true;
        }else {
            for(int i = 0; i < rowVector.length; i++) {
                int nextRow = currRow + rowVector[i];
                int nextCol = currCol + colVector[i];

                if(canKnightBePlacedOn(nextRow, nextCol, board)) {
                    cellCount++;
                    board[nextRow][nextCol] = true;
                    visited[nextRow][nextCol] = cellCount;
                    if(tourUtil(board, visited,nextRow,nextCol,size,cellCount,rowVector,colVector)) {
                        return true;
                    }
                    cellCount--;
                    board[nextRow][nextCol] = false;
                    visited[nextRow][nextCol] = 0;

                }
            }
            return false;
        }
    }

    private static boolean canKnightBePlacedOn(int row, int col, boolean[][] board) {
        if((row >= 0 && row <= board.length-1)
            && (col >= 0 && col <= board.length-1)
            && !board[row][col]) {
            return true;
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        System.out.println("Knight tours the board as follows: ");
        boolean isFirst = true;
        int count = 1;
        while(count <= 64) {
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[row].length; col++) {
                    if(board[row][col] == count) {
                        count++;
                        if(isFirst) {
                            isFirst = false;
                            System.out.println("Knight started at: {" + row + "," + col +"}");
                        }else {
                            System.out.println("then he moves next to: {" + row + "," + col +"}");
                        }
                    }
                }

            }
        }
    }
}
