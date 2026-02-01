package Graphs.BFSandDFS;

import java.util.*;

/**
 * Word Ladder II (All Shortest Transformation Sequences)
 *
 * Problem:
 * Find all shortest transformation sequences from beginWord to endWord given a dictionary.
 *
 * Approach:
 * 1) BFS to compute the minimum distance from beginWord to every reachable word while building a
 *    parents map: parents[w] contains nodes that lead to w along shortest paths.
 * 2) Backtrack (DFS) from endWord using the parents map to reconstruct all sequences of minimum length.
 *
 * Example:
 * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Shortest sequences: ["hit","hot","dot","dog","cog"] and ["hit","hot","lot","log","cog"].
 *
 * Notes:
 * - BFS ensures that we only record parents along shortest paths (dist[next] == dist[cur] + 1).
 * - Backtracking then enumerates all combinations of predecessors to list all shortest sequences.
 */

public class WordLadder2 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!dict.contains(endWord)) return res;

        // BFS to build adjacency via predecessors
        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        q.add(beginWord); dist.put(beginWord, 0);
        int L = beginWord.length();
        while (!q.isEmpty()) {
            String cur = q.poll();
            int d = dist.get(cur);
            char[] arr = cur.toCharArray();
            for (int i = 0; i < L; i++) {
                char old = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == old) continue;
                    arr[i] = c;
                    String nxt = new String(arr);
                    if (!dict.contains(nxt)) continue;
                    if (!dist.containsKey(nxt)) {
                        dist.put(nxt, d+1);
                        q.add(nxt);
                    }
                    if (dist.get(nxt) == d+1) {
                        // Record cur as a predecessor of nxt only if nxt is reached at the minimal distance
                        // This ensures parents map encodes only shortest-path parent links.
                        parents.computeIfAbsent(nxt, k -> new ArrayList<>()).add(cur);
                    }
                }
                arr[i] = old;
            }
        }

        if (!dist.containsKey(endWord)) return res;

        // Backtracking phase: reconstruct all shortest paths from endWord to beginWord
        LinkedList<String> path = new LinkedList<>();
        path.add(endWord);
        backtrack(endWord, beginWord, parents, path, res);
        return res;
    }

    private void backtrack(String cur, String begin, Map<String, List<String>> parents, LinkedList<String> path, List<List<String>> res) {
        // If we reached the begin word, reverse the path (currently end->...->begin) and add a copy
        if (cur.equals(begin)) {
            List<String> tmp = new ArrayList<>(path);
            Collections.reverse(tmp);
            res.add(tmp);
            return;
        }
        // Explore all predecessors (multiple predecessors correspond to branching shortest paths)
        List<String> preds = parents.getOrDefault(cur, new ArrayList<>());
        for (String p : preds) {
            path.add(p);                  // add predecessor and continue backtracking
            backtrack(p, begin, parents, path, res);
            path.removeLast();            // undo (backtrack)
        }
    }

    public static void main(String[] args) {
        WordLadder2 sol = new WordLadder2();
        List<List<String>> res = sol.findLadders("hit","cog", Arrays.asList("hot","dot","dog","lot","log","cog"));
        for (List<String> r : res) System.out.println(r);
    }
}
