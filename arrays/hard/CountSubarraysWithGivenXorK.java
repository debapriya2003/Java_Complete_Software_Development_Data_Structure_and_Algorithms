package arrays.hard;

import java.util.HashMap;

public class CountSubarraysWithGivenXorK {

    /*
    =====================================================================================
    PROBLEM: COUNT SUBARRAYS WITH GIVEN XOR = K
    -------------------------------------------------------------------------------------
    Given an array of integers and an integer K, the task is to count the number of
    contiguous subarrays whose bitwise XOR is equal to K.

    A brute-force approach using nested loops would take O(n²) time. The optimal solution
    uses the Prefix XOR technique combined with a HashMap to achieve linear time
    complexity.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {4, 2, 2, 6, 4};
        int k = 6;

        int count = countSubarraysWithXor(arr, k);

        System.out.println("Number of subarrays with XOR " + k + " = " + count);
    }

    /*
    =====================================================================================
    FUNCTION: countSubarraysWithXor
    -------------------------------------------------------------------------------------
    This function counts subarrays whose XOR equals K using prefix XOR and a HashMap.

    CORE IDEA:
    • Maintain a running prefix XOR while traversing the array
    • Store frequencies of prefix XOR values in a HashMap
    • If (prefixXor ^ K) exists in the map, it indicates valid subarrays ending here
    • Add the frequency of (prefixXor ^ K) to the answer

    MATHEMATICAL BASIS:
        If prefixXor[j] ^ prefixXor[i] = K
        ⇒ subarray (i+1 to j) has XOR K

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int countSubarraysWithXor(int[] arr, int k) {

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        int prefixXor = 0;
        int count = 0;

        // Important initialization:
        // prefix XOR 0 occurs once before starting
        freqMap.put(0, 1);

        for (int num : arr) {

            prefixXor ^= num;

            // Check if there exists a prefixXor ^ K
            int required = prefixXor ^ k;

            if (freqMap.containsKey(required)) {
                count += freqMap.get(required);
            }

            // Update frequency of current prefixXor
            freqMap.put(prefixXor,
                    freqMap.getOrDefault(prefixXor, 0) + 1);
        }

        return count;
    }
}
