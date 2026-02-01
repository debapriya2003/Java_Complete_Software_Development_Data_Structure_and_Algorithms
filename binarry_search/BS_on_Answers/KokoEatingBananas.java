package binarry_search.BS_on_Answers;

public class KokoEatingBananas {

    /*
    =====================================================================================
    PROBLEM: KOKO EATING BANANAS
    -------------------------------------------------------------------------------------
    Koko the monkey loves eating bananas and has a pile of bananas to eat. She receives 
    h hours to finish all bananas. Each hour, she chooses a pile and eats some bananas 
    from it. If the pile has fewer bananas than her eating speed k, she finishes in less 
    than an hour and moves to the next pile. Find the minimum eating speed k such that 
    she can finish all bananas within h hours. Binary search on the answer by checking 
    if a given speed k allows her to finish within time h.

    Time Complexity: O(n * log(max_pile))
    Space Complexity: O(1)

    Example:
    Input:  piles = [1,1,1,1], h = 4
    Output: 1 (eat 1 banana per hour, finish in 4 hours)
    =====================================================================================
    */
    
    /**
     * Finds the minimum eating speed k such that Koko can finish all bananas within h hours.
     * Binary searches on the eating speed from 1 to the maximum pile size. For each candidate
     * speed, calculates total hours needed using ceiling division for each pile. If hours needed
     * is within limit h, tries slower speeds; otherwise tries faster speeds. Returns the minimum
     * speed that allows finishing within the time constraint.
     * 
     * @param piles array of banana pile sizes
     * @param h maximum hours available
     * @return minimum eating speed in bananas per hour
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        
        // Find max pile
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            long hoursNeeded = 0;
            
            // Calculate hours needed at speed mid
            for (int pile : piles) {
                hoursNeeded += (pile + mid - 1) / mid; // Ceiling division
            }
            
            if (hoursNeeded <= h) {
                right = mid; // Try slower speed
            } else {
                left = mid + 1; // Need faster speed
            }
        }
        return left;
    }
    
    public static void main(String[] args) {
        System.out.println("Min speed [1,1,1,1], h=4: " + minEatingSpeed(new int[]{1,1,1,1}, 4));  // 1
        System.out.println("Min speed [312884132], h=968709470: " + minEatingSpeed(new int[]{312884132}, 968709470)); // 1
        System.out.println("Min speed [1,4,3,2], h=9: " + minEatingSpeed(new int[]{1,4,3,2}, 9));  // 2
    }
}
