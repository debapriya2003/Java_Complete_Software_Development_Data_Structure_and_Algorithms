package basics;
/****************************************************************************************
 * FILE NAME : collections.java
 * PURPOSE   : Complete explanation and demonstration of Java Collections Framework
 * LEVEL     : Academic + Practical (Exam & Interview ready)
 ****************************************************************************************/

import java.util.*;

/*
=========================================================================================
SECTION 1: WHY COLLECTIONS IN JAVA?
-----------------------------------------------------------------------------------------
Arrays are the first data structure most programmers learn. While they are simple and
efficient for fixed-size storage, they suffer from serious limitations. An array cannot
grow or shrink dynamically, which makes it unsuitable when the number of elements is not
known in advance. Arrays also lack built-in methods for common tasks such as searching,
sorting, insertion, and deletion.

The Java Collections Framework (JCF) solves these problems by providing dynamic, reusable,
and well-tested data structures. These structures automatically resize, provide rich APIs,
and follow a consistent design philosophy. This reduces bugs, improves productivity, and
makes code more readable and maintainable.
=========================================================================================
*/

public class collections {

    public static void main(String[] args) {

        listDemo();
        setDemo();
        mapDemo();
        queueDemo();
        genericsDemo();
        collectionsUtilityDemo();
    }

    /*
    =====================================================================================
    SECTION 2: THE CONCEPT OF A FRAMEWORK
    -------------------------------------------------------------------------------------
    The Java Collections Framework is not a random set of classes. It is a well-organized
    hierarchy of interfaces and classes. At the top is the Iterable interface, which allows
    objects to be traversed using enhanced for-loops. Below it lies the Collection interface,
    defining common behaviors like add, remove, size, and clear.

    The framework then branches into List, Set, and Queue interfaces, each designed for
    specific use cases. Separately, the Map interface handles key-value associations. This
    layered design ensures flexibility, extensibility, and consistency across collections.
    =====================================================================================
    */

    /*
    =====================================================================================
    SECTION 3: LIST INTERFACE
    -------------------------------------------------------------------------------------
    A List is an ordered collection that allows duplicate elements and provides index-based
    access. Lists are ideal when the order of insertion matters or when elements need to be
    accessed frequently by position. The most common implementation is ArrayList, which is
    backed by a dynamic array and offers fast random access.

    LinkedList is another List implementation that uses a doubly linked list internally,
    making insertions and deletions efficient but random access slower. Vector is synchronized
    and thread-safe but is largely obsolete in modern Java development.
    =====================================================================================
    */
    static void listDemo() {
        System.out.println("\n--- LIST DEMO ---");

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Java"); // duplicates allowed

        System.out.println("List elements: " + list);
        System.out.println("Element at index 1: " + list.get(1));
    }

    /*
    =====================================================================================
    SECTION 4: SET INTERFACE
    -------------------------------------------------------------------------------------
    A Set represents a collection of unique elements, meaning duplicates are not allowed.
    This makes sets extremely useful for tasks like removing duplicate entries, membership
    testing, and maintaining distinct values. Unlike lists, sets do not provide index-based
    access.

    HashSet stores elements using hashing and does not guarantee any order. LinkedHashSet
    preserves insertion order, while TreeSet stores elements in sorted order using a
    self-balancing binary search tree. The choice depends on ordering and performance needs.
    =====================================================================================
    */
    static void setDemo() {
        System.out.println("\n--- SET DEMO ---");

        Set<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(10); // duplicate ignored

        System.out.println("Set elements: " + set);
    }

    /*
    =====================================================================================
    SECTION 5: MAP INTERFACE
    -------------------------------------------------------------------------------------
    A Map stores data in key-value pairs, where each key is unique and maps to exactly one
    value. Unlike List and Set, Map does not extend the Collection interface, but it is an
    integral part of the Collections Framework.

    HashMap provides fast access with no ordering guarantee. LinkedHashMap preserves insertion
    order, making it useful for predictable iteration. TreeMap stores keys in sorted order
    and is useful when ordered traversal is required. Maps are ideal for lookup tables,
    dictionaries, and frequency counting.
    =====================================================================================
    */
    static void mapDemo() {
        System.out.println("\n--- MAP DEMO ---");

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 5);

        System.out.println("Apple count: " + map.get("Apple"));
        System.out.println("All keys: " + map.keySet());
    }

    /*
    =====================================================================================
    SECTION 6: QUEUE AND DEQUE
    -------------------------------------------------------------------------------------
    A Queue follows the FIFO (First-In-First-Out) principle and is commonly used in task
    scheduling, buffering, and breadth-first search algorithms. Java provides Queue and
    Deque interfaces for such use cases.

    ArrayDeque is a high-performance double-ended queue that allows insertion and removal
    from both ends. PriorityQueue orders elements based on priority rather than insertion
    order. Queues typically discourage null values to avoid ambiguity during processing.
    =====================================================================================
    */
    static void queueDemo() {
        System.out.println("\n--- QUEUE DEMO ---");

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println("Queue poll: " + queue.poll());
        System.out.println("Queue peek: " + queue.peek());
    }

    /*
    =====================================================================================
    SECTION 7: GENERICS AND TYPE SAFETY
    -------------------------------------------------------------------------------------
    Generics allow collections to store a specific type of object, ensuring compile-time
    type safety. Before generics, collections stored objects as Object references, requiring
    explicit casting and increasing the risk of runtime errors.

    With generics, incorrect data insertion is prevented at compile time, making code safer,
    cleaner, and more readable. Generics also eliminate unnecessary type casting, improving
    maintainability and reducing bugs in large-scale applications.
    =====================================================================================
    */
    static void genericsDemo() {
        System.out.println("\n--- GENERICS DEMO ---");

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");

        // names.add(10); // Compile-time error

        System.out.println("Names: " + names);
    }

    /*
    =====================================================================================
    SECTION 8: COLLECTIONS UTILITY CLASS
    -------------------------------------------------------------------------------------
    The Collections class is a utility class that provides static helper methods for working
    with collections. These include sorting, reversing, shuffling, searching, and creating
    unmodifiable collections.

    Using these methods reduces boilerplate code and improves readability. For example,
    sorting a list manually would require complex logic, but Collections.sort() performs
    the same task efficiently and reliably. This encourages developers to focus on business
    logic rather than low-level implementation details.
    =====================================================================================
    */
    static void collectionsUtilityDemo() {
        System.out.println("\n--- COLLECTIONS UTILITY DEMO ---");

        List<Integer> nums = Arrays.asList(4, 1, 3, 2);
        Collections.sort(nums);
        System.out.println("Sorted: " + nums);

        Collections.reverse(nums);
        System.out.println("Reversed: " + nums);
    }
}

/*
=========================================================================================
SUMMARY TABLE (REFERENCE)
-----------------------------------------------------------------------------------------
Interface     | Implementations                     | Order            | Duplicates
-----------------------------------------------------------------------------------------
List          | ArrayList, LinkedList, Vector       | Maintained       | Allowed
Set           | HashSet, LinkedHashSet, TreeSet     | No / Sorted      | Not Allowed
Queue/Deque   | ArrayDeque, PriorityQueue           | FIFO / Priority  | Allowed
Map           | HashMap, LinkedHashMap, TreeMap     | Key-based        | Keys unique
-----------------------------------------------------------------------------------------
Performance Notes:
- ArrayList: O(1) access, O(n) middle insert
- HashSet/HashMap: O(1) average operations
- TreeSet/TreeMap: O(log n) operations
=========================================================================================
*/
