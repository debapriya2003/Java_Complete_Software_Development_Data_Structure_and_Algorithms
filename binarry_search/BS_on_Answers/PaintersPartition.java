package binarry_search.BS_on_Answers;

public class PaintersPartition {

    /*
    =====================================================================================
    PROBLEM: PAINTERS PARTITION PROBLEM
    -------------------------------------------------------------------------------------
    Given n boards and k painters, each board has a length representing painting time. 
    Find the minimum time required to paint all boards such that each painter paints 
    consecutive boards and work is distributed equally. Each painter paints their 
    assigned boards sequentially and time is the sum of lengths. Use binary search on 
    the maximum time any painter takes and check if k painters can complete within that time.

    Time Complexity: O(n * log(sum))
    Space Complexity: O(1)

    Example:
    Input:  boards=[5,5,5,5], k=2
    Output: 10 (painter 1 paints [5,5] in time 10, painter 2 paints [5,5] in time 10)
    =====================================================================================
    */
    
    /**
     * Finds the minimum time needed for k painters to paint all boards.
     * Binary searches on painting time from the largest board to sum of all boards.
     * For each candidate time, greedily checks if k painters can complete all boards.
     * Each painter works on consecutive boards, moving to next painter when time exceeded.
     * Returns the minimum time that allows all k painters to finish all boards together.
     * 
     * @param boards array of painting times for each board
     * @param k number of painters available
     * @return minimum time for k painters to complete all boards
     */
    public static int paintBoard(int[] boards, int k) {
        int left = 0, right = 0;
        
        for (int board : boards) {
            left = Math.max(left, board);
            right += board;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canPaint(boards, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    /**
     * Helper function to check if k painters can paint all boards within maxTime.
     * Greedily allocates consecutive boards to each painter until adding next board
     * would exceed maxTime. Moves to next painter when needed. Returns true if all
     * boards can be painted by k or fewer painters within the time constraint.
     * 
     * @param boards array of painting times
     * @param k number of painters
     * @param maxTime maximum time any painter can spend
     * @return true if k painters can complete all boards within maxTime
     */
    private static boolean canPaint(int[] boards, int k, int maxTime) {
        int painters = 1;
        int currentTime = 0;
        
        for (int board : boards) {
            if (currentTime + board <= maxTime) {
                currentTime += board;
            } else {
                painters++;
                currentTime = board;
            }
        }
        return painters <= k;
    }
    
    public static void main(String[] args) {
        System.out.println("Paint [5,5,5,5], k=2: " + paintBoard(new int[]{5,5,5,5}, 2));           // 10
        System.out.println("Paint [1,2,3,4,5], k=2: " + paintBoard(new int[]{1,2,3,4,5}, 2));      // 9
        System.out.println("Paint [10,20,30], k=2: " + paintBoard(new int[]{10,20,30}, 2));        // 50
    }
}
