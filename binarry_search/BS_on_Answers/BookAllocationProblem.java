package binarry_search.BS_on_Answers;

public class BookAllocationProblem {

    /*
    =====================================================================================
    PROBLEM: BOOK ALLOCATION PROBLEM
    -------------------------------------------------------------------------------------
    Given n books and m students, each book has pages. Allocate books to students such 
    that each student gets consecutive books in order and the maximum number of pages 
    assigned to any student is minimized. No student should be left without books. Use 
    binary search on the maximum pages a student can read. For each candidate maximum, 
    check if we can allocate books to all m students such that no student exceeds this limit.

    Time Complexity: O(n * log(sum))
    Space Complexity: O(1)

    Example:
    Input:  pages=[12,34,67,90], m=2
    Output: 113 (student 1 gets 12+34+67=113, student 2 gets 90)
    =====================================================================================
    */
    
    /**
     * Finds the minimum maximum pages any student reads when allocating books to m students.
     * Binary searches on the maximum pages from the single largest book to total sum.
     * For each candidate maximum, greedily checks if m students can read all books with
     * no student exceeding that page limit. Each student reads consecutive books starting
     * where previous student ended. Returns the minimum possible maximum pages.
     * 
     * @param pages array of book page counts
     * @param m number of students
     * @return minimum possible maximum pages assigned to any student
     */
    public static int allocateBooks(int[] pages, int m) {
        if (m > pages.length) return -1;
        
        int left = 0, right = 0;
        
        for (int page : pages) {
            left = Math.max(left, page);
            right += page;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canAllocate(pages, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    
    /**
     * Helper function to check if m students can read all books with max pages limit.
     * Greedily allocates consecutive books to each student until adding next book
     * would exceed maxPages. Creates new student when needed. Returns true if all
     * books are allocated to m or fewer students within the page constraint.
     * 
     * @param pages array of book page counts
     * @param m number of students
     * @param maxPages maximum pages any student can read
     * @return true if m students can read all books within maxPages each
     */
    private static boolean canAllocate(int[] pages, int m, int maxPages) {
        int students = 1;
        int currentPages = 0;
        
        for (int page : pages) {
            if (currentPages + page <= maxPages) {
                currentPages += page;
            } else {
                students++;
                currentPages = page;
            }
        }
        return students <= m;
    }
    
    public static void main(String[] args) {
        System.out.println("Allocate [12,34,67,90], m=2: " + allocateBooks(new int[]{12,34,67,90}, 2));      // 113
        System.out.println("Allocate [12,34,67,90], m=3: " + allocateBooks(new int[]{12,34,67,90}, 3));      // 90
        System.out.println("Allocate [5,17,100,11], m=2: " + allocateBooks(new int[]{5,17,100,11}, 2));      // 117
    }
}
