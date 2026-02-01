package binarry_search.BS_on_Answers;

public class AggressiveCows {

    /*
    =====================================================================================
    PROBLEM: AGGRESSIVE COWS (COW PLACEMENT PROBLEM)
    -------------------------------------------------------------------------------------
    Given an array of stall positions and k cows to place, we need to maximize the 
    minimum distance between any two cows. This is a classic optimization problem where 
    we use binary search on the answer. We search for the minimum distance d such that 
    we can place k cows where each pair of adjacent cows is at least d distance apart. 
    For each candidate distance, greedily check if k cows can be placed maintaining distance.

    Time Complexity: O(n * log(max_position))
    Space Complexity: O(1)

    Example:
    Input:  stalls=[1,2,8,4,9], k=3
    Output: 4 (place at positions 1, 4, 8 with min distance 3,4)
    =====================================================================================
    */
    
    /**
     * Finds the maximum minimum distance possible when placing k cows in n stalls.
     * Binary searches on the minimum distance from 1 to the range of stalls. For each
     * candidate distance, uses greedy approach to check if k cows can be placed maintaining
     * that distance. Sorts stalls first, then greedily places cows at furthest valid positions.
     * Returns the maximum minimum distance that allows placing all k cows.
     * 
     * @param stalls array of stall positions
     * @param k number of cows to place
     * @return maximum possible minimum distance between any two cows
     */
    public static int aggressiveCows(int[] stalls, int k) {
        java.util.Arrays.sort(stalls);
        
        int left = 1, right = stalls[stalls.length - 1] - stalls[0];
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canPlace(stalls, k, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    /**
     * Helper function to check if k cows can be placed with minimum distance minDist.
     * Uses greedy approach: places first cow at first stall, then places each subsequent
     * cow at the furthest stall that maintains minimum distance from previous cow.
     * Returns true if all k cows can be successfully placed with required spacing.
     * 
     * @param stalls sorted array of stall positions
     * @param k number of cows to place
     * @param minDist minimum required distance between cows
     * @return true if k cows can be placed with minDist spacing
     */
    private static boolean canPlace(int[] stalls, int k, int minDist) {
        int count = 1;
        int lastPos = stalls[0];
        
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= minDist) {
                count++;
                lastPos = stalls[i];
                if (count == k) return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        System.out.println("Aggressive cows [1,2,8,4,9], k=3: " + aggressiveCows(new int[]{1,2,8,4,9}, 3));   // 4
        System.out.println("Aggressive cows [1,2,3,4,5], k=3: " + aggressiveCows(new int[]{1,2,3,4,5}, 3));    // 2
        System.out.println("Aggressive cows [0,3,4,7,10,9], k=4: " + aggressiveCows(new int[]{0,3,4,7,10,9}, 4)); // 3
    }
}
