package binarry_search.BS_on_Answers;

public class FindSmallestDivisor {

    /*
    =====================================================================================
    PROBLEM: FIND THE SMALLEST DIVISOR
    -------------------------------------------------------------------------------------
    Given an array of positive integers nums and a positive integer threshold, find the 
    smallest divisor d such that the sum of ceil(nums[i]/d) is less than or equal to the 
    threshold. We need to find the minimum divisor that satisfies this condition. Use 
    binary search on the divisor value and check if a given divisor d produces a sum 
    within the threshold using ceiling division for each element in the array.

    Time Complexity: O(n * log(max_element))
    Space Complexity: O(1)

    Example:
    Input:  nums=[1,2,5,9], threshold=6
    Output: 5 (1/5 + 2/5 + 5/5 + 9/5 = 1+1+1+2 = 5 <= 6)
    =====================================================================================
    */
    
    /**
     * Finds the smallest divisor d such that the sum of ceil(nums[i]/d) <= threshold.
     * Binary searches on divisor values from 1 to the maximum element. For each candidate divisor,
     * calculates the sum using ceiling division: (nums[i] + d - 1) / d for each element.
     * If sum is within threshold, tries smaller divisors; otherwise tries larger ones.
     * Returns the minimum divisor that satisfies the sum constraint.
     * 
     * @param nums array of positive integers
     * @param threshold the maximum allowed sum
     * @return smallest divisor satisfying the condition
     */
    public static int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = 0;
        
        for (int num : nums) {
            right = Math.max(right, num);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            
            // Calculate sum of ceil(nums[i]/mid)
            for (int num : nums) {
                sum += (num + mid - 1) / mid;
            }
            
            if (sum <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        System.out.println("Smallest divisor [1,2,5,9], threshold=6: " + smallestDivisor(new int[]{1,2,5,9}, 6));  // 5
        System.out.println("Smallest divisor [2,3,5], threshold=7: " + smallestDivisor(new int[]{2,3,5}, 7));       // 3
        System.out.println("Smallest divisor [1,2,3,4,5], threshold=8: " + smallestDivisor(new int[]{1,2,3,4,5}, 8)); // 3
    }
}
