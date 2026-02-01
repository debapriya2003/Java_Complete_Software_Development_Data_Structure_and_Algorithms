package Graphs.BFSandDFS;

/**
 * Flood Fill
 *
 * Problem:
 * Given an image represented by a 2D array and a starting pixel (sr,sc), replace the color
 * of the connected region containing (sr,sc) with newColor. Connectivity is 4-directional.
 *
 * Approach:
 * - Use DFS to visit all cells in the connected region that have the original color and change them to newColor.
 * - Early exit if newColor equals the original color to avoid infinite loops.
 *
 * Example:
 * image = [[1,1,1],[1,1,0],[1,0,1]], sr=1, sc=1, newColor=2
 * Result: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * Complexity: Time O(n*m), Space O(n*m) recursion worst-case.
 */

public class FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int old = image[sr][sc];
        if (old == newColor) return image;
        dfs(image, sr, sc, old, newColor);
        return image;
    }

    private void dfs(int[][] img, int i, int j, int old, int nc) {
        int n = img.length, m = img[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) return;
        if (img[i][j] != old) return;
        img[i][j] = nc;
        dfs(img, i+1, j, old, nc);
        dfs(img, i-1, j, old, nc);
        dfs(img, i, j+1, old, nc);
        dfs(img, i, j-1, old, nc);
    }

    public static void main(String[] args) {
        FloodFill sol = new FloodFill();
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] res = sol.floodFill(image, 1,1,2);
        for (int[] row : res) System.out.println(java.util.Arrays.toString(row));
    }
}
