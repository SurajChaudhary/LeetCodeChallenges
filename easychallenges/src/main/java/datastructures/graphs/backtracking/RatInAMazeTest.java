package datastructures.graphs.backtracking;

public class RatInAMazeTest {
    public static void main(String[] args) {

        int maze[][] = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};
        int size = maze.length;
        int[][] visited = new int[size][size];

        int start_x = 0;
        int start_y = -1;

        int dest_row = size - 1;
        int dest_col = size - 1;

        int[] rowVector = {1, 0};
        int[] colVector = {0, 1};
        int cellCount = 0;

        tourUtil(
                maze,
                visited,
                start_x,
                start_y,
                dest_row,
                dest_col,
                size,
                cellCount,
                rowVector,
                colVector);

    }

    private static void tourUtil(
            int[][] maze,
            int[][] visited,
            int currRow,
            int currCol,
            int dest_row,
            int dest_col,
            int size,
            int cellCount,
            int[] rowVector,
            int[] colVector) {
        if ((currRow == dest_row) && (currCol == dest_col)) {
            printMaze(visited, size);
        } else {
            for (int i = 0; i < rowVector.length; i++) {
                int nextRow = currRow + rowVector[i];
                int nextCol = currCol + colVector[i];

                if (canRatMoveTo(nextRow, nextCol, visited, maze)) {
                    cellCount++;
                    visited[nextRow][nextCol] = cellCount;
                    tourUtil(
                            maze,
                            visited,
                            nextRow,
                            nextCol,
                            dest_row,
                            dest_col,
                            size,
                            cellCount,
                            rowVector,
                            colVector);
                    cellCount--;
                    visited[nextRow][nextCol] = 0;

                }
            }
        }
        return;
    }

    private static boolean canRatMoveTo(int row, int col, int[][] visited, int[][] maze) {
        if ((row >= 0 && row <= maze.length - 1)
                && (col >= 0 && col <= maze.length - 1)
                && visited[row][col] == 0
                && maze[row][col] == 1) {
            return true;
        }
        return false;
    }

    private static void printMaze(int[][] board, int size) {
        System.out.println("Rat tours the board as follows: ");
        boolean isFirst = true;
        int count = 1;
        while (count <= (size * size)) {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[row][col] == count) {
                        count++;
                        if (isFirst) {
                            isFirst = false;
                            System.out.println("Rat started at: {" + row + "," + col + "}");
                        } else {
                            System.out.println("then he moves next to: {" + row + "," + col + "}");
                        }
                    }
                }

            }
        }
    }
}
