package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * ASSIGN COOKIES
 *
 * Problem (Greedy but often included in subsequences/DP sets): Given children with greed factors
 * and cookies with sizes, assign cookies to maximize the number of satisfied children. Each child
 * can receive at most one cookie with size >= greed.
 *
 * Approach:
 * Sort children and cookies, then greedy two-pointer: give smallest cookie that satisfies current child.
 *
 * Time: O(n log n + m log m), Space: O(1)
 *
 * Example:
 * greed = [1,2,3], cookies = [1,1] -> result = 1
 */
public class AssignCookies {

    public static int findContentChildren(int[] greed, int[] cookies) {
        Arrays.sort(greed); Arrays.sort(cookies);
        int i = greed.length - 1, j = cookies.length - 1;
        int content = 0;
        while (i >= 0 && j >= 0) {
            if (cookies[j] >= greed[i]) { content++; i--; j--; }
            else i--; // need a smaller child
        }
        return content;
    }

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1,2,3}, new int[]{1,1})); // 1
    }
}
