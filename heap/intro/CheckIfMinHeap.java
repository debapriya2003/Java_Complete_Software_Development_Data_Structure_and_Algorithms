package heap.intro;

/*
=====================================================================================
CHECK IF AN ARRAY REPRESENTS A MIN HEAP
-------------------------------------------------------------------------------------
An array represents a Min Heap if:
• For every index i:
    arr[i] ≤ arr[left child]
    arr[i] ≤ arr[right child]

We only need to check NON-LEAF nodes.
Last non-leaf node index = (n/2) - 1
=====================================================================================
*/

public class CheckIfMinHeap {

    public static boolean isMinHeap(int[] arr) {

        int n = arr.length;

        // Check only non-leaf nodes
        for (int i = 0; i <= (n / 2) - 1; i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] > arr[left])
                return false;

            if (right < n && arr[i] > arr[right])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int[] heap1 = {1, 3, 5, 7, 9, 11};
        int[] heap2 = {10, 5, 3, 2};

        System.out.println("Is Min Heap (heap1): " + isMinHeap(heap1));
        System.out.println("Is Min Heap (heap2): " + isMinHeap(heap2));
    }
}

