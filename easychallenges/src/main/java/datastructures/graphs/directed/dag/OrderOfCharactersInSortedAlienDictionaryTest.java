package datastructures.graphs.directed.dag;

import java.util.*;

public class OrderOfCharactersInSortedAlienDictionaryTest {
    public static void main(String[] args) {
        String[] words = {"caa", "aaa", "aab"};
        printOrder(words, 3, 3);
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        printOrder(words1, 5, 4);
    }

    private static void printOrder(String[] words, int numberOfWords, int wordLength) {
        DirectedGraph graph = new DirectedGraph(wordLength);

        for (int i = 0; i < numberOfWords - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {

                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.addEdge(word1.charAt(j), word2.charAt(j));
                    break;
                }
            }
        }
        printTopo(graph);
    }

    private static void printTopo(DirectedGraph graph) {
        int[] degree = graph.inDegreeOfNodes();
        Queue<Character> q = new LinkedList<>();

        for (char c : graph.nodes()) {
            if (degree[c - 'a'] == 0) {
                q.add(c);
            }
        }
        List<Character> list = new ArrayList<>();
        while (!q.isEmpty()) {
            Character cc = q.poll();
            list.add(cc);
            for (char to : graph.getNeighborsOf(cc)) {
                degree[to - 'a']--;
                if (degree[to - 'a'] == 0) {
                    q.add(to);
                }
            }
        }
        System.out.println(list);
    }

    static class DirectedGraph {
        int V;
        Map<Character, List<Character>> adjList;

        public DirectedGraph(int V) {
            this.V = V;
            this.adjList = new HashMap<>();
        }

        public int nodeCount() {
            return this.V;
        }

        public Set<Character> nodes() {
            return this.adjList.keySet();
        }

        public void addEdge(char from, char to) {
            if (this.adjList.containsKey(from)) {
                this.adjList.get(from).add(to);
            } else {
                List<Character> neighbors = new ArrayList<>();
                neighbors.add(to);
                this.adjList.put(from, neighbors);
            }
            this.adjList.putIfAbsent(to, new ArrayList<>());
        }

        public int[] inDegreeOfNodes() {
            if (this.adjList == null || this.adjList.size() == 0) {
                return null;
            }
            int[] inDegrees = new int[this.nodeCount()];
            Arrays.fill(inDegrees, 0);
            for (char node : this.nodes()) {
                for (char to : this.getNeighborsOf(node)) {
                    inDegrees[to - 'a']++;
                }
            }
            return inDegrees;
        }


        public void print() {
            System.out.println("================ Printing Graph ================");
            this.adjList.forEach((from, neighbors) -> System.out.println("Adjacent nodes of " + from + " are: " + neighbors));
            System.out.println("================ Printing Complete ================");
        }

        public List<Character> getNeighborsOf(char from) {
            return this.adjList.getOrDefault(from, new ArrayList<>());
        }
    }
}
