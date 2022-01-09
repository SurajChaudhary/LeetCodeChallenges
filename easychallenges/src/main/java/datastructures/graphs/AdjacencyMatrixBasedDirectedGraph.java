package datastructures.graphs;

import java.util.Arrays;

public class AdjacencyMatrixBasedDirectedGraph {
    private int vertices;
    private int[][] graph;

    public AdjacencyMatrixBasedDirectedGraph(int vertices) {
        if (vertices <= 0) {
            throw new IllegalArgumentException(vertices + " is not a valid number of vertices!!!");
        }
        this.vertices = vertices;
        this.graph = new int[vertices][vertices];
        Arrays.stream(this.graph).forEach(row -> Arrays.fill(row, 0));
    }

    public int getVertices() {
        return this.vertices;
    }

    public void addEdge(int source, int destination) {
        if (source < 0 || destination < 0 || source >= this.vertices || destination >= this.vertices) {
            throw new IllegalArgumentException("Not a valid number of argument!!!");
        }
        this.graph[source][destination] = 1;
    }

    public void print() {
        for (int row = 0; row < this.vertices; row++) {
            System.out.print(row + " -> ");
            for (int col = 0; col < this.vertices; col++) {
                if (this.graph[row][col] == 1) {
                    System.out.print(col + " ");
                }
            }
            System.out.println(" ");
        }
    }
}
