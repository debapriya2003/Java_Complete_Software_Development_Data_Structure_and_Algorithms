package binarry_search.BS_on_2D;

public class FindPeakElement2D {

    /*
    =====================================================================================
    PROBLEM: FIND A PEAK ELEMENT IN A 2D MATRIX
    -------------------------------------------------------------------------------------
    A peak element in a 2D matrix is an element that is greater than or equal to
    all of its adjacent neighbors (up, down, left, right).

    Given an m × n matrix, return the position (row, column) of ANY one peak element.

    APPROACH USED:
    • Binary Search on columns
    • For each middle column, find the maximum element
    • Compare it with left and right neighbors
    • Move towards the side having a larger neighbor

    This reduces the problem from 2D to 1D binary search on columns.

    Example:
    Matrix =
    [
      [1,  4,  3,  2],
      [5, 10,  3,  4],
      [8, 10,  5, 15]
    ]

    Output:
    Peak = 10 at position [1,1] or [2,1]
    =====================================================================================
    */

    public static void main(String[] args) {

        int[][] matrix1 = {
            {1, 4, 3, 2},
            {5, 10, 3, 4},
            {8, 10, 5, 15}
        };

        int[] peak1 = findPeakElement(matrix1);
        System.out.println("Peak at [" + peak1[0] + ", " + peak1[1] + "] = "
                + matrix1[peak1[0]][peak1[1]]);

        int[][] matrix2 = {
            {10, 20, 15},
            {21, 30, 14},
            {7, 16, 32}
        };

        int[] peak2 = findPeakElement(matrix2);
        System.out.println("Peak at [" + peak2[0] + ", " + peak2[1] + "] = "
                + matrix2[peak2[0]][peak2[1]]);

        int[][] matrix3 = {
            {1, 2},
            {3, 4}
        };

        int[] peak3 = findPeakElement(matrix3);
        System.out.println("Peak at [" + peak3[0] + ", " + peak3[1] + "] = "
                + matrix3[peak3[0]][peak3[1]]);
    }

    /*
    =====================================================================================
    FUNCTION: findPeakElement
    -------------------------------------------------------------------------------------
    Finds a peak element in a 2D matrix using Binary Search on columns.

    LOGIC:
    1. Perform binary search on columns
    2. In the middle column, find the row index of the maximum element
    3. Compare this element with its left and right neighbors
    4. If it is greater than both → peak found
    5. Otherwise, move towards the direction of the larger neighbor

    Time Complexity  : O(m × log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int[] findPeakElement(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = n - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Find row index of maximum element in mid column
            int maxRow = 0;
            for (int i = 1; i < m; i++) {
                if (matrix[i][mid] > matrix[maxRow][mid]) {
                    maxRow = i;
                }
            }

            // Get left and right neighbors safely
            int leftVal = (mid > 0) ? matrix[maxRow][mid - 1] : Integer.MIN_VALUE;
            int rightVal = (mid < n - 1) ? matrix[maxRow][mid + 1] : Integer.MIN_VALUE;

            // Check if current element is a peak
            if (matrix[maxRow][mid] >= leftVal &&
                matrix[maxRow][mid] >= rightVal) {

                return new int[]{maxRow, mid};
            }
            // Move to left half
            else if (leftVal > matrix[maxRow][mid]) {
                right = mid - 1;
            }
            // Move to right half
            else {
                left = mid + 1;
            }
        }

        return null; // Guaranteed peak exists as per problem constraints
    }
}
