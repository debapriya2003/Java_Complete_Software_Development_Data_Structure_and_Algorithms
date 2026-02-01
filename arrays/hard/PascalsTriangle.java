package arrays.hard;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    /*
    =====================================================================================
    PROBLEM: PASCAL'S TRIANGLE
    -------------------------------------------------------------------------------------
    Pascal’s Triangle is a triangular array of numbers where:
    • The first and last elements of every row are 1
    • Every other element is the sum of the two elements directly above it

    Mathematical Relation:
        triangle[row][col] = triangle[row-1][col-1] + triangle[row-1][col]

    Pascal’s Triangle is widely used in:
    • Combinatorics (nCr)
    • Probability
    • Binomial expansions
    =====================================================================================
    */

    public static void main(String[] args) {

        int numRows = 5;

        List<List<Integer>> triangle = generate(numRows);

        System.out.println("Pascal's Triangle:");
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }

    /*
    =====================================================================================
    FUNCTION: generate
    -------------------------------------------------------------------------------------
    This function generates the first numRows of Pascal’s Triangle.

    LOGIC:
    • Create a list of lists to store the triangle
    • First row is always [1]
    • For each new row:
        - First and last elements are 1
        - Middle elements are sum of two numbers from the previous row
    • Continue until required number of rows are generated

    Time Complexity  : O(n²)
    Space Complexity : O(n²)
    =====================================================================================
    */
    static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows == 0)
            return triangle;

        // First row
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        // Generate remaining rows
        for (int i = 1; i < numRows; i++) {

            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();

            // First element
            currentRow.add(1);

            // Middle elements
            for (int j = 1; j < i; j++) {
                currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            // Last element
            currentRow.add(1);

            triangle.add(currentRow);
        }

        return triangle;
    }
}

