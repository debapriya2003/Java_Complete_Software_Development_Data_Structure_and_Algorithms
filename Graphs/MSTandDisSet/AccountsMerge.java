package Graphs.MSTandDisSet;

import java.util.*;

/**
 * ACCOUNTS MERGE (LeetCode 721)
 *
 * Problem:
 * Given a list of accounts where each account has a name and emails, merge accounts that share any common
 * email and return merged accounts with sorted emails.
 *
 * Approach:
 * Use DSU to union indexes of accounts when they share an email. Maintain a map email→accountIndex (first seen)
 * to union later accounts with earlier ones. After unions, group emails by representative and build merged results.
 *
 * Complexity: O(totalEmails α(n)) with DSU, plus sorting for output.
 */

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSetUnionByRank dsu = new DisjointSetUnionByRank(n);
        Map<String, Integer> emailToIdx = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (!emailToIdx.containsKey(email)) emailToIdx.put(email, i);
                else dsu.union(i, emailToIdx.get(email));
            }
        }
        Map<Integer, Set<String>> groups = new HashMap<>();
        for (Map.Entry<String,Integer> e : emailToIdx.entrySet()) {
            int rep = dsu.find(e.getValue());
            groups.computeIfAbsent(rep, k -> new TreeSet<>()).add(e.getKey());
        }
        List<List<String>> res = new ArrayList<>();
        for (int rep : groups.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(rep).get(0));
            list.addAll(groups.get(rep));
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        AccountsMerge sol = new AccountsMerge();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        System.out.println(sol.accountsMerge(accounts));
    }
}
