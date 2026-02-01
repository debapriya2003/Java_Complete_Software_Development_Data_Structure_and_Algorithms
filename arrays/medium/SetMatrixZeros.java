package arrays.medium;

public class SetMatrixZeros {

    /*
    =====================================================================================
    PROBLEM: SET MATRIX ZEROS
    -------------------------------------------------------------------------------------
    Given a matrix, if an element is 0, set its entire row and entire column to 0.
    The modification must be done in-place.

    A brute-force solution would mark rows and columns separately using extra space.
    The optimal solution avoids extra space by using the first row and first column
    of the matrix as markers to indicate which rows and columns should be zeroed.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        setZeroes(matrix);

        System.out.println("Matrix after setting zeros:");
        printMatrix(matrix);
    }

    /*
    =====================================================================================
    FUNCTION: setZeroes
    -------------------------------------------------------------------------------------
    This function sets entire rows and columns to zero if any element is zero, using
    constant extra space.

    CORE IDEA:
    • Use first row and first column as markers
    • Use two flags to track whether first row or first column originally contained zero
    • Traverse matrix (excluding first row/column) and mark zeros
    • Zero rows and columns based on markers
    • Finally, zero first row/column if needed

    Time Complexity  : O(n × m)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Check if first row has zero
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column has zero
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Mark rows and columns
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set zeros based on markers
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Zero first row if needed
        if (firstRowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }

        // Zero first column if needed
        if (firstColZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
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

