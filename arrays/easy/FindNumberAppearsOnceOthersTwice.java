package arrays.easy;
public class FindNumberAppearsOnceOthersTwice {

    /*
    =====================================================================================
    PROBLEM: FIND THE NUMBER THAT APPEARS ONCE (OTHERS APPEAR TWICE)
    -------------------------------------------------------------------------------------
    Given an array where every element appears exactly twice except for one element that
    appears only once, the task is to find that unique element.

    This problem can be solved efficiently using the XOR (^) operation. XOR has special
    properties that make it ideal for this scenario, allowing us to solve the problem
    without using extra memory or nested loops.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {4, 1, 2, 1, 2};

        int uniqueElement = findSingleNumber(arr);

        System.out.println("Number that appears once: " + uniqueElement);
    }

    /*
    =====================================================================================
    FUNCTION: findSingleNumber
    -------------------------------------------------------------------------------------
    This function finds the element that appears only once using the XOR operation.

    XOR PROPERTIES USED:
    • a ^ a = 0          (same numbers cancel out)
    • a ^ 0 = a          (number XOR 0 remains unchanged)
    • XOR is commutative and associative

    Logic:
    • Initialize a variable result = 0
    • XOR all elements of the array with result
    • Duplicate elements cancel each other
    • Remaining value is the unique element

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findSingleNumber(int[] arr) {

        int result = 0;

        for (int num : arr) {
            result ^= num;   // XOR operation
        }

        return result;
    }
}

