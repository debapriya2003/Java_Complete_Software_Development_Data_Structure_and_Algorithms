package stacks_queues.implementation_problems;

import java.util.*;

/**
 * LRU Cache (Least Recently Used)
 * 
 * Implement a data structure that supports:
 * 1. get(key): Return value, mark as recently used
 * 2. put(key, value): Set key-value, evict LRU if capacity exceeded
 * 
 * Both operations must be O(1).
 * 
 * Example:
 * LRUCache cache = new LRUCache(2);
 * cache.put(1, 1);        // cache: {1=1}
 * cache.put(2, 2);        // cache: {1=1, 2=2}
 * cache.get(1);           // return 1, mark 1 as used
 * cache.put(3, 3);        // evict key 2 (LRU), cache: {1=1, 3=3}
 * cache.get(2);           // return -1 (not found)
 * cache.put(4, 4);        // evict key 1 (LRU), cache: {3=3, 4=4}
 * cache.get(1);           // return -1 (not found)
 * cache.get(3);           // return 3, mark 3 as used
 * cache.get(4);           // return 4, mark 4 as used
 * 
 * Algorithm: HashMap + Doubly Linked List
 * ========================================
 * 1. HashMap: key → node (O(1) access)
 * 2. Doubly LinkedList: track usage order
 *    - Most recent at head
 *    - Least recent at tail (evict this)
 * 3. get(key): access node, move to head
 * 4. put(key, value): update/create node, move to head, evict tail if needed
 * 
 * Time Complexity: O(1) for both get and put
 * Space Complexity: O(capacity)
 */

public class LRUCache {
    
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Map<Integer, Node> map;
    private Node head; // Most recently used
    private Node tail; // Least recently used
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // Dummy nodes to avoid null checks
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    /**
     * Get value and mark as recently used
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }
    
    /**
     * Put key-value pair, evict LRU if capacity exceeded
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // Update existing
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Create new
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            
            // Evict if capacity exceeded
            if (map.size() > capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
        }
    }
    
    /**
     * Remove node from list
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * Add node to head (most recent)
     */
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * Move node to head (mark as recently used)
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    public static void main(String[] args) {
        System.out.println("=== LRU Cache ===\n");
        
        LRUCache cache = new LRUCache(2);
        
        System.out.println("Cache capacity: 2\n");
        
        System.out.println("Operations:");
        System.out.println("1. put(1, 1)    → {1:1}");
        cache.put(1, 1);
        
        System.out.println("2. put(2, 2)    → {2:2, 1:1}");
        cache.put(2, 2);
        
        System.out.println("3. get(1)       → 1  (now 1 most recent)");
        int val = cache.get(1);
        System.out.println("   Returned: " + val + ", Cache: {1:1, 2:2}");
        
        System.out.println("4. put(3, 3)    → {3:3, 1:1} (2 evicted as LRU)");
        cache.put(3, 3);
        
        System.out.println("5. get(2)       → -1  (2 was evicted)");
        val = cache.get(2);
        System.out.println("   Returned: " + val);
        
        System.out.println("6. put(4, 4)    → {4:4, 3:3} (1 evicted as LRU)");
        cache.put(4, 4);
        
        System.out.println("7. get(1)       → -1  (1 was evicted)");
        val = cache.get(1);
        System.out.println("   Returned: " + val);
        
        System.out.println("8. get(3)       → 3");
        val = cache.get(3);
        System.out.println("   Returned: " + val + ", Cache: {3:3, 4:4}");
        
        System.out.println("9. get(4)       → 4");
        val = cache.get(4);
        System.out.println("   Returned: " + val + ", Cache: {4:4, 3:3}");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED TRACE WITH DOUBLY LINKED LIST:");
        System.out.println("-".repeat(70));
        
        cache = new LRUCache(2);
        
        System.out.println("\nInitial state:");
        System.out.println("HEAD ↔ TAIL (empty)");
        
        System.out.println("\n1. put(1, 1)");
        cache.put(1, 1);
        System.out.println("   HEAD ↔ [1:1] ↔ TAIL");
        System.out.println("   HashMap: {1→[1:1]}");
        
        System.out.println("\n2. put(2, 2)");
        cache.put(2, 2);
        System.out.println("   HEAD ↔ [2:2] ↔ [1:1] ↔ TAIL");
        System.out.println("   HashMap: {2→[2:2], 1→[1:1]}");
        System.out.println("   (2 is most recent, at head)");
        
        System.out.println("\n3. get(1) - Access and move to head");
        cache.get(1);
        System.out.println("   HEAD ↔ [1:1] ↔ [2:2] ↔ TAIL");
        System.out.println("   HashMap: {1→[1:1], 2→[2:2]}");
        System.out.println("   (1 is now most recent)");
        
        System.out.println("\n4. put(3, 3) - Capacity exceeded, evict tail");
        cache.put(3, 3);
        System.out.println("   HEAD ↔ [3:3] ↔ [1:1] ↔ TAIL");
        System.out.println("   HashMap: {3→[3:3], 1→[1:1]}");
        System.out.println("   (Evicted key 2, which was at tail.prev)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DATA STRUCTURE DETAILS:");
        System.out.println("-".repeat(70));
        System.out.println("HashMap<Integer, Node>:");
        System.out.println("  - Maps key to Node for O(1) access");
        System.out.println("  - Enables quick update/retrieval");
        
        System.out.println("\nDoubly Linked List:");
        System.out.println("  - Stores nodes in order of usage");
        System.out.println("  - Head: Most recently used");
        System.out.println("  - Tail: Least recently used");
        System.out.println("  - Enables O(1) insertion/deletion anywhere");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("OPERATIONS:");
        System.out.println("-".repeat(70));
        System.out.println("get(key):");
        System.out.println("  1. Check if key in HashMap → O(1)");
        System.out.println("  2. If found: move node to head → O(1)");
        System.out.println("  3. Return value → O(1)");
        System.out.println("  Total: O(1)");
        
        System.out.println("\nput(key, value):");
        System.out.println("  1. If key exists: update value, move to head → O(1)");
        System.out.println("  2. If key doesn't exist:");
        System.out.println("     - Create new node");
        System.out.println("     - Add to HashMap → O(1)");
        System.out.println("     - Add to head of list → O(1)");
        System.out.println("     - If capacity exceeded: remove tail → O(1)");
        System.out.println("  Total: O(1)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. HashMap + DoublyLinkedList = O(1) operations");
        System.out.println("2. Dummy nodes prevent null checks");
        System.out.println("3. Tail.prev always points to LRU node");
        System.out.println("4. Head.next always points to MRU node");
        System.out.println("5. Every access/update moves node to head");
        System.out.println("6. Eviction only happens at tail");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPLEXITY:");
        System.out.println("-".repeat(70));
        System.out.println("Time:  O(1) for get and put");
        System.out.println("Space: O(capacity) for storage");
    }
}
