package binarry_search.BS_on_Answers;

public class FindSquareRoot {

    /*
    =====================================================================================
    PROBLEM: FIND SQUARE ROOT OF A NUMBER IN LOG N TIME
    -------------------------------------------------------------------------------------
    Given a non-negative integer x, return the square root of x rounded down to the 
    nearest integer. The returned integer should also be non-negative. This problem can 
    be efficiently solved using binary search by treating it as searching for the largest 
    number whose square is less than or equal to x. We search within the range [0, x] 
    and use the middle value to check if it could be the square root.

    Time Complexity: O(log n)
    Space Complexity: O(1)

    Example:
    Input:  x = 8
    Output: 2 (because 2*2 = 4 <= 8 and 3*3 = 9 > 8)
    =====================================================================================
    */
    
    /**
     * Finds the square root of a given non-negative integer using binary search.
     * Uses the property that we need to find the largest number whose square is
     * less than or equal to x. Searches in range [1, x] and narrows down by comparing
     * the square of mid with x. Returns the integer square root rounded down.
     * 
     * @param x the non-negative integer
     * @return the integer square root of x rounded down
     */
    public static int squareRoot(int x) {
        if (x == 0) return 0;
        
        long left = 1, right = x;
        long result = 1;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;
            
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) result;
    }
    
    public static void main(String[] args) {
        System.out.println("Square root of 8: " + squareRoot(8));     // 2
        System.out.println("Square root of 16: " + squareRoot(16));   // 4
        System.out.println("Square root of 25: " + squareRoot(25));   // 5
        System.out.println("Square root of 0: " + squareRoot(0));     // 0
        System.out.println("Square root of 1: " + squareRoot(1));     // 1
        System.out.println("Square root of 7: " + squareRoot(7));     // 2
    }
}
