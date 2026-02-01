package arrays.medium;

import java.util.ArrayList;
import java.util.List;

public class LeadersInArray {

    /*
    =====================================================================================
    PROBLEM: LEADERS IN AN ARRAY
    -------------------------------------------------------------------------------------
    An element in an array is called a leader if it is greater than all the elements to
    its right side. The rightmost element is always considered a leader because there are
    no elements to its right.

    This problem tests understanding of array traversal, comparisons, and optimization.
    A brute-force solution would require nested loops, but an optimal solution exists
    using a single traversal from right to left.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {16, 17, 4, 3, 5, 2};

        List<Integer> leaders = findLeaders(arr);

        System.out.print("Leaders in the array: ");
        for (int leader : leaders) {
            System.out.print(leader + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: findLeaders
    -------------------------------------------------------------------------------------
    This function finds all leaders in the array using an optimized approach.

    LOGIC:
    • Start traversing the array from the rightmost element
    • Keep track of the maximum element seen so far (rightMax)
    • If the current element is greater than rightMax, it is a leader
    • Update rightMax whenever a new leader is found

    This approach avoids nested loops and finds all leaders efficiently.

    Time Complexity  : O(n)
    Space Complexity : O(n) (for storing leaders)
    =====================================================================================
    */
    static List<Integer> findLeaders(int[] arr) {

        List<Integer> leaders = new ArrayList<>();

        int rightMax = arr[arr.length - 1];
        leaders.add(rightMax);   // Rightmost element is always a leader

        // Traverse from right to left
        for (int i = arr.length - 2; i >= 0; i--) {

            if (arr[i] > rightMax) {
                rightMax = arr[i];
                leaders.add(rightMax);
            }
        }

        return leaders;
    }
}

