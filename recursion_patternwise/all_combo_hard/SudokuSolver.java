package recursion_patternwise.all_combo_hard;

public class SudokuSolver {

    /*
    =====================================================================================
    PROBLEM: SUDOKU SOLVER
    -------------------------------------------------------------------------------------
    Given a 9×9 Sudoku board, fill all empty cells (denoted by 0) such that:

    RULES:
    1. Each row contains digits 1–9 exactly once
    2. Each column contains digits 1–9 exactly once
    3. Each 3×3 sub-grid contains digits 1–9 exactly once

    APPROACH USED: BACKTRACKING

    IDEA:
    • Try placing digits 1–9 in each empty cell
    • Check if the placement is valid
    • If valid, recurse to solve remaining board
    • If it leads to a dead end, undo the choice (backtrack)

    This is a classic Constraint Satisfaction Problem.

    Time Complexity  : O(9^k) where k = number of empty cells (worst case)
    Space Complexity : O(81) recursion depth (constant board size)

    Example:
    Input :
    5 3 . . 7 . . . .
    6 . . 1 9 5 . . .
    . 9 8 . . . . 6 .
    ...

    Output:
    Fully solved Sudoku board
    =====================================================================================
    */

    /* ============================ SOLVER FUNCTION ============================ */

    /*
    =====================================================================================
    FUNCTION: solveSudoku
    -------------------------------------------------------------------------------------
    Solves the Sudoku board using recursive backtracking.

    RETURNS:
    • true  → if solution exists
    • false → if puzzle is unsolvable
    =====================================================================================
    */
    public static boolean solveSudoku(int[][] board) {
        return solve(board);
    }

    /*
    =====================================================================================
    FUNCTION: solve (BACKTRACKING CORE)
    -------------------------------------------------------------------------------------
    ALGORITHM:
    1. Find the next empty cell (value = 0)
    2. Try digits from 1 to 9
    3. If digit is valid:
        - Place digit
        - Recurse
        - If recursion succeeds → solution found
        - Else backtrack
    4. If no digit fits → return false
    =====================================================================================
    */
    private static boolean solve(int[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {

                    for (int num = 1; num <= 9; num++) {

                        if (isValid(board, row, col, num)) {

                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    // No valid number fits here
                    return false;
                }
            }
        }

        // No empty cell left → solved
        return true;
    }

    /* ============================ VALIDITY CHECK ============================ */

    /*
    =====================================================================================
    FUNCTION: isValid
    -------------------------------------------------------------------------------------
    Checks whether placing a number is valid by ensuring:
    • No repetition in row
    • No repetition in column
    • No repetition in 3×3 sub-grid
    =====================================================================================
    */
    private static boolean isValid(int[][] board, int row, int col, int num) {

        // Check row
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) return false;
        }

        // Check column
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) return false;
        }

        // Check 3x3 box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }

        return true;
    }

    /* ============================ UTILITY METHODS ============================ */

    /*
    =====================================================================================
    FUNCTION: printBoard
    -------------------------------------------------------------------------------------
    Prints the Sudoku board in readable format.
    =====================================================================================
    */
    private static void printBoard(int[][] board) {

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0)
                System.out.println("-----------");

            for (int j = 0; j < 9; j++) {

                if (j % 3 == 0 && j != 0)
                    System.out.print("| ");

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* ============================ DRIVER CODE ============================ */

    public static void main(String[] args) {

        int[][] board = {
            {5,3,0,0,7,0,0,0,0},
            {6,0,0,1,9,5,0,0,0},
            {0,9,8,0,0,0,0,6,0},
            {8,0,0,0,6,0,0,0,3},
            {4,0,0,8,0,3,0,0,1},
            {7,0,0,0,2,0,0,0,6},
            {0,6,0,0,0,0,2,8,0},
            {0,0,0,4,1,9,0,0,5},
            {0,0,0,0,8,0,0,7,9}
        };

        System.out.println("Unsolved Sudoku:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}
