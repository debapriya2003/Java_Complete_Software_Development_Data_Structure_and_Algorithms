package binarry_search.BS_on_Answers;

public class MinimizeMaxDistance {

    /*
    =====================================================================================
    PROBLEM: MINIMIZE MAX DISTANCE TO GAS STATION
    -------------------------------------------------------------------------------------
    Given n gas stations at positions and k gas stations to add, find the minimum 
    possible maximum distance between consecutive gas stations. We need to place k 
    new stations optimally to minimize the largest gap. Use binary search on the maximum 
    distance. For each candidate maximum distance, use a greedy approach to check if 
    we can add k stations such that all gaps are at most this distance.

    Time Complexity: O(n * log(max_distance))
    Space Complexity: O(1)

    Example:
    Input:  stations=[1,2,3,4,5,6,7,8,9,10], k=9
    Output: 0.5 (place stations optimally to minimize max gap)
    =====================================================================================
    */
    
    /**
     * Finds the minimum possible maximum distance between consecutive gas stations.
     * Binary searches on the maximum distance with floating point precision. For each
     * candidate maximum distance, calculates minimum stations needed in each gap and
     * checks if total stations needed <= k. Uses 100 iterations for precision. Returns
     * the minimum maximum distance achievable by optimally placing k new stations.
     * 
     * @param stations array of existing station positions
     * @param k number of new stations to place
     * @return minimum possible maximum distance between consecutive stations
     */
    public static double minimizeDistance(int[] stations, int k) {
        double left = 0.0, right = 0.0;
        
        for (int i = 0; i < stations.length - 1; i++) {
            right = Math.max(right, (double) stations[i + 1] - stations[i]);
        }
        
        for (int i = 0; i < 100; i++) {
            double mid = left + (right - left) / 2.0;
            
            if (canPlace(stations, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
    
    /**
     * Helper function to check if k stations can make all gaps <= maxDist.
     * For each gap between consecutive stations, calculates minimum new stations
     * needed: ceil(distance/maxDist) - 1. Sums all needed stations and returns true
     * if total needed stations <= k, meaning we can achieve the target maximum distance.
     * 
     * @param stations array of station positions
     * @param k number of new stations available
     * @param maxDist candidate maximum distance to check
     * @return true if k stations can achieve maxDist maximum gap
     */
    private static boolean canPlace(int[] stations, int k, double maxDist) {
        int stationsUsed = 0;
        
        for (int i = 0; i < stations.length - 1; i++) {
            double dist = stations[i + 1] - stations[i];
            int needed = (int) Math.ceil(dist / maxDist) - 1;
            stationsUsed += needed;
            if (stationsUsed > k) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Min distance [1,2,3,4,5,6,7,8,9,10], k=9: " + 
            minimizeDistance(new int[]{1,2,3,4,5,6,7,8,9,10}, 9));  // ~0.5
        System.out.println("Min distance [1,2,3,4,5], k=4: " + 
            minimizeDistance(new int[]{1,2,3,4,5}, 4));             // ~0.5
    }
}
