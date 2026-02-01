package binarry_search.BS_on_Answers;

public class CapacityToShip {

    /*
    =====================================================================================
    PROBLEM: CAPACITY TO SHIP PACKAGES WITHIN D DAYS
    -------------------------------------------------------------------------------------
    A conveyor belt system with packages that have certain weights. The system has a 
    capacity constraint and can move packages within d days. Find the minimum ship 
    capacity of the conveyor belt such that all the packages can be shipped within days d. 
    Each day, we load packages on the belt in order, and all packages must be loaded 
    within the day capacity. Use binary search on capacity and check if d days is sufficient.

    Time Complexity: O(n * log(sum))
    Space Complexity: O(1)

    Example:
    Input:  weights=[1,2,3,4,5,6,7,8,9,10], days=5
    Output: 15 (capacity of 15, we need [1..5], [6..7], [8..9], [10], [...])
    =====================================================================================
    */
    
    /**
     * Finds the minimum ship capacity needed to deliver all packages within the given days.
     * Binary searches on capacity from the maximum weight to the total weight sum. For each
     * candidate capacity, checks if all packages can be shipped within days using greedy allocation.
     * Loads packages sequentially, starting a new day when adding next package exceeds capacity.
     * Returns the minimum capacity that allows delivering all within the day constraint.
     * 
     * @param weights array of package weights
     * @param days maximum days available
     * @return minimum ship capacity required
     */
    public static int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canShip(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    /**
     * Helper function to check if packages can be shipped within days with given capacity.
     * Greedily loads packages into days, moving to next day whenever adding a package
     * would exceed the capacity. Counts total days required and returns true if <= given days.
     * 
     * @param weights array of package weights
     * @param days maximum days available
     * @param capacity the candidate capacity to check
     * @return true if all packages can be shipped within days
     */
    private static boolean canShip(int[] weights, int days, int capacity) {
        int count = 1;
        int currentWeight = 0;
        
        for (int weight : weights) {
            if (currentWeight + weight <= capacity) {
                currentWeight += weight;
            } else {
                count++;
                currentWeight = weight;
            }
        }
        return count <= days;
    }
    
    public static void main(String[] args) {
        System.out.println("Capacity [1,2,3,4,5,6,7,8,9,10], days=5: " + 
            shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));  // 15
        System.out.println("Capacity [3,2,2,4,1,4], days=3: " + 
            shipWithinDays(new int[]{3,2,2,4,1,4}, 3));           // 6
        System.out.println("Capacity [1,2,3,1,1], days=4: " + 
            shipWithinDays(new int[]{1,2,3,1,1}, 4));             // 3
    }
}
