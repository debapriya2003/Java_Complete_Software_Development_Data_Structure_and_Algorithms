package binarry_search.BS_on_2D;

public class MatrixMedian {

    /*
    =====================================================================================
    PROBLEM: MATRIX MEDIAN
    -------------------------------------------------------------------------------------
    Given an m x n matrix where each row is sorted in non-decreasing order, find the 
    median of the matrix. The median is defined as the middle value when all elements 
    are considered as a single sorted sequence. Use binary search on the range of values 
    from minimum to maximum element. For each candidate median, count elements less than 
    or equal to it across all rows. The actual median is the smallest value where count 
    is >= total elements / 2.

    Time Complexity: O(m * n * log(max - min))
    Space Complexity: O(1)

    Example:
    Matrix = [[1,3,5],[2,6,9],[3,6,9]]
    Output: 5 (when sorted: [1,2,3,3,5,6,6,9,9], median is at index 4)
    =====================================================================================
    */
    
    /**
     * Finds the median of a matrix where all rows are sorted.
     * Binary searches on the value range from minimum to maximum. For each candidate
     * value, counts elements less than or equal to it. The median is the smallest value
     * where count >= (m*n+1)/2, ensuring we get the true median value for the matrix.
     * 
     * @param matrix m x n matrix with sorted rows
     * @return median value of the matrix
     */
    public static int findMedian(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return -1;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int min = matrix[0][0];
        int max = matrix[m - 1][n - 1];
        
        int medianPosition = (m * n + 1) / 2;
        
        int left = min, right = max;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countElementsLessOrEqual(matrix, mid);
            
            if (count < medianPosition) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    /**
     * Helper function to count elements less than or equal to target in the matrix.
     * For each row, uses binary search to find the position of the largest element
     * <= target, then counts all elements up to that position. Sums counts from all rows.
     * 
     * @param matrix m x n sorted row matrix
     * @param target value to count up to
     * @return total count of elements <= target in matrix
     */
    private static int countElementsLessOrEqual(int[][] matrix, int target) {
        int count = 0;
        
        for (int[] row : matrix) {
            // Binary search to find rightmost position of element <= target
            int pos = binarySearchRight(row, target);
            count += pos + 1;
        }
        return count;
    }
    
    /**
     * Binary search to find the rightmost position of element <= target in a sorted array.
     * Returns the index of the largest element that is <= target. Returns -1 if no such
     * element exists. This allows counting all elements <= target in the row efficiently.
     * 
     * @param row sorted array
     * @param target value to find
     * @return rightmost index of element <= target, or -1 if none exists
     */
    private static int binarySearchRight(int[] row, int target) {
        int left = 0, right = row.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (row[mid] <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    /**
     * Alternative approach using count method to find exact median.
     * Similar logic but includes additional validation. Searches for the smallest value
     * where count >= median position and count of smaller values < median position.
     * 
     * @param matrix m x n sorted row matrix
     * @return median value of the matrix
     */
    public static int findMedianAlternative(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return -1;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int totalElements = m * n;
        int medianPos = (totalElements + 1) / 2;
        
        int left = 1, right = Integer.MAX_VALUE;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countSmaller(matrix, mid);
            
            if (count < medianPos) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    /**
     * Helper for alternative approach: count strictly smaller than target.
     * Uses binary search on each row to count elements strictly less than target.
     * 
     * @param matrix 2D matrix
     * @param target value
     * @return count of elements < target
     */
    private static int countSmaller(int[][] matrix, int target) {
        int count = 0;
        for (int[] row : matrix) {
            count += binarySearchStrictLeft(row, target);
        }
        return count;
    }
    
    /**
     * Binary search to find count of elements strictly less than target.
     * Returns count of elements < target in the sorted row.
     * 
     * @param row sorted array
     * @param target value
     * @return count of elements < target
     */
    private static int binarySearchStrictLeft(int[] row, int target) {
        int left = 0, right = row.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        int[][] matrix1 = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        System.out.println("Matrix median: " + findMedian(matrix1)); // 5
        
        int[][] matrix2 = {{1, 2}, {1, 1}};
        System.out.println("Matrix median: " + findMedian(matrix2)); // 1
        
        int[][] matrix3 = {{1, 3}, {2, 4}};
        System.out.println("Matrix median: " + findMedian(matrix3)); // 2 or 3
        
        int[][] matrix4 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println("Matrix median: " + findMedian(matrix4)); // 1
    }
}
