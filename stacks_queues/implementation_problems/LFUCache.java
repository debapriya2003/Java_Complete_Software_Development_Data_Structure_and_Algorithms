package stacks_queues.implementation_problems;

import java.util.*;

/**
 * LFU Cache (Least Frequently Used)
 * 
 * Implement a data structure that supports:
 * 1. get(key): Return value, increment frequency
 * 2. put(key, value): Set key-value, increment frequency
 *                      If capacity exceeded, evict least frequently used
 *                      (if tie, evict least recently used among them)
 * 
 * Both operations must be O(1).
 * 
 * Example:
 * LFUCache cache = new LFUCache(2);
 * cache.put(1, 1);    // freq[1]=1, cache: {1}
 * cache.put(2, 2);    // freq[2]=1, cache: {1,2}
 * cache.get(1);       // return 1, freq[1]=2
 * cache.put(3, 3);    // freq[3]=1, evict 2 (freq=1, LRU), cache: {1,3}
 * cache.get(2);       // return -1 (not found)
 * cache.put(4, 4);    // freq[4]=1, evict 3 (freq=1, LRU), cache: {1,4}
 * cache.get(1);       // return 1, freq[1]=3
 * cache.get(3);       // return -1 (not found)
 * cache.get(4);       // return 4, freq[4]=2
 * 
 * Algorithm: HashMap + Frequency Map + LinkedHashSet
 * ===================================================
 * 1. node: HashMap<key → (value, frequency)>
 * 2. freqNode: HashMap<frequency → LinkedHashSet of keys>
 * 3. minFreq: track minimum frequency
 * 4. When capacity exceeded: evict from LinkedHashSet of minFreq
 * 
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 */

public class LFUCache {
    
    static class Node {
        int value;
        int frequency;
        
        Node(int value) {
            this.value = value;
            this.frequency = 1;
        }
    }
    
