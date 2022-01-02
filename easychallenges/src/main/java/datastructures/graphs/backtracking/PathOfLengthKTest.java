package datastructures.graphs.backtracking;


import java.util.*;

public class PathOfLengthKTest {

    static class Edge {
        private int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int getFrom() {
            return from;
        }
        public int getTo() {
            return to;
        }
        public int getCost() {
            return cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

     static class Graph {
        int V;
        Map<Integer, List<Edge>> adjList;

        public Graph(int V) {
            this.V = V;
            this.adjList = new HashMap<>();
        }

        public int nodeCount() {
            return this.V;
        }

        public Set<Integer> nodes() {
            return this.adjList.keySet();
        }

        public List<Edge> edges() {
            List<Edge> edges = new ArrayList<>();
            for (List<Edge> list : this.adjList.values()) {
                edges.addAll(list);
            }
            return edges;
        }

        public void addEdge(int from, int to, int cost) {
            Edge fromEdge = new Edge(from, to, cost);
            if (this.adjList.containsKey(from)) {
                this.adjList.get(from).add(fromEdge);
            } else {
                List<Edge> neighbors = new ArrayList<>();
                neighbors.add(fromEdge);
                this.adjList.put(from, neighbors);
            }
            Edge toEdge = new Edge(to, from, cost);
            if (this.adjList.containsKey(to)) {
                this.adjList.get(to).add(toEdge);
            } else {
                List<Edge> neighbors = new ArrayList<>();
                neighbors.add(toEdge);
                this.adjList.put(to, neighbors);
            }
        }

        public int[] inDegreeOfNodes() {
            if (this.adjList == null || this.adjList.size() == 0) {
                return null;
            }
            int[] inDegrees = new int[this.nodeCount()];
            Arrays.fill(inDegrees, 0);
            for (int node : this.nodes()) {
                for (Edge edge : this.getNeighborsOf(node)) {
                    inDegrees[edge.getTo()]++;
                }
            }
            return inDegrees;
        }


        public void print() {
            System.out.println("================ Printing Graph ================");
            this.adjList.forEach((from, neighbors) -> System.out.println("Adjacent nodes of " + from + " are: " + neighbors));
            System.out.println("================ Printing Complete ================");
        }

        public List<Edge> getNeighborsOf(int from) {
            return this.adjList.getOrDefault(from, new ArrayList<>());
        }
    }




    public static void main(String[] args) {
        int V = 9;
        Graph g = new Graph(9);
        // making above shown graph
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        g.print();

        int src = 0;
        int k = 62;

        if(pathWithCostLeastKExists(src, k, g, V))
            System.out.println("YES");
        else
            System.out.println("NO");

        k = 60;
        if(pathWithCostLeastKExists(src, k, g, V))
            System.out.println("YES");
        else
            System.out.println("NO");

    }

    private static boolean pathWithCostLeastKExists(int src, int k, Graph g , int V) {
        boolean[] visited = new boolean[V];
        if(dfsUtil(src, k, g, visited)) {
            return true;
        }
        return false;

    }

    private static boolean dfsUtil(int src, int k, Graph g, boolean[] visited) {
        visited[src] = true;
        if(k == 0) {
            return true;
        }
        for(Edge edge : g.getNeighborsOf(src)){
            if(!visited[edge.getTo()]) {
                if(dfsUtil(edge.getTo(), k- edge.getCost(), g, visited)) {
                    return true;
                }
            }

        }
        visited[src] = false;
        return  false;
    }


}
