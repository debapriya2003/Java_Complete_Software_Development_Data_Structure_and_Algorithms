package Graphs.Learning;
import java.util.*;

/**
 * =========================================================
 * DEPTH FIRST SEARCH (DFS) IN GRAPH
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS DFS?
 * ---------------------------------------------------------
 * Depth First Search (DFS) is a GRAPH TRAVERSAL algorithm
 * that explores vertices by going as DEEP as possible
 * before backtracking.
 *
 * DFS uses:
 * - Recursion (implicit stack) OR
 * - Explicit Stack
 *
 * ---------------------------------------------------------
 * 2. KEY CHARACTERISTICS OF DFS
 * ---------------------------------------------------------
 * ✔ Depth-wise traversal
 * ✔ Uses STACK (recursion or explicit)
 * ✔ Useful for exploring all paths
 * ✔ Backtracking-based traversal
 *
 * ---------------------------------------------------------
 * 3. CORE LOGIC OF DFS
 * ---------------------------------------------------------
 * 1. Start from a source vertex
 * 2. Mark current vertex as visited
 * 3. Visit one unvisited neighbor
 * 4. Repeat step 2 & 3 recursively
 * 5. Backtrack when no unvisited neighbor exists
 *
 * ---------------------------------------------------------
 * 4. WHY STACK / RECURSION?
 * ---------------------------------------------------------
 * Stack ensures:
 * - Deep exploration first
 * - Natural backtracking
 *
 * ---------------------------------------------------------
 * 5. DFS VS BFS (SHORT)
 * ---------------------------------------------------------
 * DFS:
 * - Uses Stack / Recursion
 * - Depth-wise
 * - Used for cycle detection, SCC
 *
 * BFS:
 * - Uses Queue
 * - Level-wise
 * - Shortest path (unweighted)
 *
 * ---------------------------------------------------------
 * 6. APPLICATIONS OF DFS
 * ---------------------------------------------------------
 * ✔ Connected components
 * ✔ Cycle detection
 * ✔ Topological sorting
 * ✔ Strongly connected components
 * ✔ Maze & path finding
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity : O(V + E)
 * Space Complexity: O(V)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY
 * ---------------------------------------------------------
 * DFS explores a graph by going deep first using stack.
 *
 * =========================================================
 * BELOW IS A JAVA IMPLEMENTATION OF DFS
 * =========================================================
 */

public class DFS {

    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adjList;

    /**
     * Constructor to initialize graph
     */
    public DFS(int vertices) {
        this.V = vertices;
        adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Add an edge (UNDIRECTED graph)
     */
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    /**
     * Performs DFS traversal
     *
     * @param start starting vertex
     */
    public void dfsTraversal(int start) {

        boolean[] visited = new boolean[V];

        System.out.print("DFS Traversal: ");

        // Start DFS from given vertex
        dfsUtil(start, visited);

        System.out.println();
    }

    /**
     * Recursive DFS utility function
     */
    private void dfsUtil(int node, boolean[] visited) {

        // Mark current node as visited
        visited[node] = true;
        System.out.print(node + " ");

        // Visit all unvisited neighbors
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    /**
     * DFS for DISCONNECTED GRAPH
     */
    public void dfsDisconnected() {

        boolean[] visited = new boolean[V];
        System.out.print("DFS (Disconnected Graph): ");

        // Run DFS from every unvisited vertex
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsUtil(i, visited);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=== Depth First Search (DFS) ===\n");

        /*
         * Graph structure:
         *
         * 0 -- 1 -- 2
         * |         |
         * 4 ------- 3
         *
         * DFS starting from 0:
         * Output (one possible): 0 1 2 3 4
         */

        DFS graph = new DFS(5);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        graph.dfsTraversal(0);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DFS ON DISCONNECTED GRAPH:");

        // Disconnected graph example
        DFS disconnectedGraph = new DFS(6);
        disconnectedGraph.addEdge(0, 1);
        disconnectedGraph.addEdge(2, 3);
        disconnectedGraph.addEdge(4, 5);

        disconnectedGraph.dfsDisconnected();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAY:");
        System.out.println("DFS is ideal when deep exploration or backtracking is needed.");
    }
}
