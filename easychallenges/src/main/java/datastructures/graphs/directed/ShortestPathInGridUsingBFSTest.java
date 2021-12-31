package datastructures.graphs.directed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class ShortestPathInGridUsingBFSTest {
    public static void main(String[] args) {
        String[][] grid = new String[5][7];
        for (String[] row : grid) {
            Arrays.fill(row, ".");
        }
        grid[0][3] = "#";
        grid[1][1] = "#";
        grid[1][5] = "#";
        grid[2][1] = "#";
        grid[3][2] = "#";
        grid[3][3] = "#";
        grid[4][0] = "#";
        grid[4][2] = "#";
        grid[4][5] = "#";

        print(grid);

        int startRow = 0;
        int startCol = 0;
        int destRow = 4;
        int destCol = 3;

        int totalSteps = findShortestPath(startRow, startCol, destRow, destCol, grid);
        System.out.println("Total steps taken are: " + totalSteps);
    }

    private static int findShortestPath(int startRow, int startCol, int destRow, int destCol, String[][] grid) {

        int[] rowVector = new int[4];
        int[] colVector = new int[4];
        populateDirectionVectors(rowVector, colVector);
        int totalSteps = 0;

        Queue<Integer> x_q = new LinkedList<>();
        Queue<Integer> y_q = new LinkedList<>();

        boolean[][] visited = new boolean[5][7];
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        String[][] pathGrid = new String[5][7];


        x_q.add(startRow);
        y_q.add(startCol);
        visited[startRow][startCol] = true;
        pathGrid[startRow][startCol] = "S";

        while (!x_q.isEmpty()) {
            int row = x_q.poll();
            int col = y_q.poll();
            if ((row == destRow) && (col == destCol)) {
                pathGrid[destRow][destCol] = "E";
                break;
            } else {
                addValidNeighboursToQueue(row, col, visited, grid, x_q, y_q, rowVector, colVector, pathGrid);
                totalSteps++;
            }

        }
        System.out.println("====================================");
        print(visited);
        System.out.println("===============PATH=====================");
        print(pathGrid);
        System.out.println("====================================");
        return totalSteps;
    }

    private static void addValidNeighboursToQueue(
            int row,
            int col,
            boolean[][] visited,
            String[][] grid,
            Queue<Integer> x_q,
            Queue<Integer> y_q,
            int[] rowVector,
            int[] colVector, String[][] pathGrid) {
        for (int i = 0; i < 4; i++) {
            int rr = row + rowVector[i];
            int cc = col + colVector[i];

            if (rr < 0 || cc < 0 || rr > grid.length - 1 || cc > grid[0].length - 1 || visited[rr][cc] || Objects.equals(grid[rr][cc], "#")) {
                continue;
            }

            x_q.add(rr);
            y_q.add(cc);
            pathGrid[rr][cc] = "{" + row + "," + col + "}";
            visited[rr][cc] = true;

        }
    }

    private static void populateDirectionVectors(int[] rowVector, int[] colVector) {
        rowVector[0] = -1;
        rowVector[1] = 1;
        rowVector[2] = 0;
        rowVector[3] = 0;

        colVector[0] = 0;
        colVector[1] = 0;
        colVector[2] = -1;
        colVector[3] = 1;
    }

    private static void print(String[][] grid) {
        for (String[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void print(boolean[][] grid) {
        for (boolean[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
