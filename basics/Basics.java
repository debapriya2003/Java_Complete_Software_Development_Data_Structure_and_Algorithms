package basics;
// ************************************************************
//  * FILE NAME : Basics.java
//  * PURPOSE   : Learn programming fundamentals using Java
//  * LEVEL     : Beginner (Concepts explained in comments)
//  ************************************************************/

import java.util.Scanner; // Scanner is used for user input

public class Basics {

    /*
    =========================================================
    1. WHAT IS A PROGRAM?
    ---------------------------------------------------------
    A program is a set of instructions given to a computer
    to solve a specific problem.

    Every program follows this model:
        INPUT  → PROCESSING → OUTPUT
    =========================================================
    */

    public static void main(String[] args) {

        /*
        =====================================================
        2. RESOURCE & PROBLEM
        -----------------------------------------------------
        Resource : Java Programming Language
        Problem  : Take two numbers from the user and
                   display their sum.
        =====================================================
        */

        /*
        =====================================================
        3. USER INPUT / OUTPUT
        -----------------------------------------------------
        In Java, we use the Scanner class to take input
        from the user through the keyboard.
        =====================================================
        */

        Scanner sc = new Scanner(System.in); // Creating Scanner object

        System.out.print("Enter first number: ");
        int a = sc.nextInt(); // Taking integer input

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        int sum = a + b; // Processing

        System.out.println("Sum = " + sum); // Output


        /*
        =====================================================
        4. DATA TYPES
        -----------------------------------------------------
        Data types define what kind of data a variable stores.
        =====================================================
        */

        int age = 20;            // Integer numbers
        double price = 99.99;    // Decimal numbers
        char grade = 'A';        // Single character
        boolean isPassed = true; // true or false
        String name = "Debapriya"; // Sequence of characters

        System.out.println("\nData Types Example:");
        System.out.println(age + ", " + price + ", " + grade + ", " + isPassed + ", " + name);


        /*
        =====================================================
        5. IF-ELSE STATEMENTS
        -----------------------------------------------------
        Used to make decisions based on conditions.
        =====================================================
        */

        if (age >= 18) {
            System.out.println("Eligible to vote");
        } else {
            System.out.println("Not eligible to vote");
        }


        /*
        =====================================================
        6. SWITCH STATEMENT
        -----------------------------------------------------
        Used when multiple fixed choices exist.
        =====================================================
        */

        int day = 3;

        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            default:
                System.out.println("Invalid day");
        }


        /*
        =====================================================
        7. WHAT ARE STRINGS?
        -----------------------------------------------------
        A String is a sequence of characters.
        Strings are objects in Java.
        =====================================================
        */

        String message = "Hello Java";

        System.out.println("\nString length: " + message.length());
        System.out.println("Uppercase: " + message.toUpperCase());
        System.out.println("Character at index 1: " + message.charAt(1));


        /*
        =====================================================
        8. FOR LOOP
        -----------------------------------------------------
        Used when the number of iterations is known.
        =====================================================
        */

        System.out.println("\nFor Loop Example:");
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }


        /*
        =====================================================
        9. WHILE LOOP
        -----------------------------------------------------
        Used when the number of iterations is unknown.
        =====================================================
        */

        System.out.println("\nWhile Loop Example:");
        int count = 1;

        while (count <= 5) {
            System.out.println("count = " + count);
            count++;
        }


        /*
        =====================================================
        10. FUNCTIONS (METHODS)
        -----------------------------------------------------
        A function is a block of reusable code.
        Java supports:
        - Call by Value (default)
        =====================================================
        */

        int x = 10;
        changeValue(x);
        System.out.println("\nAfter function call, x = " + x);
        // Value does NOT change because Java uses call by value


        /*
        =====================================================
        11. CALL BY VALUE VS REFERENCE (IMPORTANT)
        -----------------------------------------------------
        - Primitive types → Call by Value
        - Objects/Arrays  → Reference passed (address)
        =====================================================
        */

        int[] arr = {1, 2, 3};
        modifyArray(arr);

        System.out.println("Modified Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }


        /*
        =====================================================
        12. TIME COMPLEXITY (BASICS)
        -----------------------------------------------------
        Time complexity measures how execution time
        grows with input size.
        =====================================================
        */

        /*
        Example:
        for (int i = 1; i <= n; i++) → O(n)
        Nested loop (n x n)          → O(n²)
        Constant operations         → O(1)

        We learn basics first,
        detailed analysis comes later.
        */

        sc.close(); // Closing Scanner
    }

    /*
    =====================================================
    FUNCTION: changeValue
    -----------------------------------------------------
    Demonstrates call by value.
    =====================================================
    */
    static void changeValue(int num) {
        num = num + 5;
    }

    /*
    =====================================================
    FUNCTION: modifyArray
    -----------------------------------------------------
    Demonstrates reference behavior with arrays.
    =====================================================
    */
    static void modifyArray(int[] a) {
        a[0] = 99;
    }
}
