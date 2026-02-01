package binarry_search.BS_on_Answers;

public class MinimumDaysToBouquets {

    /*
    =====================================================================================
    PROBLEM: MINIMUM DAYS TO MAKE M BOUQUETS
    -------------------------------------------------------------------------------------
    You want to make m bouquets from a garden with n flowers. Each bouquet requires 
    exactly k adjacent blooming flowers. The bloomDay array represents when each flower 
    blooms. Find the minimum number of days needed to make m bouquets, where a flower 
    on position i can only be used if it bloomed by day d. Use binary search on the number 
    of days and check if we can make m bouquets by day d using a greedy approach to count 
    consecutive bloomed flowers.

    Time Complexity: O(n * log(max_day))
    Space Complexity: O(1)

    Example:
    Input:  bloomDay=[1,10,3,10,2], m=3, k=1
    Output: 3 (by day 3: flowers at indices 0,2 bloom)
    =====================================================================================
    */
    
    /**
     * Finds the minimum number of days needed to make m bouquets of k consecutive blooming flowers.
     * Binary searches on the number of days from 1 to the maximum bloom day. For each candidate day,
     * checks if m bouquets can be formed by counting consecutive bloomed flowers and resetting
     * counter when a non-bloomed flower or k consecutive bloomed flowers are encountered. Returns
     * the earliest day when m bouquets can be successfully created, or -1 if impossible.
     * 
     * @param bloomDay array where bloomDay[i] is the day flower i blooms
     * @param m number of bouquets needed
     * @param k flowers required per bouquet
     * @return minimum days to make m bouquets, or -1 if impossible
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;
        
        int left = 1, right = 0;
        for (int day : bloomDay) {
            right = Math.max(right, day);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int bouquets = 0;
            int bloom = 0;
            
            // Count bouquets possible by day mid
            for (int day : bloomDay) {
                if (day <= mid) {
                    bloom++;
                    if (bloom == k) {
                        bouquets++;
                        bloom = 0;
                    }
                } else {
                    bloom = 0;
                }
            }
            
            if (bouquets >= m) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        System.out.println("Min days [1,10,3,10,2], m=3, k=1: " + minDays(new int[]{1,10,3,10,2}, 3, 1));  // 3
        System.out.println("Min days [1,10,3,10,2], m=2, k=2: " + minDays(new int[]{1,10,3,10,2}, 2, 2));  // 9
        System.out.println("Min days [7,7,7,7,13,11,12,7], m=2, k=3: " + minDays(new int[]{7,7,7,7,13,11,12,7}, 2, 3)); // 12
    }
}
