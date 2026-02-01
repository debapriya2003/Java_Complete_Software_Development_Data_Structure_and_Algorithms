package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * SHORTEST PATH IN A BINARY MAZE (BFS)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given a 2D grid (binary maze) where:
 *  - 0 → open cell (can move)
 *  - 1 → wall (blocked)
 *
 * You are also given:
 *  - start cell (source)
 *  - end cell (destination)
 *
 * You can move ONLY in 4 directions:
 *  - Up
 *  - Down
 *  - Left
 *  - Right
 *
 * Task:
 * Find the SHORTEST number of steps from start to end.
 * Return -1 if no path exists.
 *
 * ---------------------------------------------------------
 * 2. WHY BFS?
 * ---------------------------------------------------------
 * - Each move has equal cost (1 step)
 * - Graph is UNWEIGHTED
 * - BFS guarantees SHORTEST PATH in unweighted graphs
 *
 * Hence:
 * ✔ BFS is the OPTIMAL choice
 *
 * ---------------------------------------------------------
 * 3. GRAPH INTERPRETATION
 * ---------------------------------------------------------
 * - Each cell → a node
 * - Each valid move → an edge
 * - Total nodes = n × m
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA / LOGIC
 * ---------------------------------------------------------
 * Use BFS starting from the source cell.
 *
 * Maintain:
 * - dist[][] → distance from source to each cell
 * - Queue → for level-order traversal
 *
 * Steps:
 * 1. Initialize dist[][] with -1 (unvisited)
 * 2. Push source into queue, dist[source] = 0
 * 3. While queue not empty:
 *      - Pop current cell
 *      - If it is the destination → return distance
 *      - Explore all 4 valid neighbors
 *      - Mark distance and push into queue
 *
 * If destination is never reached → return -1
 *
 * ---------------------------------------------------------
 * 5. DIRECTION HANDLING
 * ---------------------------------------------------------
 * Movement directions:
 *  (1, 0)  → Down
 * (-1, 0)  → Up
 *  (0, 1)  → Right
 *  (0,-1)  → Left
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n × m)
 * Space Complexity : O(n × m)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * BFS gives the shortest path in a binary maze because all
 * moves have equal weight.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class BinaryMazeShortestPath {

    /**
     * Finds shortest path from start to end in binary maze
     *
     * @param grid  binary grid (0 = open, 1 = wall)
     * @param start start cell [row, col]
     * @param end   end cell [row, col]
     * @return shortest distance or -1 if unreachable
     */
    public int shortestPath(int[][] grid, int[] start, int[] end) {

        int n = grid.length;
        int m = grid[0].length;

        /**
         * Distance matrix:
         * dist[r][c] = shortest distance from start to (r, c)
         * Initialized to -1 → unvisited
         */
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        /**
         * Direction vectors for movement
         */
        int[][] directions = {
                {1, 0},   // down
                {-1, 0},  // up
                {0, 1},   // right
                {0, -1}   // left
        };

        /**
         * BFS Queue
         * Each element stores {row, col}
         */
        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from source
        queue.add(new int[]{start[0], start[1]});
        dist[start[0]][start[1]] = 0;

        /**
         * BFS traversal
         */
        while (!queue.isEmpty()) {

            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // If destination reached, return distance
            if (r == end[0] && c == end[1]) {
                return dist[r][c];
            }

            // Explore all 4 directions
            for (int[] d : directions) {

                int nr = r + d[0];
                int nc = c + d[1];

                // Check boundaries and validity
                if (nr >= 0 && nc >= 0 && nr < n && nc < m
                        && grid[nr][nc] == 0           // open cell
                        && dist[nr][nc] == -1) {       // unvisited

                    dist[nr][nc] = dist[r][c] + 1;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        // Destination not reachable
        return -1;
    }

    public static void main(String[] args) {

        System.out.println("=== Shortest Path in Binary Maze (BFS) ===\n");

        /*
         * Grid:
         * 0 1 0
         * 0 0 0
         * 1 0 0
         *
         * Start = (0,0)
         * End   = (2,2)
         *
         * Shortest Path Length = 4
         */

        BinaryMazeShortestPath solver = new BinaryMazeShortestPath();

        int[][] grid = {
                {0, 1, 0},
                {0, 0, 0},
                {1, 0, 0}
        };

        int result = solver.shortestPath(
                grid,
                new int[]{0, 0},
                new int[]{2, 2}
        );

        System.out.println("Shortest Path Length: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ BFS guarantees shortest path in unweighted grids");
        System.out.println("✔ Use dist[][] instead of visited[] to track steps");
        System.out.println("✔ Grid problems = Graph problems in disguise");
    }
}
