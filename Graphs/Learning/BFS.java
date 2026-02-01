package Graphs.Learning;

import java.util.*;

/**
 * =========================================================
 * BREADTH FIRST SEARCH (BFS) IN GRAPH
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS BFS?
 * ---------------------------------------------------------
 * Breadth First Search (BFS) is a GRAPH TRAVERSAL algorithm
 * that explores vertices LEVEL BY LEVEL.
 *
 * BFS starts from a source vertex and:
 * - Visits all its immediate neighbors first
 * - Then moves to neighbors of neighbors
 *
 * BFS uses a QUEUE data structure.
 *
 * ---------------------------------------------------------
 * 2. KEY CHARACTERISTICS OF BFS
 * ---------------------------------------------------------
 * ✔ Level-wise traversal
 * ✔ Uses QUEUE (FIFO)
 * ✔ Finds SHORTEST PATH in UNWEIGHTED graphs
 * ✔ Used for connectivity & distance problems
 *
 * ---------------------------------------------------------
 * 3. CORE LOGIC OF BFS
 * ---------------------------------------------------------
 * 1. Mark the starting node as visited
 * 2. Push it into a queue
 * 3. While queue is not empty:
 *      - Remove front element
 *      - Visit all unvisited neighbors
 *      - Mark them visited
 *      - Push them into the queue
 *
 * ---------------------------------------------------------
 * 4. WHY QUEUE IS USED?
 * ---------------------------------------------------------
 * Queue ensures:
 * - Nodes discovered earlier are processed earlier
 * - This guarantees level-by-level traversal
 *
 * ---------------------------------------------------------
 * 5. BFS VS DFS (SHORT)
 * ---------------------------------------------------------
 * BFS:
 * - Uses Queue
 * - Level-wise
 * - Shortest path (unweighted)
 *
 * DFS:
 * - Uses Stack / Recursion
 * - Depth-wise
 *
 * ---------------------------------------------------------
 * 6. APPLICATIONS OF BFS
 * ---------------------------------------------------------
 * ✔ Shortest path in unweighted graph
 * ✔ Finding connected components
 * ✔ Bipartite graph checking
 * ✔ Minimum moves problems
 * ✔ Network broadcasting
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
 * BFS explores a graph level by level using a queue.
 *
 * =========================================================
 * BELOW IS A JAVA IMPLEMENTATION OF BFS
 * =========================================================
 */

public class BFS {

    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adjList;

    /**
     * Constructor to initialize graph
     */
    public BFS(int vertices) {
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
     * Performs Breadth First Search traversal
     *
     * @param start starting vertex
     */
    public void bfsTraversal(int start) {

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        // Step 1: Mark start node as visited
        visited[start] = true;

        // Step 2: Add start node to queue
        queue.add(start);

        System.out.print("BFS Traversal: ");

        // Step 3: Process queue
        while (!queue.isEmpty()) {

            // Remove front element
            int node = queue.poll();
            System.out.print(node + " ");

            // Visit all unvisited neighbors
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=== Breadth First Search (BFS) ===\n");

        /*
         * Graph structure:
         *
         * 0 -- 1 -- 2
         * |         |
         * 4 ------- 3
         *
         * BFS starting from 0:
         * Output: 0 1 4 2 3
         */

        BFS graph = new BFS(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 3);

        graph.bfsTraversal(0);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAY:");
        System.out.println("Use BFS when shortest path or level-wise traversal is needed.");
    }
}
