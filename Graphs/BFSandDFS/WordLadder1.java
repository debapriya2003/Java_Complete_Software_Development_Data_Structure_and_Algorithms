package Graphs.BFSandDFS;

import java.util.*;

/**
 * Word Ladder I
 * Problem: Find the length of the shortest transformation sequence from beginWord to endWord.
 * Approach: BFS over word transformations (single-letter changes). Use a dictionary set and visited set to avoid repeats.
 * Time Complexity: O(N * L * 26) roughly where N is number of words and L is word length.
 * Space Complexity: O(N)
 */

public class WordLadder1 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        // Standard BFS: queue holds words at current level. 'level' represents the number of
        // transformations used (beginWord has level 1).
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        Set<String> vis = new HashSet<>(); vis.add(beginWord);
        int level = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            // Process all words at current transformation distance
            for (int k = 0; k < sz; k++) {
                String w = q.poll();
                if (w.equals(endWord)) return level; // found shortest path to endWord
                char[] arr = w.toCharArray();
                // Try changing each position to every lowercase letter
                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        arr[i] = c;
                        String nw = new String(arr);
                        // If the mutated word exists in dictionary and wasn't visited, enqueue it
                        if (dict.contains(nw) && !vis.contains(nw)) {
                            vis.add(nw);
                            q.add(nw);
                        }
                    }
                    arr[i] = old; // restore before moving to next position
                }
            }
            level++; // increase transformation distance after finishing current level
        }
        return 0;
    }

    public static void main(String[] args) {
        WordLadder1 sol = new WordLadder1();
        System.out.println(sol.ladderLength("hit","cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}
