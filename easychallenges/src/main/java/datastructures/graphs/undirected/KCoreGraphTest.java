package datastructures.graphs.undirected;

import datastructures.graphs.AdjacencyListBasedUndirectedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KCoreGraphTest {
    public static void main(String[] args) {
        AdjacencyListBasedUndirectedGraph graph = new AdjacencyListBasedUndirectedGraph(9);
        graph.addEdge(0,1);//a - b
        graph.addEdge(0,2); // a - c
        graph.addEdge(0,3); // a - d


        graph.addEdge(1,3); // b - d
        graph.addEdge(2,3); // c - d
        graph.addEdge(2,4); // c - f

        graph.addEdge(3,5); // d - e
        graph.addEdge(3,6); // d - g
        graph.addEdge(3,7); // d - h

        graph.addEdge(5,6); // e - g
        graph.addEdge(5,4); // e - f
        graph.addEdge(5,7); // e - h
        graph.addEdge(5,8); // e - m

        graph.addEdge(6,7); // g - h
        graph.addEdge(6,8); // g - m
        graph.addEdge(7,8); // h - m

        graph.print();

        int k = 3;
        findKCoreGraph(graph, k);

    }

    private static void findKCoreGraph(AdjacencyListBasedUndirectedGraph graph, int k) {
        int[] degrees = graph.getDegrees();
        System.out.println("Degrees: " + Arrays.toString(degrees));

        while(true) {
            boolean canBreak = true;
            Set<Integer> nodes = new HashSet<>(graph.getNodes());
            for(int node : nodes) {
                if(degrees[node] < k) {
                    graph.deleteNode(node, degrees);
                    canBreak = false;
                }
            }
            if(canBreak) break;
        }

        graph.print();
    }
}
