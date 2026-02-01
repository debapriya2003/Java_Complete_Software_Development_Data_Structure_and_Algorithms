package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class MColoringGraph {

    /*
    =====================================================================================
    PROBLEM: M COLORING PROBLEM
    -------------------------------------------------------------------------------------
    Given a graph represented as an adjacency matrix and M colors, determine if the
    graph can be colored using at most M colors such that no adjacent vertices have
    the same color.
    
    This is an NP-complete problem. The graph coloring problem has applications in:
    - Register allocation in compilers
    - Scheduling problems
    - Map coloring
    
    Example:
    Graph with 4 vertices and edges: 0-1, 0-2, 1-2, 1-3
    M = 3
    Output: true (can be colored with 3 colors)
    
    Approach: Backtracking
    1. Try to color each vertex with colors 0 to M-1
    2. For each color, check if it's safe (no adjacent vertex has same color)
    3. If safe, assign color and move to next vertex
    4. If we successfully color all vertices, return true
    5. Otherwise, backtrack and try next color
    
    Time Complexity: O(M^V) - worst case all colors for all vertices
    Space Complexity: O(V) - color array + recursion depth
    =====================================================================================
    */
    
    /**
     * Check if graph can be colored with M colors using backtracking.
     * 
     * @param graph adjacency matrix (1 = edge, 0 = no edge)
     * @param m number of available colors
     * @return true if graph can be colored
     */
    public static boolean canColorGraph(int[][] graph, int m) {
        int n = graph.length;
        int[] color = new int[n];
        
        // Initialize all vertices with no color (-1)
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }
        
        return colorGraph(graph, m, 0, color);
    }
    
    /**
     * Backtracking helper to color the graph.
     * 
     * Algorithm:
     * 1. Base case: all vertices colored (vertex == n)
     * 2. For current vertex, try each color 0 to m-1
     * 3. Check if color is safe (no adjacent vertex has same color)
     * 4. If safe, assign and recurse to next vertex
     * 5. If recursion succeeds, return true
     * 6. Otherwise, backtrack by resetting color
     * 
     * @param graph adjacency matrix
     * @param m number of colors
     * @param vertex current vertex to color
     * @param color color array
     * @return true if graph can be colored
     */
    private static boolean colorGraph(int[][] graph, int m, int vertex, int[] color) {
        int n = graph.length;
        
        // Base case: all vertices colored
        if (vertex == n) {
            return true;
        }
        
        // Try each color for current vertex
        for (int c = 0; c < m; c++) {
            if (isSafe(graph, vertex, c, color)) {
                // Assign color
                color[vertex] = c;
                
                // Recurse to next vertex
                if (colorGraph(graph, m, vertex + 1, color)) {
                    return true;
                }
                
                // Backtrack
                color[vertex] = -1;
            }
        }
        
        return false;
    }
    
    /**
     * Check if a color is safe for a vertex (no adjacent vertex has this color).
     * 
     * @param graph adjacency matrix
     * @param vertex vertex to check
     * @param color color to assign
     * @param colorArray current color assignments
     * @return true if safe to assign color
     */
    private static boolean isSafe(int[][] graph, int vertex, int color, int[] colorArray) {
        for (int i = 0; i < graph.length; i++) {
            // If there's an edge and adjacent vertex has same color
            if (graph[vertex][i] == 1 && colorArray[i] == color) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Find all possible colorings of the graph with M colors.
     * 
     * @param graph adjacency matrix
     * @param m number of colors
     * @return list of all valid colorings
     */
    public static List<int[]> findAllColorings(int[][] graph, int m) {
        List<int[]> result = new ArrayList<>();
        int n = graph.length;
        int[] color = new int[n];
        
        for (int i = 0; i < n; i++) {
            color[i] = -1;
        }
        
        findAllColoringsHelper(graph, m, 0, color, result);
        return result;
    }
    
    private static void findAllColoringsHelper(int[][] graph, int m, int vertex, 
                                               int[] color, List<int[]> result) {
        int n = graph.length;
        
        if (vertex == n) {
            result.add(color.clone());
            return;
        }
        
        for (int c = 0; c < m; c++) {
            if (isSafe(graph, vertex, c, color)) {
                color[vertex] = c;
                findAllColoringsHelper(graph, m, vertex + 1, color, result);
                color[vertex] = -1;
            }
        }
    }
    
    /**
     * Get chromatic number - minimum colors needed to color the graph.
     * 
     * @param graph adjacency matrix
     * @return chromatic number
     */
    public static int getChromaticNumber(int[][] graph) {
        int n = graph.length;
        
        for (int m = 1; m <= n; m++) {
            if (canColorGraph(graph, m)) {
                return m;
            }
        }
        
        return n;
    }
    
    /**
     * Print coloring for visualization.
     */
    private static void printColoring(int[] coloring) {
        System.out.print("Coloring: [");
        for (int i = 0; i < coloring.length; i++) {
            System.out.print(coloring[i]);
            if (i < coloring.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    /**
     * Print adjacency matrix.
     */
    private static void printGraph(int[][] graph) {
        int n = graph.length;
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple graph - 3 vertices, needs 3 colors
        System.out.println("=== Test Case 1: Triangle Graph ===");
        int[][] graph1 = {
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 0}
        };
        printGraph(graph1);
        System.out.println("Can color with 2 colors: " + canColorGraph(graph1, 2));
        System.out.println("Can color with 3 colors: " + canColorGraph(graph1, 3));
        System.out.println("Chromatic number: " + getChromaticNumber(graph1));
        System.out.println();
        
        // Test Case 2: Bipartite graph - needs 2 colors
        System.out.println("=== Test Case 2: Bipartite Graph ===");
        int[][] graph2 = {
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0}
        };
        printGraph(graph2);
        System.out.println("Can color with 2 colors: " + canColorGraph(graph2, 2));
        System.out.println("Chromatic number: " + getChromaticNumber(graph2));
        System.out.println();
        
        // Test Case 3: Complex graph
        System.out.println("=== Test Case 3: Complex Graph ===");
        int[][] graph3 = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };
        printGraph(graph3);
        System.out.println("Can color with 3 colors: " + canColorGraph(graph3, 3));
        System.out.println("Chromatic number: " + getChromaticNumber(graph3));
        System.out.println("All colorings with 3 colors:");
        List<int[]> colorings = findAllColorings(graph3, 3);
        for (int[] coloring : colorings) {
            printColoring(coloring);
        }
        System.out.println();
        
        // Test Case 4: Complete graph K4 - needs 4 colors
        System.out.println("=== Test Case 4: Complete Graph K4 ===");
        int[][] graph4 = {
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 0}
        };
        System.out.println("Chromatic number: " + getChromaticNumber(graph4));
        System.out.println();
        
        // Test Case 5: Disconnected graph
        System.out.println("=== Test Case 5: Disconnected Graph ===");
        int[][] graph5 = {
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 0, 1},
            {0, 0, 1, 0}
        };
        System.out.println("Chromatic number: " + getChromaticNumber(graph5));
    }
}
