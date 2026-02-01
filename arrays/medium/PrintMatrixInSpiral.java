package arrays.medium;

public class PrintMatrixInSpiral {

    /*
    =====================================================================================
    PROBLEM: PRINT MATRIX IN SPIRAL MANNER
    -------------------------------------------------------------------------------------
    Given a 2D matrix, the task is to print its elements in spiral order. Spiral order
    means traversing the matrix starting from the top-left corner, moving right across
    the top row, then down the last column, then left across the bottom row, and finally
    up the first column. This process is repeated by shrinking the boundaries inward
    until all elements are printed.

    This problem tests understanding of matrix traversal, boundary management, and loop
    control.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[][] matrix = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9, 10, 11, 12}
        };

        System.out.print("Spiral order: ");
        printSpiral(matrix);
    }

    /*
    =====================================================================================
    FUNCTION: printSpiral
    -------------------------------------------------------------------------------------
    This function prints the elements of a matrix in spiral order using boundary pointers.

    LOGIC:
    • Maintain four boundaries: top, bottom, left, right
    • Traverse from left → right across the top row
    • Traverse from top → bottom down the right column
    • Traverse from right → left across the bottom row (if valid)
    • Traverse from bottom → top up the left column (if valid)
    • Shrink the boundaries after completing one spiral layer

    The checks prevent double-printing when rows or columns overlap.

    Time Complexity  : O(r × c)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void printSpiral(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;

        while (top <= bottom && left <= right) {

            // Traverse top row (left to right)
            for (int j = left; j <= right; j++) {
                System.out.print(matrix[top][j] + " ");
            }
            top++;

            // Traverse right column (top to bottom)
            for (int i = top; i <= bottom; i++) {
                System.out.print(matrix[i][right] + " ");
            }
            right--;

            // Traverse bottom row (right to left)
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    System.out.print(matrix[bottom][j] + " ");
                }
                bottom--;
            }

            // Traverse left column (bottom to top)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(matrix[i][left] + " ");
                }
                left++;
            }
        }
    }
}

