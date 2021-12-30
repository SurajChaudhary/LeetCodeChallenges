package datastructures.graphs.directed.sssp;

import java.util.*;

public class DijkstraTest {
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
        boolean [] visited = new boolean[nodes];
        double [] dist = new double[nodes];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        int [] prev = new int[nodes];
        Arrays.fill(prev, -1);
        final double EPS = 1e-6;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(2 * nodes, new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                if(Math.abs(node1.getDistance()-node2.getDistance()) < EPS) return 0;
                return (node1.getDistance() - node2.getDistance()) > 0 ? 1 : -1;
            }
        });
        solveDijkstra(start, end, visited, dist, prev, priorityQueue, graph);

        System.out.println("Distances from " + start + " to all other nodes are: " + Arrays.toString(dist));

        printPathBetween(start, end, prev);

    }

    private static void printPathBetween(int start, int end, int[] prev) {
        List<Integer> path = new ArrayList<>();
        for(int at = end; prev[at]!=-1;at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        path.add(0,start);
        System.out.println(path);
    }

    private static void solveDijkstra(int start, int end, boolean[] visited, double[] dist, int[] prev, PriorityQueue<Node> priorityQueue, WeightedGraph graph) {
        dist[start] = 0;
        priorityQueue.offer(new Node(start, 0));

        while(!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            visited[node.getValue()] = true;

            if(dist[node.getValue()] < node.getDistance()) continue;

            List<Edge> outwardEdges = graph.getOutwardEdgesOf((int) node.getValue());
            if(outwardEdges == null) continue;
            for(Edge edge : outwardEdges) {
                if(visited[edge.getTo()]) continue;

                double newDist = dist[edge.getFrom()] + edge.getCost();
                if(newDist < dist[edge.getTo()]) {
                    dist[edge.getTo()] = newDist;
                    prev[edge.getTo()] = edge.getFrom();
                    priorityQueue.offer(new Node(edge.getTo(), dist[edge.getTo()]));
                }
            }
        }
    }
}
