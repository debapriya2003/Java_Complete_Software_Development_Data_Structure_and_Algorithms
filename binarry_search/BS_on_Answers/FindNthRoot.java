package binarry_search.BS_on_Answers;

public class FindNthRoot {

    /*
    =====================================================================================
    PROBLEM: FIND THE NTH ROOT OF A NUMBER USING BINARY SEARCH
    -------------------------------------------------------------------------------------
    Given two positive integers x and n, find the nth root of x rounded down to the 
    nearest integer. The nth root of x is the number r such that r^n = x. This can be 
    efficiently solved using binary search by searching for the largest number whose 
    nth power is less than or equal to x. We search in range [1, x] and use multiplication 
    to check if the mid value could be the nth root, handling potential overflow carefully.

    Time Complexity: O(log x * log n)
    Space Complexity: O(1)

    Example:
    Input:  x = 27, n = 3
    Output: 3 (because 3^3 = 27)
    =====================================================================================
    */
    
    /**
     * Finds the nth root of a given positive integer using binary search.
     * Searches for the largest number r such that r^n equals x. Binary search is
     * performed in the range [1, x] and for each mid value, we compute mid^n and
     * compare with x. Handles potential overflow using a helper function that
     * returns a large value if overflow is detected during power calculation.
     * 
     * @param x the positive integer
     * @param n the root to find
     * @return the integer nth root of x rounded down
     */
    public static int nthRoot(int x, int n) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        
        long left = 1, right = x;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long power = power(mid, n);
            
            if (power == x) {
                return (int) mid;
            } else if (power < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }
    
    /**
     * Helper function to calculate mid^n with overflow handling.
     * Multiplies mid by itself n times to get mid^n. Returns a large sentinel value
     * if the result exceeds 10^9 to prevent integer overflow issues during computation.
     * This allows binary search to make correct decisions without overflow errors.
     * 
     * @param mid the base
     * @param n the exponent
     * @return mid^n or a large value if overflow detected
     */
    private static long power(long mid, int n) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            if (result > 1e9) return 1000000000L; // Return large value if overflow
            result *= mid;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("3rd root of 27: " + nthRoot(27, 3));      // 3
        System.out.println("2nd root of 16: " + nthRoot(16, 2));      // 4
        System.out.println("3rd root of 125: " + nthRoot(125, 3));    // 5
        System.out.println("2nd root of 8: " + nthRoot(8, 2));        // 2
        System.out.println("4th root of 81: " + nthRoot(81, 4));      // 3
    }
}