    private int capacity;
    private int minFreq;
    private Map<Integer, Node> cache;              // key → (value, freq)
    private Map<Integer, LinkedHashSet<Integer>> freqMap; // freq → keys
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }
    
    /**
     * Get value and increment frequency
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        
        Node node = cache.get(key);
        incrementFrequency(key, node);
        return node.value;
    }
    
    /**
     * Put key-value, evict LFU if needed
     */
    public void put(int key, int value) {
        if (capacity <= 0) return;
        
        if (cache.containsKey(key)) {
            // Update existing
            Node node = cache.get(key);
            node.value = value;
            incrementFrequency(key, node);
        } else {
            // New key
            if (cache.size() >= capacity) {
                evictLFU();
            }
            
            Node node = new Node(value);
            cache.put(key, node);
            
            // Add to frequency map
            freqMap.putIfAbsent(1, new LinkedHashSet<>());
            freqMap.get(1).add(key);
            minFreq = 1;
        }
    }
    
    /**
     * Increment frequency of a key
     */
    private void incrementFrequency(int key, Node node) {
        int freq = node.frequency;
        
        // Remove from old frequency set
        freqMap.get(freq).remove(key);
        
        // If old frequency set is now empty and it was minFreq
        if (freqMap.get(freq).isEmpty() && freq == minFreq) {
            minFreq++;
        }
        
        // Add to new frequency set
        node.frequency++;
        freqMap.putIfAbsent(node.frequency, new LinkedHashSet<>());
        freqMap.get(node.frequency).add(key);
    }
    
    /**
     * Evict LFU node
     */
    private void evictLFU() {
        LinkedHashSet<Integer> lfuSet = freqMap.get(minFreq);
        int keyToRemove = lfuSet.iterator().next(); // First element (LRU among LFU)
        lfuSet.remove(keyToRemove);
        cache.remove(keyToRemove);
    }
    
    public static void main(String[] args) {
        System.out.println("=== LFU Cache ===\n");
        
        LFUCache cache = new LFUCache(2);
        
        System.out.println("Cache capacity: 2\n");
        
        System.out.println("Operations:");
        System.out.println("1. put(1, 1)    → freq[1]=1, {1}");
        cache.put(1, 1);
        
        System.out.println("2. put(2, 2)    → freq[2]=1, {1,2}");
        cache.put(2, 2);
        
        System.out.println("3. get(1)       → 1, freq[1]=2");
        int val = cache.get(1);
        System.out.println("   Returned: " + val);
        
        System.out.println("4. put(3, 3)    → Evict 2 (freq=1, LRU), freq[3]=1, {1,3}");
        cache.put(3, 3);
        
        System.out.println("5. get(2)       → -1 (evicted)");
        val = cache.get(2);
        System.out.println("   Returned: " + val);
        
        System.out.println("6. put(4, 4)    → Evict 3 (freq=1, LRU), freq[4]=1, {1,4}");
        cache.put(4, 4);
        
        System.out.println("7. get(1)       → 1, freq[1]=3");
        val = cache.get(1);
        System.out.println("   Returned: " + val);
        
        System.out.println("8. get(3)       → -1 (evicted)");
        val = cache.get(3);
        System.out.println("   Returned: " + val);
        
        System.out.println("9. get(4)       → 4, freq[4]=2");
        val = cache.get(4);
        System.out.println("   Returned: " + val);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED TRACE:");
        System.out.println("-".repeat(70));
        
        cache = new LFUCache(2);
        
        System.out.println("\n1. put(1, 1)");
        cache.put(1, 1);
        System.out.println("   cache: {1→(1, freq=1)}");
        System.out.println("   freqMap: {1→[1]}");
        System.out.println("   minFreq: 1");
        
        System.out.println("\n2. put(2, 2)");
        cache.put(2, 2);
        System.out.println("   cache: {1→(1,1), 2→(2,1)}");
        System.out.println("   freqMap: {1→[1,2]}");
        System.out.println("   minFreq: 1");
        
        System.out.println("\n3. get(1) - increment freq[1]");
        cache.get(1);
        System.out.println("   cache: {1→(1,2), 2→(2,1)}");
        System.out.println("   freqMap: {1→[2], 2→[1]}");
        System.out.println("   minFreq: 1 (still exists)");
        
        System.out.println("\n4. put(3, 3) - capacity exceeded");
        System.out.println("   Evict from freqMap[minFreq=1] = [2]");
        System.out.println("   Remove key 2");
        cache.put(3, 3);
        System.out.println("   cache: {1→(1,2), 3→(3,1)}");
        System.out.println("   freqMap: {1→[3], 2→[1]}");
        System.out.println("   minFreq: 1");
        
        System.out.println("\n5. get(2)");
        val = cache.get(2);
        System.out.println("   Key not found, return -1");
        
        System.out.println("\n6. put(4, 4) - capacity exceeded");
        System.out.println("   Evict from freqMap[minFreq=1] = [3]");
        System.out.println("   Remove key 3 (LRU among LFU)");
        cache.put(4, 4);
        System.out.println("   cache: {1→(1,2), 4→(4,1)}");
        System.out.println("   freqMap: {1→[4], 2→[1]}");
        System.out.println("   minFreq: 1");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DATA STRUCTURES:");
        System.out.println("-".repeat(70));
        System.out.println("1. cache: HashMap<Integer, Node>");
        System.out.println("   - Maps key to (value, frequency)");
        System.out.println("   - O(1) access");
        
        System.out.println("\n2. freqMap: HashMap<Integer, LinkedHashSet<Integer>>");
        System.out.println("   - Maps frequency to set of keys with that frequency");
        System.out.println("   - LinkedHashSet maintains insertion order (LRU within frequency)");
        System.out.println("   - O(1) access and removal");
        
        System.out.println("\n3. minFreq: Integer");
        System.out.println("   - Tracks minimum frequency");
        System.out.println("   - Updated when incrementing frequency");
        System.out.println("   - Enables O(1) eviction");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY DIFFERENCE: LRU vs LFU");
        System.out.println("-".repeat(70));
        System.out.println("LRU (Least Recently Used):");
        System.out.println("  - Evicts based on recency");
        System.out.println("  - Uses DoublyLinkedList to track order");
        System.out.println("  - Example: [A, B, C] → access B → [A, C, B]");
        
        System.out.println("\nLFU (Least Frequently Used):");
        System.out.println("  - Evicts based on frequency");
        System.out.println("  - Secondary: LRU among same frequency");
        System.out.println("  - Uses frequency map + LinkedHashSet");
        System.out.println("  - Example: freq[A]=2, freq[B]=1 → evict B");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("OPERATIONS:");
        System.out.println("-".repeat(70));
        System.out.println("get(key):");
        System.out.println("  1. Check if key exists → O(1)");
        System.out.println("  2. Increment frequency → O(1)");
        System.out.println("  3. Remove from old freq set, add to new → O(1)");
        System.out.println("  4. Update minFreq if needed → O(1)");
        System.out.println("  Total: O(1)");
        
        System.out.println("\nput(key, value):");
        System.out.println("  1. If exists: update and increment freq → O(1)");
        System.out.println("  2. If new:");
        System.out.println("     - If at capacity: evict LFU → O(1)");
        System.out.println("     - Add to cache and freqMap → O(1)");
        System.out.println("     - Set minFreq = 1 → O(1)");
        System.out.println("  Total: O(1)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. LinkedHashSet maintains order (insertion = LRU order)");
        System.out.println("2. First element in freqMap[minFreq] is LRU among LFU");
        System.out.println("3. When frequency increases, minFreq stays same or increases");
        System.out.println("4. minFreq becomes k+1 only when last key with freq k is promoted");
        System.out.println("5. Every key starts with frequency 1");
    }
}
