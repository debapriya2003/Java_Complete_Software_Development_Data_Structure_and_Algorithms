package Graphs.Learning;

import java.util.*;

/**
 * =========================================================
 * GRAPH REPRESENTATION IN JAVA
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS GRAPH REPRESENTATION?
 * ---------------------------------------------------------
 * Graph representation is the way we STORE a graph in memory
 * so that we can efficiently perform operations like:
 * - Traversal (BFS / DFS)
 * - Shortest path
 * - Cycle detection
 *
 * The two MOST IMPORTANT representations are:
 * 1️⃣ Adjacency Matrix
 * 2️⃣ Adjacency List
 *
 * ---------------------------------------------------------
 * 2. ADJACENCY MATRIX REPRESENTATION
 * ---------------------------------------------------------
 * - A 2D matrix of size V x V
 * - matrix[i][j] = 1 → edge exists between i and j
 * - matrix[i][j] = 0 → no edge
 *
 * ✔ Fast edge lookup: O(1)
 * ❌ High space usage: O(V²)
 *
 * BEST USED WHEN:
 * - Graph is dense
 * - Number of vertices is small
 *
 * ---------------------------------------------------------
 * 3. ADJACENCY LIST REPRESENTATION
 * ---------------------------------------------------------
 * - Each vertex stores a LIST of its neighbors
 * - Implemented using ArrayList<List<Integer>>
 *
 * ✔ Space efficient: O(V + E)
 * ✔ Most commonly used in coding problems
 *
 * BEST USED WHEN:
 * - Graph is sparse
 * - Large number of vertices
 *
 * ---------------------------------------------------------
 * 4. DIRECTED vs UNDIRECTED
 * ---------------------------------------------------------
 * Undirected Graph:
 *   addEdge(u, v) → u ↔ v
 *
 * Directed Graph:
 *   addEdge(u, v) → u → v
 *
 * ---------------------------------------------------------
 * 5. WEIGHTED GRAPHS
 * ---------------------------------------------------------
 * For weighted graphs:
 * - Adjacency List stores (neighbor, weight) pairs
 *
 * ---------------------------------------------------------
 * 6. BELOW WE IMPLEMENT:
 * ---------------------------------------------------------
 * ✔ Adjacency Matrix
 * ✔ Adjacency List (Unweighted, Undirected)
 *
 * =========================================================
 */

public class GraphRepresentation {

    /* =====================================================
     * PART 1: ADJACENCY MATRIX IMPLEMENTATION
     * =====================================================
     */

    static class GraphMatrix {

        int V;                 // Number of vertices
        int[][] matrix;        // Adjacency matrix

        /**
         * Constructor
         */
        GraphMatrix(int V) {
            this.V = V;
            matrix = new int[V][V];
        }

        /**
         * Add edge (Undirected Graph)
         */
        void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        /**
         * Print adjacency matrix
         */
        void printMatrix() {
            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    /* =====================================================
     * PART 2: ADJACENCY LIST IMPLEMENTATION
     * =====================================================
     */

    static class GraphList {

        int V;                              // Number of vertices
        List<List<Integer>> adjList;        // Adjacency list

        /**
         * Constructor
         */
        GraphList(int V) {
            this.V = V;
            adjList = new ArrayList<>();

            // Initialize empty lists
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        /**
         * Add edge (Undirected Graph)
         */
        void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        /**
         * Print adjacency list
         */
        void printList() {
            System.out.println("Adjacency List:");
            for (int i = 0; i < V; i++) {
                System.out.print(i + " -> ");
                for (int neighbor : adjList.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }
    }

    /* =====================================================
     * MAIN METHOD
     * =====================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Graph Representation in Java ===\n");

        /*
         * Graph structure:
         *
         * 0 -- 1
         * |    |
         * 4 -- 3
         *      |
         *      2
         */

        int vertices = 5;

        /* ---------- Adjacency Matrix ---------- */
        GraphMatrix matrixGraph = new GraphMatrix(vertices);
        matrixGraph.addEdge(0, 1);
        matrixGraph.addEdge(0, 4);
        matrixGraph.addEdge(1, 3);
        matrixGraph.addEdge(3, 4);
        matrixGraph.addEdge(3, 2);

        matrixGraph.printMatrix();

        System.out.println();

        /* ---------- Adjacency List ---------- */
        GraphList listGraph = new GraphList(vertices);
        listGraph.addEdge(0, 1);
        listGraph.addEdge(0, 4);
        listGraph.addEdge(1, 3);
        listGraph.addEdge(3, 4);
        listGraph.addEdge(3, 2);

        listGraph.printList();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY COMPARISON");
        System.out.println("-".repeat(60));
        System.out.println("Adjacency Matrix:");
        System.out.println("  Space: O(V²)");
        System.out.println("  Edge Check: O(1)");

        System.out.println("\nAdjacency List:");
        System.out.println("  Space: O(V + E)");
        System.out.println("  Edge Check: O(deg(V))");

        System.out.println("\nNOTE:");
        System.out.println("✔ Adjacency List is preferred in interviews");
        System.out.println("✔ Matrix is useful for dense graphs");
    }
}
