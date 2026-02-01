package Graphs.Learning;

import java.util.*;

/**
 * =========================================================
 * CONNECTED COMPONENTS IN AN UNDIRECTED GRAPH
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS A CONNECTED COMPONENT?
 * ---------------------------------------------------------
 * A Connected Component is a MAXIMAL set of vertices such that:
 * - Every vertex is reachable from every other vertex in the same set.
 *
 * In simple words:
 * - Inside a component → all nodes are connected
 * - Between components → no path exists
 *
 * ---------------------------------------------------------
 * 2. WHEN DO CONNECTED COMPONENTS EXIST?
 * ---------------------------------------------------------
 * - Mainly discussed in UNDIRECTED GRAPHS
 * - If graph is connected → only ONE component
 * - If graph is disconnected → MULTIPLE components
 *
 * ---------------------------------------------------------
 * 3. CORE LOGIC TO FIND CONNECTED COMPONENTS
 * ---------------------------------------------------------
 * KEY IDEA:
 * - Each DFS/BFS starting from an UNVISITED vertex
 *   discovers EXACTLY ONE connected component.
 *
 * ---------------------------------------------------------
 * 4. STEP-BY-STEP LOGIC
 * ---------------------------------------------------------
 * 1. Maintain a visited[] array
 * 2. Initialize componentCount = 0
 * 3. Traverse all vertices:
 *      - If vertex is UNVISITED:
 *          → Run DFS/BFS from that vertex
 *          → Mark all reachable vertices as visited
 *          → Increment componentCount
 *
 * Each DFS/BFS call = ONE connected component
 *
 * ---------------------------------------------------------
 * 5. WHY DFS / BFS WORKS
 * ---------------------------------------------------------
 * - DFS/BFS visits all vertices reachable from the start node
 * - Since graph is UNDIRECTED, reachability is symmetric
 * - When traversal finishes, entire component is covered
 *
 * ---------------------------------------------------------
 * 6. IMPORTANT OBSERVATIONS
 * ---------------------------------------------------------
 * - One DFS/BFS run → One component
 * - Multiple DFS/BFS runs → Graph is disconnected
 * - Single DFS/BFS run → Graph is connected
 *
 * ---------------------------------------------------------
 * 7. SPECIAL CASES
 * ---------------------------------------------------------
 * - Isolated vertex → component of size 1
 * - Fully connected graph → only one component
 *
 * ---------------------------------------------------------
 * 8. DIRECTED GRAPH NOTE
 * ---------------------------------------------------------
 * - Connected Components apply to UNDIRECTED graphs
 * - Directed graphs use Strongly Connected Components (SCC)
 *
 * ---------------------------------------------------------
 * 9. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity : O(V + E)
 * Space Complexity: O(V)
 *
 * ---------------------------------------------------------
 * 10. ONE-LINE SUMMARY
 * ---------------------------------------------------------
 * A connected component is a group of vertices where each
 * vertex is reachable from every other vertex.
 *
 * =========================================================
 * BELOW IS A JAVA IMPLEMENTATION USING DFS
 * =========================================================
 */

public class ConnectedComponents {

    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adjList;

    /**
     * Constructor to initialize the graph
     */
    public ConnectedComponents(int vertices) {
        this.V = vertices;
        adjList = new ArrayList<>();

        // Initialize adjacency list for each vertex
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Adds an edge (UndIRECTED graph)
     */
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    /**
     * DFS traversal to mark all vertices in the same component
     */
    private void dfs(int node, boolean[] visited) {

        // Mark current node as visited
        visited[node] = true;

        // Visit all unvisited neighbors
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited);
            }
        }
    }

    /**
     * Counts number of connected components in the graph
     */
    public int countConnectedComponents() {

        boolean[] visited = new boolean[V];
        int componentCount = 0;

        // Traverse all vertices
        for (int i = 0; i < V; i++) {

            // If vertex is unvisited, start DFS
            if (!visited[i]) {
                dfs(i, visited);
                componentCount++;
            }
        }

        return componentCount;
    }

    /**
     * Prints all connected components (optional utility)
     */
    public void printConnectedComponents() {

        boolean[] visited = new boolean[V];
        int componentNumber = 1;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                System.out.print("Component " + componentNumber + ": ");
                dfsPrint(i, visited);
                System.out.println();
                componentNumber++;
            }
        }
    }

    private void dfsPrint(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfsPrint(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Connected Components in Graph ===\n");

        /*
         * Graph structure:
         *
         * 0 -- 1      3 -- 4
         *      |
         *      2
         *
         * Connected Components:
         * Component 1 → {0, 1, 2}
         * Component 2 → {3, 4}
         */

        ConnectedComponents graph = new ConnectedComponents(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        // Count components
        int count = graph.countConnectedComponents();
        System.out.println("Total Connected Components: " + count);

        // Print components
        graph.printConnectedComponents();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAY:");
        System.out.println("Run DFS/BFS from every unvisited node and count runs.");
    }
}
