package heap.intro;

/*
=====================================================================================
MIN HEAP AND MAX HEAP IMPLEMENTATION (USING ARRAY)
-------------------------------------------------------------------------------------
A Binary Heap is a complete binary tree stored using an array.

Min Heap Property:
• Parent ≤ Children

Max Heap Property:
• Parent ≥ Children

Index relationships:
• Parent index = (i - 1) / 2
• Left child   = 2*i + 1
• Right child  = 2*i + 2
=====================================================================================
*/

public class MinHeapAndMaxHeapImplementation {

    static class MinHeap {

        int[] heap;
        int size;

        MinHeap(int capacity) {
            heap = new int[capacity];
            size = 0;
        }

        void insert(int value) {
            heap[size] = value;
            int i = size;
            size++;

            // Heapify-up
            while (i > 0 && heap[(i - 1) / 2] > heap[i]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        int extractMin() {
            int min = heap[0];
            heap[0] = heap[size - 1];
            size--;
            heapify(0);
            return min;
        }

        void heapify(int i) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap[left] < heap[smallest])
                smallest = left;
            if (right < size && heap[right] < heap[smallest])
                smallest = right;

            if (smallest != i) {
                swap(i, smallest);
                heapify(smallest);
            }
        }

        void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    public static void main(String[] args) {

        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(20);
        minHeap.insert(2);

        System.out.println("Extract Min: " + minHeap.extractMin());
        System.out.println("Extract Min: " + minHeap.extractMin());
    }
}
