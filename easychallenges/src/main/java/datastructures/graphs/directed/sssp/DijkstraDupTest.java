package datastructures.graphs.directed.sssp;

import java.util.*;

public class DijkstraDupTest {
    public static void main(String[] args) {
        int nodes = 5;
        WeightedGraph graph = new WeightedGraph(nodes);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 5);
        graph.addEdge(3, 4, 3);

        graph.print();

        int start = 0;
        int end = 4;
        boolean[] visited = new boolean[nodes];
        Integer[] prev = new Integer[nodes];
        Arrays.fill(prev, null);
        double[] distance = new double[nodes];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(2 * nodes, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return (int) (node1.getDistance() - node2.getDistance());
            }
        });

        solve(start, visited, distance, prev, priorityQueue, graph);

        System.out.println("Minimum cost from {" + start + "} to {" + end + "} is: " + distance[end]);
        printPath(start, end, prev);
    }

    private static void printPath(int start, int end, Integer[] prev) {
        Stack<Integer> path = new Stack<>();
        path.push(end);
        Integer node = end;
        while (node != null) {
            node = prev[node];
            if (node != null)
                path.push(node);
        }
        Collections.reverse(path);
        System.out.println(path);
    }

    private static void solve(int start, boolean[] visited, double[] distance, Integer[] prev, PriorityQueue<Node> priorityQueue, WeightedGraph graph) {
        distance[start] = 0;
        prev[start] = null;

        priorityQueue.offer(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node preferredNode = priorityQueue.poll();
            visited[preferredNode.getValue()] = true;
            for (Edge edge : graph.getOutwardEdgesOf(preferredNode.getValue())) {
                if (visited[edge.getTo()]) {
                    continue;
                }
                double newDistance = distance[preferredNode.getValue()] + edge.getCost();
                if (newDistance < distance[edge.getTo()]) {
                    distance[edge.getTo()] = newDistance;
                    priorityQueue.offer(new Node(edge.getTo(), newDistance));
                    prev[edge.getTo()] = preferredNode.getValue();
                }
            }
        }

    }


}
