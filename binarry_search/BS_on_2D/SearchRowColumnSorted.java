package binarry_search.BS_on_2D;

public class SearchRowColumnSorted {

    /*
    =====================================================================================
    PROBLEM: SEARCH IN A ROW AND COLUMN WISE SORTED MATRIX
    -------------------------------------------------------------------------------------
    Given an m x n matrix where integers in each row are sorted from left to right and 
    integers in each column are sorted from top to bottom, search for a target value 
    in the matrix. Start from top-right corner or bottom-left corner and use comparisons 
    to eliminate either a row or column at each step. This greedy approach achieves 
    O(m + n) time complexity by eliminating one row or column with each comparison, 
    which is optimal for this problem structure.

    Time Complexity: O(m + n)
    Space Complexity: O(1)

    Example:
    Matrix = [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]], target = 5
    Output: true (found at position [1,1])
    =====================================================================================
    */
    
    /**
     * Searches for a target in a row and column wise sorted matrix.
     * Starts from the top-right corner where we have unique directional properties.
     * If target is greater, move down; if less, move left. This eliminates one row
     * or column with each comparison. Returns true if target found, false otherwise.
     * 
     * @param matrix m x n row and column sorted matrix
     * @param target value to search
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int row = 0;
        int col = matrix[0].length - 1;
        
        // Start from top-right corner
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;  // Move left
            } else {
                row++;  // Move down
            }
        }
        return false;
    }
    
    /**
     * Alternative approach starting from bottom-left corner.
     * Similar logic but starting from opposite corner. If target is less, move up;
     * if greater, move right. Both approaches have same time complexity O(m + n).
     * 
     * @param matrix m x n row and column sorted matrix
     * @param target value to search
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrixFromBottomLeft(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int row = matrix.length - 1;
        int col = 0;
        
        // Start from bottom-left corner
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;  // Move up
            } else {
                col++;  // Move right
            }
        }
        return false;
    }
    
    /**
     * Helper function to find position of target if it exists.
     * Returns the coordinates of the target as [row, col] or null if not found.
     * 
     * @param matrix m x n row and column sorted matrix
     * @param target value to search
     * @return array [row, col] if found, null otherwise
     */
    public static int[] findPosition(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return null;
        
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return new int[]{row, col};
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        int[][] matrix1 = {{1, 4, 7, 11}, {2, 5, 8, 12}, {3, 6, 9, 16}, {10, 13, 14, 17}};
        System.out.println("Search 5: " + searchMatrix(matrix1, 5));     // true
        System.out.println("Search 13: " + searchMatrix(matrix1, 13));   // true
        System.out.println("Search 15: " + searchMatrix(matrix1, 15));   // false
        
        int[][] matrix2 = {{-10, -8, 0, 2}, {-9, -6, 1, 4}, {-7, -5, 3, 5}};
        System.out.println("Search -7: " + searchMatrix(matrix2, -7));   // true
        System.out.println("Search 6: " + searchMatrix(matrix2, 6));     // false
        
        int[] pos = findPosition(matrix1, 5);
        if (pos != null) {
            System.out.println("Position of 5: [" + pos[0] + ", " + pos[1] + "]"); // [1, 1]
        }
    }
}
