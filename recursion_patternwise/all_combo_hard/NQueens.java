package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /*
    =====================================================================================
    PROBLEM: N-QUEENS
    -------------------------------------------------------------------------------------
    The N-Queens problem: Place N queens on an N×N chessboard such that no two queens
    threaten each other. A queen can attack any piece on the same row, column, or diagonal.
    Return all distinct solutions.
    
    Example:
    n = 4
    Output: [[".Q..", "...Q", "Q...", "..Q."],
             ["..Q.", "Q...", "...Q", ".Q.."]]
    
    Approach:
    1. Use backtracking to place queens row by row
    2. For each row, try placing queen in each column
    3. Check if placement is safe (no conflicts with previous queens)
    4. If queen placement is valid, move to next row
    5. Backtrack if no valid column found or after exploring all options
    
    Time Complexity: O(N!) - N choices for row 1, N-1 for row 2, etc.
    Space Complexity: O(N) - recursion depth
    =====================================================================================
    */
    
    /**
     * Solve N-Queens problem.
     * 
     * @param n board size
     * @return list of all valid solutions
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with dots
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        solve(board, 0, result);
        return result;
    }
    
    /**
     * Recursive helper to solve N-Queens using backtracking.
     * 
     * Algorithm:
     * 1. Base case: if row equals n, found valid solution
     * 2. For each column in current row:
     *    - If queen can be placed safely:
     *      - Place queen and recurse to next row
     *      - Backtrack by removing queen
     * 3. Check safety by verifying:
     *    - No queen in same column
     *    - No queen in upper-left diagonal
     *    - No queen in upper-right diagonal
     * 
     * @param board current board state
     * @param row current row to place queen
     * @param result all valid solutions
     */
    private static void solve(char[][] board, int row, List<List<String>> result) {
        int n = board.length;
        
        // Base case: all queens placed
        if (row == n) {
            result.add(boardToList(board));
            return;
        }
        
        // Try placing queen in each column of current row
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                // Place queen
                board[row][col] = 'Q';
                
                // Recurse to next row
                solve(board, row + 1, result);
                
                // Backtrack
                board[row][col] = '.';
            }
        }
    }
    
    /**
     * Check if queen can be safely placed at (row, col).
     * 
     * @param board current board
     * @param row target row
     * @param col target column
     * @return true if safe to place
     */
    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;
        
        // Check column (above current position)
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Convert board to list of strings.
     * 
     * @param board board state
     * @return list representation
     */
    private static List<String> boardToList(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] row : board) {
            list.add(new String(row));
        }
        return list;
    }
    
    /**
     * Count number of solutions without returning them.
     * 
     * @param n board size
     * @return number of solutions
     */
    public static int countNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        return countSolutions(board, 0);
    }
    
    private static int countSolutions(char[][] board, int row) {
        int n = board.length;
        
        if (row == n) {
            return 1;
        }
        
        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                count += countSolutions(board, row + 1);
                board[row][col] = '.';
            }
        }
        
        return count;
    }
    
    /**
     * Print a solution for visualization.
     */
    private static void printSolution(List<String> solution) {
        for (String row : solution) {
            System.out.println("  " + row);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: N = 4
        System.out.println("=== N = 4 ===");
        List<List<String>> result1 = solveNQueens(4);
        System.out.println("Number of solutions: " + result1.size());
        System.out.println("First solution:");
        printSolution(result1.get(0));
        System.out.println();
        
        // Test Case 2: N = 1
        System.out.println("=== N = 1 ===");
        List<List<String>> result2 = solveNQueens(1);
        System.out.println("Number of solutions: " + result2.size());
        printSolution(result2.get(0));
        System.out.println();
        
        // Test Case 3: N = 2
        System.out.println("=== N = 2 ===");
        List<List<String>> result3 = solveNQueens(2);
        System.out.println("Number of solutions: " + result3.size() + " (expected: 0)");
        System.out.println();
        
        // Test Case 4: N = 8
        System.out.println("=== N = 8 ===");
        int count8 = countNQueens(8);
        System.out.println("Number of solutions: " + count8 + " (expected: 92)");
        System.out.println();
        
        // Test Case 5: All sizes
        System.out.println("=== Solution Count for Different N ===");
        for (int n = 1; n <= 8; n++) {
            System.out.println("N = " + n + ": " + countNQueens(n) + " solutions");
        }
    }
}
