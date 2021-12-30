package datastructures.graphs.undirected;

public class CycleDetectionTest {
    public static void main(String[] args) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(4);
        graph.addEdge(1,2);
        graph.addEdge(1,0);
        graph.addEdge(0,2);
        graph.addEdge(2,3);

        graph.print();
        System.out.println("=============================================");
        boolean isCyclic = isCyclic(graph, 4);
        System.out.println("The graph is " + (isCyclic ? "cyclic!" : "not cyclic!"));
    }

    private static boolean isCyclic(AdjacencyListBasedGraph graph, int V) {
        boolean[] visited = new boolean[V];

        for(int node : graph.getNodes()) {
            if(!visited[node]) {
                if(cyclicUtil(node, visited, graph, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean cyclicUtil(int node, boolean[] visited, AdjacencyListBasedGraph graph, int parent) {
        visited[node] = true;
        for(int n : graph.getNeighboursOf(node)) {
            if(!visited[n]) {
                if(cyclicUtil(n, visited, graph, node)) {
                    return true;
                }
            } else if(n != parent) {
                return true;
            }
        }
        return false;
    }
}
