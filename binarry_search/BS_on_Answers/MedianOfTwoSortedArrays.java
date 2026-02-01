package binarry_search.BS_on_Answers;

public class MedianOfTwoSortedArrays {

    /*
    =====================================================================================
    PROBLEM: MEDIAN OF TWO SORTED ARRAYS
    -------------------------------------------------------------------------------------
    Given two sorted arrays of different sizes, find the median of the combined array 
    without actually merging them. The median is the middle value for odd total length 
    or average of two middle values for even total length. Use binary search on the 
    smaller array to partition both arrays such that the left partition has the same 
    number of elements as the right partition, ensuring we can find the median in O(log min(n,m)).

    Time Complexity: O(log(min(n,m)))
    Space Complexity: O(1)

    Example:
    Input:  nums1=[1,3], nums2=[2]
    Output: 2.0 (combined: [1,2,3], median is 2)
    =====================================================================================
    */
    
    /**
     * Finds the median of two sorted arrays without merging them.
     * Binary searches on the smaller array to partition both arrays such that left
     * partition has same elements as right partition. Ensures all left elements <= all
     * right elements. Handles edge cases using Integer.MIN_VALUE and Integer.MAX_VALUE.
     * Returns average of middle elements for even total length, or middle element for odd.
     * 
     * @param nums1 first sorted array
     * @param nums2 second sorted array
     * @return the median of the combined sorted array
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        
        while (left <= right) {
            int cut1 = left + (right - left) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;
            
            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int right1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int right2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];
            
            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return (double) Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                right = cut1 - 1;
            } else {
                left = cut1 + 1;
            }
        }
        return -1.0;
    }
    
    public static void main(String[] args) {
        System.out.println("Median [1,3], [2]: " + findMedianSortedArrays(new int[]{1,3}, new int[]{2}));             // 2.0
        System.out.println("Median [1,2], [3,4]: " + findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));         // 2.5
        System.out.println("Median [0,0], [0,0]: " + findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}));         // 0.0
    }
}
