package binarry_search.BS_on_Answers;

public class SplitArrayLargestSum {

    /*
    =====================================================================================
    PROBLEM: SPLIT ARRAY LARGEST SUM
    -------------------------------------------------------------------------------------
    Given an array of non-negative integers, we need to split the array into k subarrays 
    such that the maximum sum among all subarrays is minimized. Each subarray is contiguous 
    and must contain at least one element. This is solved using binary search on the maximum 
    sum. For each candidate maximum sum, we greedily check if we can split the array into 
    at most k subarrays where no subarray exceeds this sum using a single pass.

    Time Complexity: O(n * log(sum))
    Space Complexity: O(1)

    Example:
    Input:  arr=[7,2,5,10,8], k=2
    Output: 18 (split as [7,2,5,10] [8] with max 18 or [7] [2,5,10,8] with max 25)
    =====================================================================================
    */
    
    /**
     * Finds the minimum possible maximum sum when splitting array into k subarrays.
     * Binary searches on the maximum sum from the largest element to the total sum.
     * For each candidate maximum sum, checks if array can be split into k or fewer
     * subarrays where no subarray exceeds the maximum using greedy allocation.
     * Returns the minimum maximum sum that allows valid split into exactly k parts.
     * 
     * @param nums array of non-negative integers
     * @param k number of subarrays to split into
     * @return minimum possible maximum subarray sum
     */
    public static int splitArray(int[] nums, int k) {
        int left = 0, right = 0;
        
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canSplit(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    /**
     * Helper function to check if array can be split into k subarrays with max sum limit.
     * Greedily creates subarrays by accumulating elements until adding next would exceed
     * maxSum, then starts new subarray. Returns true if entire array can be split into
     * k or fewer subarrays without any subarray exceeding the maximum sum constraint.
     * 
     * @param nums array of non-negative integers
     * @param k number of subarrays allowed
     * @param maxSum maximum allowed sum per subarray
     * @return true if array can be split with max sum constraint
     */
    private static boolean canSplit(int[] nums, int k, int maxSum) {
        int subarrays = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                subarrays++;
                currentSum = num;
            }
        }
        return subarrays <= k;
    }
    
    public static void main(String[] args) {
        System.out.println("Split [7,2,5,10,8], k=2: " + splitArray(new int[]{7,2,5,10,8}, 2));   // 18
        System.out.println("Split [1,2,3,4,5], k=2: " + splitArray(new int[]{1,2,3,4,5}, 2));     // 9
        System.out.println("Split [1,4,4], k=3: " + splitArray(new int[]{1,4,4}, 3));             // 4
    }
}
