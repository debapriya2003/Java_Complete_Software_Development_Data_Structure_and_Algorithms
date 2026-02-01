package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    /*
    =====================================================================================
    PROBLEM: WORD SEARCH
    -------------------------------------------------------------------------------------
    Given a 2D board and a word, find if the word exists in the grid. The word can be
    constructed from letters of sequentially adjacent cells, where "adjacent" means
    horizontally or vertically neighboring. The same letter cell may not be used more
    than once in a word.
    
    Example:
    board = [["A","B","C","E"],
             ["S","F","C","S"],
             ["A","D","E","E"]]
    word = "ABCCED"
    Output: true
    
    Approach:
    1. For each cell in board, try starting word search
    2. Use DFS with backtracking
    3. Mark visited cells to avoid reuse
    4. Explore all 4 directions (up, down, left, right)
    5. Backtrack when path doesn't match word
    
    Time Complexity: O(M*N*4^L) where M,N are board dimensions, L is word length
    Space Complexity: O(L) - recursion depth
    =====================================================================================
    */
    
    /**
     * Check if word exists in board.
     * 
     * @param board 2D character board
     * @param word word to search
     * @return true if word exists
     */
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * DFS helper to search word.
     * 
     * Algorithm:
     * 1. If index reaches end of word, found complete word
     * 2. Check bounds and if current cell matches word character
     * 3. Mark cell as visited
     * 4. Explore all 4 directions
     * 5. Backtrack by unmarking cell
     * 
     * @param board game board
     * @param word word to search
     * @param index current index in word
     * @param row current row
     * @param col current column
     * @param visited visited cells
     * @return true if word found from this position
     */
    private static boolean dfs(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
        // Base case: found entire word
        if (index == word.length()) {
            return true;
        }
        
        // Out of bounds
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        
        // Already visited or character doesn't match
        if (visited[row][col] || board[row][col] != word.charAt(index)) {
            return false;
        }
        
        // Mark as visited
        visited[row][col] = true;
        
        // Explore 4 directions: up, down, left, right
        boolean found = dfs(board, word, index + 1, row - 1, col, visited) ||  // up
                        dfs(board, word, index + 1, row + 1, col, visited) ||  // down
                        dfs(board, word, index + 1, row, col - 1, visited) ||  // left
                        dfs(board, word, index + 1, row, col + 1, visited);    // right
        
        // Backtrack
        visited[row][col] = false;
        
        return found;
    }
    
    /**
     * Find all words from list that exist in board.
     * 
     * @param board game board
     * @param words list of words
     * @return list of words found
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (exist(board, word)) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    /**
     * Print board for visualization.
     */
    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Word exists
        System.out.println("=== Test Case 1: Word Exists ===");
        char[][] board1 = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        System.out.println("Board:");
        printBoard(board1);
        System.out.println("Word: " + word1);
        System.out.println("Exists: " + exist(board1, word1));
        System.out.println();
        
        // Test Case 2: Word does not exist
        System.out.println("=== Test Case 2: Word Does Not Exist ===");
        char[][] board2 = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        String word2 = "SEE";
        System.out.println("Board: (same as above)");
        System.out.println("Word: " + word2);
        System.out.println("Exists: " + exist(board2, word2));
        System.out.println();
        
        // Test Case 3: Single character
        System.out.println("=== Test Case 3: Single Character ===");
        char[][] board3 = {{'A'}};
        String word3 = "A";
        System.out.println("Board: " + board3[0][0]);
        System.out.println("Word: " + word3);
        System.out.println("Exists: " + exist(board3, word3));
        System.out.println();
        
        // Test Case 4: Backtracking needed
        System.out.println("=== Test Case 4: Backtracking ===");
        char[][] board4 = {{'A', 'B'},
                          {'C', 'D'}};
        String word4 = "ACDB";
        System.out.println("Board:");
        printBoard(board4);
        System.out.println("Word: " + word4);
        System.out.println("Exists: " + exist(board4, word4));
        System.out.println();
        
        // Test Case 5: Multiple words
        System.out.println("=== Test Case 5: Multiple Words ===");
        char[][] board5 = {{'O', 'A', 'A', 'N'},
                          {'E', 'T', 'A', 'E'},
                          {'I', 'H', 'K', 'R'},
                          {'I', 'F', 'L', 'V'}};
        String[] words = {"OATH", "PATS", "KATE", "OAT"};
        System.out.println("Words found: " + findWords(board5, words));
    }
}
