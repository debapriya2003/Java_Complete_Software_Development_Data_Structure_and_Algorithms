package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Alien Dictionary — Comprehensive Explanation & Example Code
 *
 * Problem restated: Given an array of words sorted according to an unknown character order
 * (alien dictionary), determine a valid ordering of characters. This is a precedence inference
 * problem: letters that appear earlier in the order must come before those that depend on them.
 *
 * Approach overview:
 * 1) Initialize nodes for all characters appearing in the words and create adjacency sets.
 * 2) For each pair of adjacent words, find the first index k where the characters differ. This
 *    gives an ordering edge: a.charAt(k) -> b.charAt(k). If no differing char found and a is longer
 *    than b, the input is invalid (prefix problem), return "".
 * 3) After building the directed graph, run Kahn's algorithm (BFS) using indegrees to produce a
 *    topological order. If the resulting ordering length != number of unique characters, a cycle
 *    exists and we return "".
 *
 * Short code sketch:
 *    for words: register chars in indeg & adj
 *    for adjacent pairs: if (a[k] != b[k]) add edge a[k] -> b[k] and indeg[b[k]]++
 *    run Kahn: queue indeg==0, pop append, decrement neighbors
 *
 * Complexity: O(totalLetters + alphabetSize + edges), Space O(alphabetSize + edges). This method
 * ensures we derive a consistent order or detect invalid inputs efficiently.
 */

public class AlienDictionary {

    public String alienOrder(String[] words) {
        // Build graph for lowercase letters present in words
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indeg = new HashMap<>();
        for (String w : words) for (char c : w.toCharArray()) {
            adj.putIfAbsent(c, new HashSet<>());
            indeg.putIfAbsent(c, 0);
        }
        // Build edges by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String a = words[i], b = words[i+1];
            int len = Math.min(a.length(), b.length());
            int k = 0;
            while (k < len && a.charAt(k) == b.charAt(k)) k++;
            if (k < len) {
                char u = a.charAt(k), v = b.charAt(k);
                if (!adj.get(u).contains(v)) {
                    adj.get(u).add(v);
                    indeg.put(v, indeg.get(v) + 1);
                }
            } else {
                // If b is a prefix of a, ordering is invalid (e.g., "abc" before "ab")
                if (a.length() > b.length()) return "";
            }
        }
        // Kahn's algorithm
        Queue<Character> q = new LinkedList<>();
        for (char c : indeg.keySet()) if (indeg.get(c) == 0) q.add(c);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll(); sb.append(c);
            for (char nei : adj.getOrDefault(c, Collections.emptySet())) {
                indeg.put(nei, indeg.get(nei) - 1);
                if (indeg.get(nei) == 0) q.add(nei);
            }
        }
        // If result contains all characters, return order
        if (sb.length() != indeg.size()) return "";
        return sb.toString();
    }

    public static void main(String[] args) {
        AlienDictionary sol = new AlienDictionary();
        String[] words = {"wrt","wrf","er","ett","rftt"};
        System.out.println("Alien order: " + sol.alienOrder(words));
    }
}
