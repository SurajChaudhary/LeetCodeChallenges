package datastructures.graphs.undirected;

public class MaxAreaOfIslandTest {
    public static void main(String[] args) {
        int[][] grid = new int[8][12];

        for(int row = 0; row < 8; row++) {
            for(int col=0; col < 12; col++) {
                grid[row][col] = 0;
            }
        }

        grid[0][2] = 1;
        grid[0][7] = 1;

        grid[1][7] = 1;
        grid[1][8] = 1;
        grid[1][9] = 1;

        int maxArea = maxArea(grid, 8, 12);
        System.out.println(maxArea);

    }

    private static int maxArea(int[][] grid, int ROWS, int COLS) {
        int area = 0;
        for(int row = 0; row < ROWS; row++) {
            for(int col=0; col < COLS; col++) {
                if(grid[row][col] == 1) {
                    area = Math.max(area, dfs(row, col, grid, ROWS, COLS));
                }
            }
        }
        return area;
    }

    private static int dfs(int row, int col, int[][] grid, int ROWS, int COLS) {
        if(row < 0 || col < 0 || row >= ROWS || col >= COLS || grid[row][col] == 0) {
            return 0;
        }

        grid[row][col] = 0;
        int count = 1;

        count += dfs(row-1, col, grid,ROWS,COLS)
                + dfs(row+1, col, grid,ROWS,COLS)
                + dfs(row, col-1, grid,ROWS,COLS)
                + dfs(row, col+1, grid,ROWS,COLS);

        return count;
    }
}
