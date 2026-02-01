package heap.intro;
/*
=====================================================================================
CONVERT MIN HEAP TO MAX HEAP
-------------------------------------------------------------------------------------
To convert a Min Heap to a Max Heap:
• We can reuse the same array
• Apply MAX-HEAPIFY from the last non-leaf node to root

This works because heapify ensures the heap property locally and propagates downward.
=====================================================================================
*/

public class ConvertMinHeapToMaxHeap {

    static void convertToMaxHeap(int[] arr) {

        int n = arr.length;

        // Start heapifying from last non-leaf node
        for (int i = (n / 2) - 1; i >= 0; i--) {
            maxHeapify(arr, n, i);
        }
    }

    static void maxHeapify(int[] arr, int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] minHeap = {1, 3, 5, 7, 9, 11};

        convertToMaxHeap(minHeap);

        System.out.print("Converted Max Heap: ");
        for (int num : minHeap) {
            System.out.print(num + " ");
        }
    }
}

