package binarry_search.BS_on_Answers;

public class KthMissingPositive {

    /*
    =====================================================================================
    PROBLEM: KTH MISSING POSITIVE NUMBER
    -------------------------------------------------------------------------------------
    Given an array arr of positive integers sorted in ascending order, find the kth 
    missing positive integer that is not in this array. For example, if the array is 
    [1,2,3,4] and k=2, the missing positives are [5,6,7,...] so the 2nd missing positive 
    is 6. Use binary search to find the position where the kth missing number would exist. 
    At each position, we can calculate how many numbers are missing up to that point.

    Time Complexity: O(log n)
    Space Complexity: O(1)

    Example:
    Input:  arr=[1,2,3,4], k=2
    Output: 6 (missing are 5,6,7,...)
    =====================================================================================
    */
    
    /**
     * Finds the kth missing positive integer from a sorted array of positive integers.
     * Binary searches on the array indices to find the first position where the number of
     * missing positive integers is >= k. At each position i, missing count = arr[i] - (i+1).
     * Once found, the kth missing number is calculated as: left + k, where left is the index.
     * This handles the gap between array elements and automatically computes the answer.
     * 
     * @param arr sorted array of positive integers
     * @param k the kth missing positive to find
     * @return the kth missing positive integer
     */
    public static int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Count missing positive numbers up to arr[mid]
            int missing = arr[mid] - (mid + 1);
            
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // left is the first index where arr[left] - (left + 1) >= k
        // The kth missing number is left + k
        return left + k;
    }
    
    public static void main(String[] args) {
        System.out.println("Kth missing [1,2,3,4], k=2: " + findKthPositive(new int[]{1,2,3,4}, 2));      // 6
        System.out.println("Kth missing [2,3,4,7,11], k=5: " + findKthPositive(new int[]{2,3,4,7,11}, 5)); // 9
        System.out.println("Kth missing [1,2,3,4,5,6,7,8,9,10], k=1: " + findKthPositive(new int[]{1,2,3,4,5,6,7,8,9,10}, 1)); // 11
    }
}
