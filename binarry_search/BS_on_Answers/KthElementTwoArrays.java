package binarry_search.BS_on_Answers;

public class KthElementTwoArrays {

    /*
    =====================================================================================
    PROBLEM: KTH ELEMENT OF TWO SORTED ARRAYS
    -------------------------------------------------------------------------------------
    Given two sorted arrays, find the kth smallest element considering both arrays as 
    one merged sequence without actually merging them. Use binary search to efficiently 
    find the answer in O(log(min(n,m))) time. We binary search on one array and use its 
    position to determine the corresponding position in the second array such that we 
    have exactly k-1 elements before our answer.

    Time Complexity: O(log(min(n,m)))
    Space Complexity: O(1)

    Example:
    Input:  arr1=[2,3,6,7], arr2=[1,4,8,10], k=5
    Output: 6 (merged: [1,2,3,4,6,7,8,10], 5th element is 6)
    =====================================================================================
    */
    
    /**
     * Finds the kth smallest element in two sorted arrays combined.
     * Binary searches on the first array to partition both such that we have exactly
     * k-1 elements before the answer. Uses cut positions to determine boundaries and
     * ensures all left elements are <= all right elements. Returns the minimum of the
     * right partition elements, which is the kth smallest in combined sequence.
     * 
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @param k the position to find
     * @return the kth smallest element in combined arrays
     */
    public static int kthElement(int[] arr1, int[] arr2, int k) {
        int n = arr1.length, m = arr2.length;
        int left = 0, right = n - 1;
        
        while (left <= right) {
            int cut1 = left + (right - left) / 2;
            int cut2 = k - 2 - cut1;
            
            int left1 = (cut1 >= 0) ? arr1[cut1] : Integer.MIN_VALUE;
            int left2 = (cut2 >= 0 && cut2 < m) ? arr2[cut2] : Integer.MIN_VALUE;
            int right1 = (cut1 + 1 < n) ? arr1[cut1 + 1] : Integer.MAX_VALUE;
            int right2 = (cut2 + 1 < m) ? arr2[cut2 + 1] : Integer.MAX_VALUE;
            
            if (left1 <= right2 && left2 <= right1) {
                return Math.min(right1, right2);
            } else if (left1 > right2) {
                right = cut1 - 1;
            } else {
                left = cut1 + 1;
            }
        }
        return -1;
    }
    
    /**
     * Alternative simpler approach using merge concept to find kth element.
     * Uses two pointers to traverse both arrays simultaneously, advancing whichever
     * pointer has the smaller element. Counts elements and returns the kth one.
     * Time complexity is O(k) but space is O(1). Useful for smaller k values.
     * 
     * @param arr1 first sorted array
     * @param arr2 second sorted array
     * @param k the position to find
     * @return the kth smallest element in combined arrays
     */
    public static int kthElementSimple(int[] arr1, int[] arr2, int k) {
        int i = 0, j = 0;
        int count = 0;
        
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                count++;
                if (count == k) return arr1[i];
                i++;
            } else {
                count++;
                if (count == k) return arr2[j];
                j++;
            }
        }
        
        while (i < arr1.length) {
            count++;
            if (count == k) return arr1[i];
            i++;
        }
        
        while (j < arr2.length) {
            count++;
            if (count == k) return arr2[j];
            j++;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Kth [2,3,6,7], [1,4,8,10], k=5: " + 
            kthElement(new int[]{2,3,6,7}, new int[]{1,4,8,10}, 5));  // 6
        System.out.println("Kth [1,5], [2,3,4,6], k=1: " + 
            kthElement(new int[]{1,5}, new int[]{2,3,4,6}, 1));       // 1
        System.out.println("Kth [1,5], [2,3,4,6], k=4: " + 
            kthElement(new int[]{1,5}, new int[]{2,3,4,6}, 4));       // 4
    }
}
