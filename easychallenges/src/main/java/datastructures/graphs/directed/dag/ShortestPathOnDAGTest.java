package datastructures.graphs.directed.dag;

import java.util.*;

public class ShortestPathOnDAGTest {
    static class Edge {
        private final String from;
        private final String to;
        private final double cost;

        public Edge(String from, String to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public double getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", cost=" + cost +
                    '}';
        }
    }

    static class Graph {
        private final int nodes;
        private final Map<String, List<Edge>> adjacencyList;

        public Graph(int nodes) {
            this.nodes = nodes;
            this.adjacencyList = new HashMap<>();
        }

        public void addEdge(String from, String to, double cost) {
            Edge edge = new Edge(from, to, cost);
            if (this.adjacencyList.containsKey(from)) {
                this.adjacencyList.get(from).add(edge);
            } else {
                List<Edge> edges = new ArrayList<>();
                edges.add(edge);
                this.adjacencyList.put(from, edges);
            }

            this.adjacencyList.putIfAbsent(to, new ArrayList<>());
        }

        public Set<String> getGraphVertices() {
            return this.adjacencyList.keySet();
        }

        public List<Edge> getOutEdgesFrom(String from) {
            return this.adjacencyList.getOrDefault(from, new ArrayList<>());
        }

        public int getVertexCount() {
            return this.nodes;
        }


    }

    public static void main(String[] args) {

        Graph graph = new Graph(8);
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 6);
        graph.addEdge("B", "C", 4);
        graph.addEdge("B", "D", 4);
        graph.addEdge("B", "E", 11);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "G", 11);
        graph.addEdge("D", "E", -4);
        graph.addEdge("D", "F", 5);
        graph.addEdge("D", "G", 2);
        graph.addEdge("E", "H", 9);
        graph.addEdge("F", "H", 1);
        graph.addEdge("G", "H", 2);

        String[] topologicallySortedNodes = sortInTopologicalOrder(graph);
        System.out.println("Nodes in topological order: " + Arrays.toString(topologicallySortedNodes));
        Map<String, Double> distances = getDistances(graph, topologicallySortedNodes);
        distances.forEach((key, value) -> System.out.println("Distance from {" + topologicallySortedNodes[0] + " to " + key + "} is: [" + value + "]"));
    }

    private static String[] sortInTopologicalOrder(Graph graph) {
        Map<String, Boolean> visited = new HashMap<>();
        for (String node : graph.getGraphVertices()) {
            visited.put(node, false);
        }

        Stack<String> stk = new Stack<>();

        for (String node : graph.getGraphVertices()) {
            if (!visited.getOrDefault(node, false)) {
                sort(node, visited, stk, graph);
            }
        }
        String[] topologicallySortedNodes = new String[graph.getVertexCount()];
        int index = 0;
        while (!stk.isEmpty()) {
            topologicallySortedNodes[index] = stk.pop();
            index++;
        }
        return topologicallySortedNodes;
    }

    private static void sort(String node, Map<String, Boolean> visited, Stack<String> stk, Graph graph) {
        visited.put(node, true);
        for (Edge edge : graph.getOutEdgesFrom(node)) {
            if (!visited.getOrDefault(edge.getTo(), false)) {
                sort(edge.getTo(), visited, stk, graph);
            }
        }
        stk.push(node);
    }

    private static Map<String, Double> getDistances(Graph graph, String[] topologicallySortedNodes) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        Map<String, String> path = new HashMap<>();
        for (String node : graph.getGraphVertices()) {
            visited.put(node, false);
            path.put(node, null);
            distances.put(node, Double.POSITIVE_INFINITY);
        }

        distances.put(topologicallySortedNodes[0], (double) 0);

        for (String vertex : topologicallySortedNodes) {
            visited.put(vertex, true);
            for (Edge edge : graph.getOutEdgesFrom(vertex)) {
                if (visited.get(edge.getTo())) continue;
                double newDistance = (distances.get(vertex) + edge.getCost());
                if (newDistance < distances.get(edge.getTo())) {
                    distances.put(edge.getTo(), newDistance);
                    path.put(edge.getTo(), vertex);
                }
            }
        }
        printPath(path);
        return distances;
    }

    private static void printPath(Map<String, String> path) {
        String at = "H";
        Stack<String> stk = new Stack<>();
        stk.push(at);
        while (at != null) {
            String from = path.get(at);
            if (from != null && from.length() > 0) {
                stk.push(from);
            }
            at = from;
        }

        boolean isFirst = true;
        System.out.print("The shortest path is: {");
        while (!stk.isEmpty()) {
            if (isFirst) {
                System.out.print(stk.pop());
                isFirst = false;
            } else {
                System.out.print(" --> " + stk.pop());
            }
        }
        System.out.println("}");
    }
}
