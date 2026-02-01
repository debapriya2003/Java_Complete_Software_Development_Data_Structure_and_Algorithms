package arrays.easy;
import java.util.HashSet;
import java.util.Set;

public class FindTheUnion {

    /*
    =====================================================================================
    PROBLEM: FIND THE UNION OF TWO ARRAYS
    -------------------------------------------------------------------------------------
    The union of two arrays is a collection of all distinct elements present in either of
    the arrays. Duplicate elements must appear only once in the result.

    This problem is commonly solved using hashing because it automatically handles
    duplicates and provides efficient insertion and lookup. The HashSet data structure
    is ideal for this purpose as it stores only unique elements.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};

        Set<Integer> union = findUnion(arr1, arr2);

        System.out.println("Union of the two arrays:");
        for (int num : union) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: findUnion
    -------------------------------------------------------------------------------------
    This function finds the union of two arrays using a HashSet.

    Logic:
    • Create an empty HashSet
    • Insert all elements of the first array into the set
    • Insert all elements of the second array into the same set
    • Since HashSet stores only unique values, duplicates are automatically ignored
    • The resulting set represents the union of the two arrays

    Time Complexity  : O(n + m)
    Space Complexity : O(n + m)
    where n and m are the sizes of the two arrays
    =====================================================================================
    */
    static Set<Integer> findUnion(int[] arr1, int[] arr2) {

        Set<Integer> set = new HashSet<>();

        // Add elements of first array
        for (int num : arr1) {
            set.add(num);
        }

        // Add elements of second array
        for (int num : arr2) {
            set.add(num);
        }

        return set;
    }
}
