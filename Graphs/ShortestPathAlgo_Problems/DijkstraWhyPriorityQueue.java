package Graphs.ShortestPathAlgo_Problems;

/**
 * Why Priority Queue is used in Dijkstra's Algorithm
 *
 * Short explanation:
 * Dijkstra's algorithm progressively builds shortest path distances from the source. At each
 * step it needs to select the unprocessed vertex with the smallest tentative distance. A priority
 * queue (min-heap) supports extracting the smallest-distance vertex in O(log V) time. Without a
 * PQ you'd need O(V) to find the minimum each time (resulting in O(V^2) overall), which is
 * inefficient for sparse graphs. Using a PQ reduces complexity to O((V+E) log V).
 *
 * Example: PQ helps to immediately get the next vertex with lowest distance (greedy choice) and
 * propagate relaxations efficiently. PQ also allows multiple tentative entries for a vertex; we skip
 * outdated entries when popped (distance mismatch) which is simple and correct.
 */
public class DijkstraWhyPriorityQueue {
    public static void main(String[] args) {
        System.out.println("PriorityQueue (min-heap) is essential to select next vertex with minimal tentative distance efficiently.");
    }
}
