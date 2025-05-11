package ch4trees_graphs;
import java.util.*;
/**

Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a
route between two nodes.

*/
public class RouteBetweenGraphNodes1 {

    public static boolean hasRouteBFS(Map<Integer, List<Integer>> graph, int start, int end) {
        if (start == end) return true;  // Trivial case

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : graph.getOrDefault(current, new ArrayList<>())) {
                if (neighbor == end) {
                    return true;  // Path found!
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return false;  // No path exists
    }

    public static void main(String[] args) {
        // Example directed graph (adjacency list)
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2));
        graph.put(2, Arrays.asList(0, 3));
        graph.put(3, Arrays.asList(3));  // Self-loop

        int start = 1;
        int end = 3;
        boolean result = hasRouteBFS(graph, start, end);
        System.out.println("Route Between Graph Nodes(Using BFS):");  // Output: true
        System.out.println("Path exists from " + start + " to " + end + "? " + result);  // Output: true
    }
}
