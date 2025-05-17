package ch4trees_graphs;
import java.util.*;
/**

Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
route between two nodes.

*/
class Graph_Solu2RouteBetGraphNodes1 {
        private int vertices;
        private List<List<Integer>> adjList;

        public Graph_Solu2RouteBetGraphNodes1(int v) {
            vertices = v;
            adjList = new ArrayList<>(); //list elements are still empty
            for (int i = 0; i < v; i++) {
                adjList.add(new LinkedList<>());// add a list in each array element
            }
        }

        public void addEdge(int src, int dest) {
            adjList.get(src).add(dest);
        }

        // BFS to check if there is a route from start to end
        public boolean hasRouteUsingBFS(int start, int end) {
            if (start == end) return true;

            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll(); //Retrieves and removes the head of this queue, or returns null if this queue is empty.
                for (int neighbor : adjList.get(current)) {
                    if (neighbor == end) {
                        return true;
                    }
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }

            return false; 
        }

        public static void main(String[] args) {
            Graph_Solu2RouteBetGraphNodes1 graph = new Graph_Solu2RouteBetGraphNodes1(6);

            graph.addEdge(0, 1);
            graph.addEdge(0, 5);
            graph.addEdge(1, 2);
            graph.addEdge(1, 3);
            graph.addEdge(1, 4);
            graph.addEdge(3, 2);
            graph.addEdge(4, 3);


            System.out.println(graph.hasRouteUsingBFS(0, 2)); // true
            System.out.println(graph.hasRouteUsingBFS(5, 2)); // false
            System.out.println(graph.hasRouteUsingBFS(1, 4)); // true
        }
    }
