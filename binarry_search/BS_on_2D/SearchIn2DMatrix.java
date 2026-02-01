package binarry_search.BS_on_2D;

public class SearchIn2DMatrix {

    /*
    =====================================================================================
    PROBLEM: SEARCH IN A 2D MATRIX
    -------------------------------------------------------------------------------------
    Given an m x n matrix where each row is sorted from left to right and the first 
    integer of each row is greater than the last integer of the previous row, search 
    for a given target value in the matrix. The matrix can be viewed as a single sorted 
    array. Use binary search by treating the 2D matrix as a 1D sorted array where the 
    index can be converted to 2D coordinates. This provides O(log(m*n)) time complexity 
    with efficient search operations.

    Time Complexity: O(log(m * n))
    Space Complexity: O(1)

    Example:
    Matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true (found at position [0,1])
    =====================================================================================
    */
    
    /**
     * Searches for a target value in a 2D sorted matrix using binary search.
     * Treats the matrix as a 1D sorted array by converting indices. Uses binary search
     * on the virtual 1D array treating rows and columns as a continuous sequence.
     * Converts 1D index to 2D coordinates using division and modulo operations.
     * 
     * @param matrix m x n sorted 2D matrix
     * @param target value to search
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = matrix[mid / n][mid % n];
            
            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    
    /**
     * Alternative approach: Two binary searches.
     * First searches for the row containing the target, then searches within that row.
     * More intuitive for understanding but slightly less efficient than single pass.
     * 
     * @param matrix m x n sorted 2D matrix
     * @param target value to search
     * @return true if target is found, false otherwise
     */
    public static boolean searchMatrixAlternative(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Find the row containing target
        int top = 0, bottom = m - 1;
        int targetRow = -1;
        
        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;
            if (matrix[mid][0] <= target && target <= matrix[mid][n - 1]) {
                targetRow = mid;
                break;
            } else if (matrix[mid][0] > target) {
                bottom = mid - 1;
            } else {
                top = mid + 1;
            }
        }
        
        if (targetRow == -1) return false;
        
        // Search in the target row
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (matrix[targetRow][mid] == target) {
                return true;
            } else if (matrix[targetRow][mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] matrix1 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        System.out.println("Search 3: " + searchMatrix(matrix1, 3));   // true
        System.out.println("Search 13: " + searchMatrix(matrix1, 13)); // false
        
        int[][] matrix2 = {{1}};
        System.out.println("Search 1: " + searchMatrix(matrix2, 1));   // true
        System.out.println("Search 0: " + searchMatrix(matrix2, 0));   // false
        
        int[][] matrix3 = {{1, 3}};
        System.out.println("Search 3: " + searchMatrix(matrix3, 3));   // true
    }
}
