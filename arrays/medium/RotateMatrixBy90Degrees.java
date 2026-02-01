package arrays.medium;

public class RotateMatrixBy90Degrees {

    /*
    =====================================================================================
    PROBLEM: ROTATE A MATRIX BY 90 DEGREES (CLOCKWISE)
    -------------------------------------------------------------------------------------
    Given a square matrix, the task is to rotate the matrix by 90 degrees clockwise.
    The rotation must be done in-place, meaning no additional matrix should be used.

    A naive approach would require an extra matrix and O(n²) space. However, the optimal
    approach uses two simple steps:
    1. Transpose the matrix
    2. Reverse each row

    This transforms the matrix into its 90-degree clockwise rotated form efficiently.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[][] matrix = {
                {1,  2,  3},
                {4,  5,  6},
                {7,  8,  9}
        };

        rotate90(matrix);

        System.out.println("Matrix after 90 degree rotation:");
        printMatrix(matrix);
    }

    /*
    =====================================================================================
    FUNCTION: rotate90
    -------------------------------------------------------------------------------------
    This function rotates the given square matrix by 90 degrees clockwise.

    STEPS:
    • First transpose the matrix (convert rows into columns)
    • Then reverse each row

    WHY THIS WORKS:
    Transposition aligns elements correctly along diagonals, and reversing rows completes
    the clockwise rotation.

    Time Complexity  : O(n²)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void rotate90(int[][] matrix) {

        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
    }

    /*
    =====================================================================================
    HELPER FUNCTION: reverseRow
    -------------------------------------------------------------------------------------
    Reverses a single row of the matrix.
    =====================================================================================
    */
    static void reverseRow(int[] row) {

        int left = 0, right = row.length - 1;

        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;

            left++;
            right--;
        }
    }

    /*
    =====================================================================================
    HELPER FUNCTION: printMatrix
    -------------------------------------------------------------------------------------
    Prints the matrix in row-wise format.
    =====================================================================================
    */
    static void printMatrix(int[][] matrix) {

        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
