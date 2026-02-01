package recursion_patternwise.all_combo_hard;

public class AndroidUnlockPatterns {

    /*
    =====================================================================================
    PROBLEM: ANDROID UNLOCK PATTERNS
    -------------------------------------------------------------------------------------
    Android unlock patterns are drawn on a 3×3 keypad containing numbers 1 to 9.

    RULES:
    1. Pattern length must be between m and n (inclusive)
    2. Each key can be used only once
    3. A move is INVALID if it skips an unvisited key

    KEYPAD:
    1 2 3
    4 5 6
    7 8 9

    INVALID MOVES EXAMPLES:
    • 1 → 3 requires 2 to be already used
    • 1 → 9 requires 5 to be already used
    • 3 → 7 requires 5 to be already used
    • 7 → 9 requires 8 to be already used

    APPROACH: BACKTRACKING + DFS

    IDEA:
    • Try starting from every key (1–9)
    • Recursively build patterns
    • Use a "skip" matrix to enforce invalid jumps
    • Count valid patterns of length m to n

    Time Complexity  : O(9!)
    Space Complexity : O(9) (recursion + visited array)
    =====================================================================================
    */

    /* ============================ SKIP MATRIX ============================ */

    /*
    skip[i][j] = k means:
    To move from i → j, key k must be visited first.
    If skip[i][j] = 0, move is allowed directly.
    */
    private static final int[][] skip = new int[10][10];

    static {
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = 5;
        skip[3][7] = skip[7][3] = 5;
        skip[4][6] = skip[6][4] = 5;
        skip[2][8] = skip[8][2] = 5;
    }

    /* ============================ MAIN FUNCTION ============================ */

    /*
    =====================================================================================
    FUNCTION: countPatterns
    -------------------------------------------------------------------------------------
    Counts the number of valid unlock patterns of length between m and n.
    =====================================================================================
    */
    public static int countPatterns(int m, int n) {

        boolean[] visited = new boolean[10];
        int count = 0;

        // Symmetry optimization:
        // 1,3,7,9 are symmetric (×4)
        // 2,4,6,8 are symmetric (×4)
        // 5 is unique

        count += dfs(1, 1, m, n, visited) * 4;
        count += dfs(2, 1, m, n, visited) * 4;
        count += dfs(5, 1, m, n, visited);

        return count;
    }

    /* ============================ DFS BACKTRACKING ============================ */

    /*
    =====================================================================================
    FUNCTION: dfs
    -------------------------------------------------------------------------------------
    Recursively builds valid unlock patterns.

    PARAMETERS:
    • current → current key
    • length  → current pattern length
    • visited → tracks used keys

    RETURNS:
    • number of valid patterns from this state
    =====================================================================================
    */
    private static int dfs(int current, int length, int m, int n, boolean[] visited) {

        int count = 0;

        if (length >= m) {
            count++;
        }

        if (length == n) {
            return count;
        }

        visited[current] = true;

        for (int next = 1; next <= 9; next++) {

            int mid = skip[current][next];

            if (!visited[next] && (mid == 0 || visited[mid])) {
                count += dfs(next, length + 1, m, n, visited);
            }
        }

        visited[current] = false;
        return count;
    }

    /* ============================ DRIVER CODE ============================ */

    public static void main(String[] args) {

        System.out.println("=== ANDROID UNLOCK PATTERNS ===");

        System.out.println("m = 1, n = 1 → " + countPatterns(1, 1)); // 9
        System.out.println("m = 1, n = 2 → " + countPatterns(1, 2)); // 65
        System.out.println("m = 2, n = 2 → " + countPatterns(2, 2));
        System.out.println("m = 1, n = 4 → " + countPatterns(1, 4));

        System.out.println("\n=== COUNT BY EXACT LENGTH ===");
        for (int i = 1; i <= 9; i++) {
            System.out.println("Length " + i + " → " + countPatterns(i, i));
        }
    }
}
