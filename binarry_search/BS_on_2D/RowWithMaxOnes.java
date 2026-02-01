package binarry_search.BS_on_2D;

public class RowWithMaxOnes {

    /*
    =====================================================================================
    PROBLEM: FIND THE ROW WITH MAXIMUM NUMBER OF 1'S
    -------------------------------------------------------------------------------------
    Given a 2D matrix where each row is sorted in non-decreasing order containing only 
    0s and 1s, find the row index with the maximum number of 1's in the matrix. If 
    multiple rows have the same maximum count of 1's, return the smallest row index. 
    Use binary search on each row to find the first occurrence of 1, then count the 
    total 1's in that row. This approach is more efficient than linear scanning.

    Time Complexity: O(m * log n) where m is rows and n is columns
    Space Complexity: O(1)

    Example:
    Matrix = [[0,0,0,1], [0,1,1,1], [1,1,1,1], [0,0,1,1]]
    Output: 2 (row 2 has 4 ones, maximum in the matrix)
    =====================================================================================
    */
    
    /**
     * Finds the row index with maximum number of 1's in a binary matrix.
     * Iterates through each row and uses binary search to find the first occurrence
     * of 1 in that row. Calculates total 1's as columns - first occurrence index.
     * Tracks the row with maximum 1's throughout iteration and returns its index.
     * 
     * @param matrix 2D array of 0s and 1s, sorted per row
     * @return row index with maximum number of 1's
     */
    public static int rowWithMax1s(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return -1;
        
        int maxRow = 0, maxCount = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            int firstOne = findFirstOne(matrix[i]);
            if (firstOne != -1) {
                int count = matrix[i].length - firstOne;
                if (count > maxCount) {
                    maxCount = count;
                    maxRow = i;
                }
            }
        }
        return maxRow;
    }
    
    /**
     * Helper function to find the first occurrence of 1 in a sorted row.
     * Uses binary search to locate the leftmost 1 in the array. Returns -1
     * if the row contains only 0s, otherwise returns the index of first 1.
     * 
     * @param row sorted array containing 0s and 1s
     * @return index of first 1, or -1 if not found
     */
    private static int findFirstOne(int[] row) {
        int left = 0, right = row.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (left == row.length) ? -1 : left;
    }
    
    public static void main(String[] args) {
        int[][] matrix1 = {{0, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 1}};
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix1)); // 2
        
        int[][] matrix2 = {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix2)); // 1
        
        int[][] matrix3 = {{0, 0}, {1, 1}};
        System.out.println("Row with max 1's: " + rowWithMax1s(matrix3)); // 1
    }
}
