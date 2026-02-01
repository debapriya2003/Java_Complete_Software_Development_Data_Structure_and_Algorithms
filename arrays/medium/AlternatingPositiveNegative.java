package arrays.medium;

import java.util.ArrayList;
import java.util.List;

public class AlternatingPositiveNegative {

    /*
    =====================================================================================
    PROBLEM: ARRANGE ARRAY IN ALTERNATING POSITIVE AND NEGATIVE ELEMENTS
    -------------------------------------------------------------------------------------
    Given an array containing both positive and negative integers, the task is to rearrange
    the array so that positive and negative numbers appear alternately. The relative order
    of positive numbers among themselves and negative numbers among themselves should be
    preserved.

    If one type (positive or negative) has more elements than the other, the remaining
    elements should appear at the end of the array.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, -2, 3, -4, -1, 4};

        rearrange(arr);

        System.out.print("Array after alternating positives and negatives: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: rearrange
    -------------------------------------------------------------------------------------
    This function rearranges the array so that positive and negative numbers appear
    alternately while preserving their relative order.

    LOGIC:
    • Separate all positive and negative elements into two lists
    • Use indices to place elements alternately from both lists
    • If one list gets exhausted, append remaining elements

    This approach is simple and clear, though it uses extra space.

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static void rearrange(int[] arr) {

        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        // Separate positives and negatives
        for (int num : arr) {
            if (num >= 0)
                positives.add(num);
            else
                negatives.add(num);
        }

        int i = 0, p = 0, n = 0;

        // Alternate placement
        while (p < positives.size() && n < negatives.size()) {
            arr[i++] = positives.get(p++);
            arr[i++] = negatives.get(n++);
        }

        // Append remaining positives
        while (p < positives.size()) {
            arr[i++] = positives.get(p++);
        }

        // Append remaining negatives
        while (n < negatives.size()) {
            arr[i++] = negatives.get(n++);
        }
    }
}
