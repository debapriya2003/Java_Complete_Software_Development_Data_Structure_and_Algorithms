package Graphs.Learning;

/**
 * =========================================================
 * GRAPH AND ITS TYPES — THEORY + SAMPLE CODE
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS A GRAPH?
 * ---------------------------------------------------------
 * A Graph is a NON-LINEAR data structure used to represent
 * relationships between entities.
 *
 * A graph is defined as:
 *      G = (V, E)
 *
 * Where:
 *  - V → Set of vertices (nodes)
 *  - E → Set of edges (connections)
 *
 * ---------------------------------------------------------
 * 2. WHY GRAPHS ARE IMPORTANT
 * ---------------------------------------------------------
 * Graphs model real-world problems such as:
 *  - Social networks (users & friendships)
 *  - Road maps (cities & roads)
 *  - Computer networks
 *  - Web page links
 *  - Task dependencies
 *
 * ---------------------------------------------------------
 * 3. BASIC TERMINOLOGIES
 * ---------------------------------------------------------
 * Vertex (Node)  → An entity in the graph
 * Edge           → Connection between two vertices
 * Degree         → Number of edges connected to a vertex
 * Path           → Sequence of connected vertices
 * Cycle          → Path that starts and ends at same vertex
 * Connected      → All vertices are reachable
 * Disconnected   → Some vertices are unreachable
 *
 * ---------------------------------------------------------
 * 4. TYPES OF GRAPHS
 * ---------------------------------------------------------
 *
 * 4.1 BASED ON DIRECTION
 * ----------------------------------
 * 1️⃣ Undirected Graph
 *    - Edges have no direction
 *    - A -- B means both can reach each other
 *
 * 2️⃣ Directed Graph (Digraph)
 *    - Edges have direction
 *    - A → B means one-way connection
 *
 * ---------------------------------------------------------
 * 4.2 BASED ON WEIGHT
 * ----------------------------------
 * 3️⃣ Weighted Graph
 *    - Each edge has a weight or cost
 *
 * 4️⃣ Unweighted Graph
 *    - All edges have equal weight (assumed as 1)
 *
 * ---------------------------------------------------------
 * 4.3 BASED ON CYCLES
 * ----------------------------------
 * 5️⃣ Cyclic Graph
 *    - Contains at least one cycle
 *
 * 6️⃣ Acyclic Graph
 *    - Does NOT contain cycles
 *
 * 7️⃣ Directed Acyclic Graph (DAG)
 *    - Directed graph with no cycles
 *    - Used in scheduling and topological sorting
 *
 * ---------------------------------------------------------
 * 4.4 SPECIAL GRAPHS
 * ----------------------------------
 * 8️⃣ Complete Graph
 *    - Every vertex is connected to every other vertex
 *    - Number of edges = n(n-1)/2
 *
 * 9️⃣ Null Graph
 *    - Vertices present but no edges
 *
 * 🔟 Connected Graph
 *    - Path exists between every pair of vertices
 *
 * 1️⃣1️⃣ Disconnected Graph
 *    - Some vertices are not reachable
 *
 * ---------------------------------------------------------
 * 4.5 TREE-RELATED GRAPHS
 * ----------------------------------
 * 1️⃣2️⃣ Tree
 *    - Connected graph with no cycles
 *    - Has exactly (n - 1) edges
 *
 * 1️⃣3️⃣ Forest
 *    - Collection of disjoint trees
 *
 * ---------------------------------------------------------
 * 4.6 BASED ON EDGE MULTIPLICITY
 * ----------------------------------
 * 1️⃣4️⃣ Simple Graph
 *    - No self-loops
 *    - No multiple edges
 *
 * 1️⃣5️⃣ Multigraph
 *    - Multiple edges allowed
 *
 * 1️⃣6️⃣ Pseudograph
 *    - Self-loops allowed
 *
 * ---------------------------------------------------------
 * 5. GRAPH REPRESENTATION METHODS
 * ---------------------------------------------------------
 *
 * 1️⃣ Adjacency Matrix
 *    - 2D matrix of size V x V
 *    - matrix[i][j] = 1 if edge exists
 *    - Fast lookup, high space usage
 *
 * 2️⃣ Adjacency List (MOST USED)
 *    - Each vertex stores list of neighbors
 *    - Space efficient
 *
 * ---------------------------------------------------------
 * 6. GRAPH TRAVERSALS
 * ---------------------------------------------------------
 * BFS (Breadth First Search)
 *  - Level-wise traversal
 *
 * DFS (Depth First Search)
 *  - Depth-wise traversal
 *
 * ---------------------------------------------------------
 * 7. KEY INTERVIEW TAKEAWAYS
 * ---------------------------------------------------------
 * ✔ Graphs model relationships
 * ✔ DAG is extremely important
 * ✔ Trees are special graphs
 * ✔ Adjacency List is preferred
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY
 * ---------------------------------------------------------
 * A graph is a collection of vertices connected by edges
 * used to represent relationships.
 *
 * =========================================================
 * BELOW IS A SIMPLE GRAPH IMPLEMENTATION (ADJ LIST)
 * =========================================================
 */

import java.util.*;

public class GraphTheoryExample {

    // Number of vertices
    private int V;

    // Adjacency List representation
    private List<List<Integer>> adjList;

    /**
     * Constructor to initialize graph
     */
    public GraphTheoryExample(int vertices) {
        this.V = vertices;
        adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Adds an edge to the graph
     * (Undirected Graph Example)
     */
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    /**
     * Breadth First Search (BFS)
     */
    public void bfs(int start) {

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search (DFS)
     */
    public void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("DFS Traversal: ");
        dfsUtil(start, visited);
        System.out.println();
    }

    private void dfsUtil(int node, boolean[] visited) {

        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Graph Theory Example ===\n");

        // Create graph with 5 vertices
        GraphTheoryExample graph = new GraphTheoryExample(5);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);

        // Perform traversals
        graph.bfs(0);
        graph.dfs(0);
    }
}
