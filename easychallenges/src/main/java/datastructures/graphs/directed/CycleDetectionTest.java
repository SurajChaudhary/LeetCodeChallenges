package datastructures.graphs.directed;


public class CycleDetectionTest {
    public static void main(String[] args) {
        AdjacencyListBasedGraph graph = new AdjacencyListBasedGraph(9, false);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(3,6);
        graph.addEdge(4,5);
        graph.addEdge(6,5);

        graph.addEdge(7,2);
        graph.addEdge(7,8);
        graph.addEdge(8,9);
        graph.addEdge(9,7);

        graph.print();
        System.out.println("=============================================");
        boolean isCyclic = isCyclic(graph, 9);
        System.out.println("The graph is " + (isCyclic ? "cyclic!" : "not cyclic!"));
    }

    private static boolean isCyclic(AdjacencyListBasedGraph graph, int V) {
        boolean[] visited = new boolean[V+1];
        boolean[] dfsVisited = new boolean[V+1];

        for(int node : graph.getNodes()) {
            if(!visited[node]) {
                if(cyclicUtil(node, visited, dfsVisited, graph)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean cyclicUtil(int node, boolean[] visited, boolean[] dfsVisited, AdjacencyListBasedGraph graph) {

        visited[node] = true;
        dfsVisited[node] = true;

        for(int n : graph.getNeighboursOf(node)) {
            if(!visited[n]) {
                if(cyclicUtil(n, visited, dfsVisited, graph)) {
                    return true;
                }
            } else if(dfsVisited[n]) {
                return true;
            }
        }
        dfsVisited[node] = false;
        return false;
    }
}
