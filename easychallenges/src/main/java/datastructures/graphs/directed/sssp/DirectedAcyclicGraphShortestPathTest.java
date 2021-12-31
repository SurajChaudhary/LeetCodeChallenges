package datastructures.graphs.directed.sssp;

import java.util.*;

public class DirectedAcyclicGraphShortestPathTest {
    public static void main(String[] args) {
        int nodes = 8;
        StringWeightedGraph graph = new StringWeightedGraph(nodes);
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 6);
        graph.addEdge("B", "C", 4);
        graph.addEdge("B", "D", 4);
        graph.addEdge("B", "E", 11);

        graph.addEdge("C", "G", 11);
        graph.addEdge("C", "D", 8);

        graph.addEdge("D", "E", 4);
        graph.addEdge("D", "F", 5);
        graph.addEdge("D", "G", 2);

        graph.addEdge("E", "H", 9);
        graph.addEdge("F", "H", 1);
        graph.addEdge("G", "H", 2);

        graph.print();
        System.out.println("================== Topological Order ==================");

        Stack<String> topologicalOrder = getTopologicalOrder(graph);
        List<String> sortedNodes = new ArrayList<>(topologicalOrder);
        Collections.reverse(sortedNodes);
        System.out.println("Topological Order of given graph is: " + sortedNodes);

        Map<String, String> prev = new HashMap<>();
        Map<String, Integer> distance = findShortestDistance("A", sortedNodes, graph, prev);
        distance.forEach((key, value) -> System.out.println("Distance from A to " + key + " is: " + value));
        System.out.println("================== PATH ==================");
        printPath("A", "H", prev);
    }

    private static void printPath(String start, String end, Map<String, String> prev) {
        Stack<String> path = new Stack<>();
        String flag = "start";
        String node = end;
        path.push(node);
        do {
            node = prev.get(node);
            path.push(node);
        } while (!flag.equalsIgnoreCase(node));
        System.out.print("{");
        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
        System.out.print("}");
    }

    private static Map<String, Integer> findShortestDistance(String start, List<String> sortedNodes, StringWeightedGraph graph, Map<String, String> prev) {
        Map<String, Integer> distance = new HashMap<>();
        distance.put(start, 0);
        prev.put(start, "start");
        for (int index = 0; index < sortedNodes.size(); index++) {
            String topNode = sortedNodes.get(index);
            if (distance.containsKey(topNode)) {
                for (StringEdge edge : graph.getOutwardEdgesOf(topNode)) {
                    int newDistance = distance.get(topNode) + (int) edge.getCost();
                    if (!distance.containsKey(edge.getTo()) || newDistance < distance.get(edge.getTo())) {
                        distance.put(edge.getTo(), newDistance);
                        prev.put(edge.getTo(), edge.getFrom());
                    }
                }
            }
        }
        return distance;
    }

    private static Stack<String> getTopologicalOrder(StringWeightedGraph graph) {
        Map<String, Boolean> visited = new HashMap<>();
        Stack<String> result = new Stack<>();
        for (String node : graph.getNodes()) {
            if (!visited.getOrDefault(node, false)) {
                topologicalSort(node, visited, graph, result);
            }
        }
        return result;
    }

    private static void topologicalSort(String from, Map<String, Boolean> visited, StringWeightedGraph graph, Stack<String> result) {
        visited.put(from, true);

        for (StringEdge to : graph.getOutwardEdgesOf(from)) {
            if (!visited.getOrDefault(to.getTo(), false)) {
                topologicalSort(to.getTo(), visited, graph, result);
            }
        }
        result.push(from);
    }
}
