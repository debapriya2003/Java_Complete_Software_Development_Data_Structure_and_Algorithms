package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class RatInMaze {

    /*
    =====================================================================================
    PROBLEM: RAT IN A MAZE
    -------------------------------------------------------------------------------------
    A rat is placed at (0,0) of an m×n matrix. The rat wants to reach (m-1,n-1).
    The rat can move in 4 directions: UP, DOWN, LEFT, RIGHT.
    Some cells are blocked (0), and some cells are open (1).
    
    Find all valid paths from source to destination.
    
    Example:
    Matrix:
    [1, 1, 1]
    [1, 0, 1]
    [1, 1, 1]
    
    Output: ["DDRR", "RRDD"]
    (D = Down, R = Right)
    
    Approach:
    1. Use backtracking to explore all possible paths
    2. Mark cells as visited before recursion
    3. Unmark after backtracking to explore other paths
    4. Move in all 4 directions if valid
    5. Base case: reached destination
    
    Time Complexity: O(4^(m*n)) - worst case all paths
    Space Complexity: O(m*n) - visited matrix + recursion depth
    =====================================================================================
    */
    
    /**
     * Find all paths from (0,0) to (m-1,n-1) in a maze.
     * 
     * @param matrix maze (1 = open, 0 = blocked)
     * @return list of all valid paths
     */
    public static List<String> findAllPaths(int[][] matrix) {
        List<String> result = new ArrayList<>();
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        if (matrix[0][0] == 0 || matrix[m - 1][n - 1] == 0) {
            return result;
        }
        
        boolean[][] visited = new boolean[m][n];
        dfs(matrix, 0, 0, "", visited, result);
        return result;
    }
    
    /**
     * DFS with backtracking to find all valid paths.
     * 
     * Directions: UP, DOWN, LEFT, RIGHT (checked in this order for consistent path ordering)
     * 
     * @param matrix maze
     * @param row current row
     * @param col current column
     * @param path current path string
     * @param visited visited cells
     * @param result all found paths
     */
    private static void dfs(int[][] matrix, int row, int col, String path, 
                            boolean[][] visited, List<String> result) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Base case: reached destination
        if (row == m - 1 && col == n - 1) {
            result.add(path);
            return;
        }
        
        visited[row][col] = true;
        
        // UP
        if (isValid(matrix, row - 1, col, visited)) {
            dfs(matrix, row - 1, col, path + "U", visited, result);
        }
        
        // DOWN
        if (isValid(matrix, row + 1, col, visited)) {
            dfs(matrix, row + 1, col, path + "D", visited, result);
        }
        
        // LEFT
        if (isValid(matrix, row, col - 1, visited)) {
            dfs(matrix, row, col - 1, path + "L", visited, result);
        }
        
        // RIGHT
        if (isValid(matrix, row, col + 1, visited)) {
            dfs(matrix, row, col + 1, path + "R", visited, result);
        }
        
        // Backtrack
        visited[row][col] = false;
    }
    
    /**
     * Check if a position is valid to move to.
     * Valid means: in bounds, open cell (1), not visited
     * 
     * @param matrix maze
     * @param row target row
     * @param col target column
     * @param visited visited tracking
     * @return true if valid to visit
     */
    private static boolean isValid(int[][] matrix, int row, int col, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        return row >= 0 && row < m && col >= 0 && col < n && 
               matrix[row][col] == 1 && !visited[row][col];
    }
    
    /**
     * Count all valid paths (without returning them).
     * 
     * @param matrix maze
     * @return number of valid paths
     */
    public static int countPaths(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        if (matrix[0][0] == 0 || matrix[m - 1][n - 1] == 0) {
            return 0;
        }
        
        boolean[][] visited = new boolean[m][n];
        return countPathsDFS(matrix, 0, 0, visited);
    }
    
    private static int countPathsDFS(int[][] matrix, int row, int col, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        
        visited[row][col] = true;
        
        int count = 0;
        
        // Try all 4 directions
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (isValid(matrix, newRow, newCol, visited)) {
                count += countPathsDFS(matrix, newRow, newCol, visited);
            }
        }
        
        visited[row][col] = false;
        return count;
    }
    
    /**
     * Print matrix for visualization.
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple 3x3 maze
        System.out.println("=== Test Case 1: 3x3 Maze ===");
        int[][] maze1 = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        printMatrix(maze1);
        System.out.println("Paths: " + findAllPaths(maze1));
        System.out.println("Path count: " + countPaths(maze1));
        System.out.println();
        
        // Test Case 2: 2x2 maze
        System.out.println("=== Test Case 2: 2x2 Maze ===");
        int[][] maze2 = {
            {1, 1},
            {1, 1}
        };
        printMatrix(maze2);
        System.out.println("Paths: " + findAllPaths(maze2));
        System.out.println();
        
        // Test Case 3: Maze with blocked path
        System.out.println("=== Test Case 3: Blocked Maze (No Path) ===");
        int[][] maze3 = {
            {1, 0},
            {0, 1}
        };
        printMatrix(maze3);
        System.out.println("Paths: " + findAllPaths(maze3));
        System.out.println("Path count: " + countPaths(maze3));
        System.out.println();
        
        // Test Case 4: 4x4 maze
        System.out.println("=== Test Case 4: 4x4 Maze ===");
        int[][] maze4 = {
            {1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}
        };
        printMatrix(maze4);
        System.out.println("Path count: " + countPaths(maze4));
        System.out.println();
        
        // Test Case 5: Single cell
        System.out.println("=== Test Case 5: Single Cell (1x1) ===");
        int[][] maze5 = {{1}};
        System.out.println("Paths: " + findAllPaths(maze5));
    }
}
