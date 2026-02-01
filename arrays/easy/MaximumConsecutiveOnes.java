package arrays.easy;
public class MaximumConsecutiveOnes {

    /*
    =====================================================================================
    PROBLEM: FIND MAXIMUM CONSECUTIVE ONES IN AN ARRAY
    -------------------------------------------------------------------------------------
    Given a binary array consisting only of 0s and 1s, the task is to find the maximum
    number of consecutive 1s present in the array.

    This problem tests array traversal, condition checking, and maintaining running
    counts. It is commonly asked in coding interviews and forms the basis for more
    advanced sliding window problems.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 1, 0, 1, 1, 1, 0, 1, 1};

        int maxOnes = findMaxConsecutiveOnes(arr);

        System.out.println("Maximum consecutive ones: " + maxOnes);
    }

    /*
    =====================================================================================
    FUNCTION: findMaxConsecutiveOnes
    -------------------------------------------------------------------------------------
    This function finds the maximum number of consecutive 1s in the given array.

    Logic:
    • Traverse the array from left to right
    • Maintain a counter for current consecutive 1s
    • Reset the counter whenever a 0 is encountered
    • Update the maximum count whenever current count increases

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findMaxConsecutiveOnes(int[] arr) {

        int maxCount = 0;
        int currentCount = 0;

        for (int num : arr) {

            if (num == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0; // Reset on encountering 0
            }
        }

        return maxCount;
    }
}

