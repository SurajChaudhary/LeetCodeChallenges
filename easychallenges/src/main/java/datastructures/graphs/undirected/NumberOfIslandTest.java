package datastructures.graphs.undirected;

public class NumberOfIslandTest {
    public static void main(String[] args) {
        int[][] grid = new int[8][12];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 12; col++) {
                grid[row][col] = 0;
            }
        }

        grid[0][2] = 1;
        grid[0][7] = 1;

        grid[1][7] = 1;
        grid[1][8] = 1;
        grid[1][9] = 1;

        int totalIslands = numberOfIslands(grid);
        System.out.println(totalIslands);

    }

    private static int numberOfIslands(int[][] grid) {
        int count = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 12; col++) {
                if (grid[row][col] == 1) {
                    count += dfs(row, col, grid, 8, 12);
                }
            }
        }
        return count;
    }

    private static int dfs(int row, int col, int[][] grid, int ROWS, int COLS) {
        if (row < 0 || col < 0 || row >= ROWS || col >= COLS || grid[row][col] == 0) {
            return 0;
        }

        grid[row][col] = 0;
        dfs(row - 1, col, grid, ROWS, COLS);
        dfs(row + 1, col, grid, ROWS, COLS);
        dfs(row, col - 1, grid, ROWS, COLS);
        dfs(row, col + 1, grid, ROWS, COLS);

        return 1;
    }


}
