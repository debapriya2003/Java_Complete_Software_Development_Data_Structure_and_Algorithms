package basics;
/****************************************************************************************
 * FILE NAME : HashingBasics.java
 * PURPOSE   : Explain hashing theory and solve frequency-based problems using HashMap
 * LEVEL     : DSA Foundations + Exam Oriented
 ****************************************************************************************/
/*
=========================================================================================
🔷 HASHING THEORY (COMPLETE EXPLANATION)
=========================================================================================

WHAT IS HASHING?
-----------------------------------------------------------------------------------------
Hashing is a technique used to store and retrieve data efficiently by converting a key
into an index using a hash function. Instead of searching elements linearly in an array
or list, hashing allows direct access to data in constant average time, i.e., O(1).

In hashing, a hash function takes an input value called a key and converts it into a
fixed-size integer known as a hash value. This hash value acts as an index in a data
structure called a hash table, where the actual data is stored.

-----------------------------------------------------------------------------------------
WHY HASHING IS NEEDED?
-----------------------------------------------------------------------------------------
Without hashing, many common problems require nested loops, which significantly increase
time complexity. Examples include:

• Counting frequencies of elements
• Checking whether an element exists
• Finding duplicate elements
• Finding highest or lowest frequency elements

Such problems using brute-force approaches take O(n²) time complexity. Hashing optimizes
these operations by storing elements as key-value pairs, reducing the time complexity to
O(n) and making the solution more efficient and scalable.

-----------------------------------------------------------------------------------------
HASHMAP IN JAVA (CORE OF HASHING)
-----------------------------------------------------------------------------------------
In Java, hashing is implemented using the HashMap class from the java.util package.
A HashMap stores data in the form of key-value pairs.

General representation:
Key → Value

Example:
Number → Frequency
5      → 3
2      → 1
7      → 4

Internally, HashMap works as follows:
• It uses a hash table data structure
• Keys are processed using the hashCode() method
• Hash collisions are handled using chaining
  (linked lists, and trees in newer Java versions)

-----------------------------------------------------------------------------------------
IMPORTANT PROPERTIES OF HASHMAP
-----------------------------------------------------------------------------------------
• Average time complexity for insertion and lookup: O(1)
• Worst-case time complexity (rare): O(n)
• Order of elements is NOT guaranteed
• Keys must be unique
• Values can be duplicated
• HashMap allows one null key and multiple null values

=========================================================================================
🔷 PROBLEM 1: COUNT FREQUENCIES OF ARRAY ELEMENTS
=========================================================================================

PROBLEM STATEMENT:
-----------------------------------------------------------------------------------------
Given an array of elements, determine how many times each element appears in the array.

BRUTE FORCE APPROACH:
-----------------------------------------------------------------------------------------
• Use nested loops
• For each element, count its occurrences
• Time Complexity: O(n²)
• Inefficient for large inputs ❌

HASHING APPROACH (OPTIMAL):
-----------------------------------------------------------------------------------------
• Traverse the array once
• Store each element as a key in a HashMap
• Store its frequency as the value
• Increment count whenever the element repeats
• Time Complexity: O(n)
• Space Complexity: O(n) ✅

=========================================================================================
🔷 PROBLEM 2: FIND HIGHEST AND LOWEST FREQUENCY ELEMENT
=========================================================================================

IDEA:
-----------------------------------------------------------------------------------------
Once the frequency of each element is stored in a HashMap:

• Traverse the HashMap entries
• Compare frequency values
• Track the maximum frequency element
• Track the minimum frequency element

This approach avoids sorting and nested loops, making it efficient with O(n) time
complexity and suitable for competitive programming and interviews.

=========================================================================================
*/

import java.util.HashMap;
import java.util.Map;

public class HashingBasics {

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3, 4, 1, 2};

        countFrequencies(arr);
        highestAndLowestFrequency(arr);
    }

    /*
    =====================================================================================
    PROBLEM 1: COUNT FREQUENCIES OF ARRAY ELEMENTS USING HASHING
    -------------------------------------------------------------------------------------
    This function counts how many times each element appears in the array. We use a
    HashMap where the key is the array element and the value is its frequency. As we
    traverse the array, we check whether the element already exists in the map. If it
    exists, we increment its count; otherwise, we insert it with frequency 1. This
    approach avoids nested loops and runs in linear time.
    =====================================================================================
    */
    static void countFrequencies(int[] arr) {

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            // If element exists, increase count
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            }
            // If element does not exist, insert with count 1
            else {
                freqMap.put(num, 1);
            }
        }

        System.out.println("Frequency of elements:");
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    /*
    =====================================================================================
    PROBLEM 2: FIND HIGHEST AND LOWEST FREQUENCY ELEMENT
    -------------------------------------------------------------------------------------
    After computing frequencies using HashMap, we traverse the map to determine which
    element has the highest frequency and which has the lowest. We initialize variables
    to store maximum and minimum frequencies and update them while iterating through
    the map. This approach efficiently solves the problem in linear time without sorting.
    =====================================================================================
    */
    static void highestAndLowestFrequency(int[] arr) {

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // Build frequency map
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = Integer.MIN_VALUE;
        int minFreq = Integer.MAX_VALUE;

        int maxElement = -1;
        int minElement = -1;

        // Traverse HashMap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {

            int element = entry.getKey();
            int frequency = entry.getValue();

            if (frequency > maxFreq) {
                maxFreq = frequency;
                maxElement = element;
            }

            if (frequency < minFreq) {
                minFreq = frequency;
                minElement = element;
            }
        }

        System.out.println("\nHighest Frequency Element:");
        System.out.println(maxElement + " → " + maxFreq + " times");

        System.out.println("\nLowest Frequency Element:");
        System.out.println(minElement + " → " + minFreq + " times");
    }
}
