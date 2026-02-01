package arrays.hard;

public class FindRepeatingAndMissingNumber {

    /*
    =====================================================================================
    PROBLEM: FIND THE REPEATING AND MISSING NUMBER
    -------------------------------------------------------------------------------------
    Given an array of size n containing numbers from 1 to n, one number is missing and
    one number is repeated. The task is to find both numbers.

    Constraints:
    • Array contains numbers from 1 to n
    • One number repeats exactly once
    • One number is missing exactly once

    A brute-force or sorting-based approach is inefficient. The optimal solution uses
    mathematical equations to solve the problem in linear time and constant space.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 5, 3};

        int[] result = findRepeatingAndMissing(arr);

        System.out.println("Repeating number: " + result[0]);
        System.out.println("Missing number  : " + result[1]);
    }

    /*
    =====================================================================================
    FUNCTION: findRepeatingAndMissing
    -------------------------------------------------------------------------------------
    This function finds the repeating and missing numbers using mathematics.

    MATHEMATICAL IDEA:
    Let:
        S  = sum of first n natural numbers
        S' = sum of array elements
        P  = sum of squares of first n natural numbers
        P' = sum of squares of array elements

    Then:
        S - S' = Missing - Repeating  → (1)
        P - P' = Missing² - Repeating²
               = (Missing - Repeating)(Missing + Repeating)

    Using (1), we can compute Missing + Repeating and solve both values.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int[] findRepeatingAndMissing(int[] arr) {

        int n = arr.length;

        long sumExpected = (long) n * (n + 1) / 2;
        long sumSquaresExpected = (long) n * (n + 1) * (2 * n + 1) / 6;

        long sumActual = 0;
        long sumSquaresActual = 0;

        for (int num : arr) {
            sumActual += num;
            sumSquaresActual += (long) num * num;
        }

        long diff = sumExpected - sumActual; // Missing - Repeating
        long squareDiff = sumSquaresExpected - sumSquaresActual;

        long sum = squareDiff / diff; // Missing + Repeating

        int missing = (int) ((diff + sum) / 2);
        int repeating = (int) (sum - missing);

        return new int[]{repeating, missing};
    }
}
