package basics;

/****************************************************************************************
 * FILE NAME : Recursion.java
 * PURPOSE   : Understand and implement basic recursion problems in Java
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

public class Recursion {

    public static void main(String[] args) {

        printSomethingNTimes(5);
        printNameNTimes(3, "Java");
        print1ToN(5);
        printNTo1(5);
        sumOfN(5);
        factorial(5);

        int[] arr = {1, 2, 3, 4, 5};
        reverseArray(arr, 0, arr.length - 1);

        System.out.print("\nReversed Array: ");
        for (int x : arr) System.out.print(x + " ");

        checkPalindrome("MADAM", 0);
        // fibonacci(7);
        printFibonacciSeries(7);
    }

    /*
    =====================================================================================
    WHAT IS RECURSION?
    -------------------------------------------------------------------------------------
    Recursion is a programming technique where a function calls itself to solve a problem.
    Instead of solving the entire problem at once, recursion breaks it into smaller sub-
    problems of the same type. Every recursive function must have two parts: a base case,
    which stops the recursion, and a recursive case, which reduces the problem size. Without
    a base case, recursion leads to infinite calls and stack overflow.
    =====================================================================================
    */

    /*
    =====================================================================================
    PROBLEM 1: PRINT SOMETHING N TIMES
    -------------------------------------------------------------------------------------
    This problem demonstrates the simplest form of recursion. The function prints a
    message and then calls itself with a reduced value of N. Each recursive call reduces
    the problem size until the base case (N == 0) is reached. This helps understand how
    recursive calls are stacked and how the function terminates naturally.
    =====================================================================================
    */
    static void printSomethingNTimes(int n) {
        if (n == 0) return; // Base case

        System.out.println("Hello Recursion");
        printSomethingNTimes(n - 1); // Recursive call
    }

    /*
    =====================================================================================
    PROBLEM 2: PRINT NAME N TIMES USING RECURSION
    -------------------------------------------------------------------------------------
    This problem is similar to the previous one but introduces parameter passing. The name
    is passed as an argument and printed during each recursive call. The function keeps
    calling itself while decreasing N until the base case is reached. This reinforces the
    idea that recursion works well with parameters and repeated tasks.
    =====================================================================================
    */
    static void printNameNTimes(int n, String name) {
        if (n == 0) return;

        System.out.println(name);
        printNameNTimes(n - 1, name);
    }

    /*
    =====================================================================================
    PROBLEM 3: PRINT 1 TO N USING RECURSION
    -------------------------------------------------------------------------------------
    To print numbers from 1 to N using recursion, we first make the recursive call and then
    print the value. This ensures that printing happens while the recursive calls return.
    This problem introduces the idea of recursion working during the "backtracking" phase,
    not only during the forward recursive calls.
    =====================================================================================
    */
    static void print1ToN(int n) {
        if (n == 0) return;

        print1ToN(n - 1);
        System.out.print(n + " ");
    }

    /*
    =====================================================================================
    PROBLEM 4: PRINT N TO 1 USING RECURSION
    -------------------------------------------------------------------------------------
    In this case, printing is done before making the recursive call. As a result, numbers
    are printed in descending order. This problem highlights how the position of the print
    statement (before or after recursion) affects the output order.
    =====================================================================================
    */
    static void printNTo1(int n) {
        if (n == 0) return;

        System.out.print(n + " ");
        printNTo1(n - 1);
    }

    /*
    =====================================================================================
    PROBLEM 5: SUM OF FIRST N NUMBERS
    -------------------------------------------------------------------------------------
    This recursive function calculates the sum of the first N natural numbers by adding N
    to the result of sum(N-1). The recursion stops when N becomes zero. This problem shows
    how recursion can replace loops for cumulative calculations and helps in understanding
    mathematical recurrence relations.
    =====================================================================================
    */
    static int sumOfN(int n) {
        if (n == 0) return 0;

        int result = n + sumOfN(n - 1);
        System.out.println("\nSum till " + n + " = " + result);
        return result;
    }

    /*
    =====================================================================================
    PROBLEM 6: FACTORIAL OF N
    -------------------------------------------------------------------------------------
    Factorial of a number N is defined as N × (N-1) × (N-2) … × 1. Using recursion, factorial
    can be directly expressed as N × factorial(N-1). The base case is factorial(0) = 1.
    This is a classic recursion example that closely matches its mathematical definition.
    =====================================================================================
    */
    static int factorial(int n) {
        if (n == 0) return 1;

        int fact = n * factorial(n - 1);
        System.out.println("Factorial till " + n + " = " + fact);
        return fact;
    }

    /*
    =====================================================================================
    PROBLEM 7: REVERSE AN ARRAY USING RECURSION
    -------------------------------------------------------------------------------------
    Reversing an array recursively involves swapping elements from the beginning and end
    and then calling the function for the remaining sub-array. The recursion stops when
    the start index becomes greater than or equal to the end index. This problem helps in
    understanding recursion with multiple parameters.
    =====================================================================================
    */
    static void reverseArray(int[] arr, int start, int end) {
        if (start >= end) return;

        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        reverseArray(arr, start + 1, end - 1);
    }

    /*
    =====================================================================================
    PROBLEM 8: CHECK IF STRING IS PALINDROME USING RECURSION
    -------------------------------------------------------------------------------------
    A string is a palindrome if it reads the same forward and backward. Using recursion,
    we compare the first and last characters and then recursively check the substring in
    between. If any mismatch occurs, the string is not a palindrome. The base case occurs
    when the indices cross each other.
    =====================================================================================
    */
    static void checkPalindrome(String str, int index) {
        int n = str.length();

        if (index >= n / 2) {
            System.out.println("\n" + str + " is a Palindrome");
            return;
        }

        if (str.charAt(index) != str.charAt(n - index - 1)) {
            System.out.println("\n" + str + " is NOT a Palindrome");
            return;
        }

        checkPalindrome(str, index + 1);
    }

    /*
    =====================================================================================
    PROBLEM 9 : FIBONACCI SERIES USING RECURSION (CORRECT WAY)
    -------------------------------------------------------------------------------------
    The Fibonacci series is generated by summing the previous two numbers. The recursive
    function should only compute and return Fibonacci values, not print them. Printing is
    handled separately in a loop. This avoids repeated and incorrect outputs caused by
    multiple recursive calls.
    =====================================================================================
    */

    static int fibonacci(int n) {
        if (n == 0) return 0;   // Base case
        if (n == 1) return 1;   // Base case

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    static void printFibonacciSeries(int n) {
        System.out.print("Fibonacci Series: ");
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

}

