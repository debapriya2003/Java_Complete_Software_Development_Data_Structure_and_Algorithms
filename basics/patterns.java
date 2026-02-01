package basics;

/************************************************************
 * FILE NAME : Patterns.java
 * PURPOSE   : Practice and understand pattern printing
 * LANGUAGE  : Java
 ************************************************************/

public class patterns {

    public static void main(String[] args) {

        pattern1();
        pattern2();
        pattern3();
        pattern4();
        pattern5();
        pattern6();
        pattern7();
        pattern8();
        pattern9();
        pattern10();
        pattern11();
        pattern12();
        pattern13();
        pattern14();
        pattern15();
        pattern16();
        pattern17();
        pattern18();
        pattern19();
        pattern20();
        pattern21();
        pattern22();

    }

    /*
    =========================================================
    PATTERN 1
    ---------------------------------------------------------
    *****
    *****
    *****
    *****
    *****
    ---------------------------------------------------------
    Logic:
    - Outer loop controls rows
    - Inner loop prints 5 stars in each row
    =========================================================
    */
    static void pattern1() {
        System.out.println("\nPattern 1:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 2
    ---------------------------------------------------------
    *
    **
    ***
    ****
    *****
    ---------------------------------------------------------
    Logic:
    - Row number decides number of stars
    =========================================================
    */
    static void pattern2() {
        System.out.println("\nPattern 2:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 3
    ---------------------------------------------------------
    1
    12
    123
    1234
    12345
    ---------------------------------------------------------
    Logic:
    - Print numbers from 1 to row index
    =========================================================
    */
    static void pattern3() {
        System.out.println("\nPattern 3:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 4
    ---------------------------------------------------------
    1
    22
    333
    4444
    55555
    ---------------------------------------------------------
    Logic:
    - Print row number, row times
    =========================================================
    */
    static void pattern4() {
        System.out.println("\nPattern 4:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 5
    ---------------------------------------------------------
    *****
    ****
    ***
    **
    *
    ---------------------------------------------------------
    Logic:
    - Stars decrease with each row
    =========================================================
    */
    static void pattern5() {
        System.out.println("\nPattern 5:");
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 6
    ---------------------------------------------------------
    12345
    1234
    123
    12
    1
    ---------------------------------------------------------
    Logic:
    - Numbers printed up to decreasing limit
    =========================================================
    */
    static void pattern6() {
        System.out.println("\nPattern 6:");
        for (int i = 5; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 7
    ---------------------------------------------------------
        *
       ***
      *****
     *******
    ---------------------------------------------------------
    Logic:
    - Spaces = n - row
    - Stars  = 2*row - 1
    =========================================================
    */
    static void pattern7() {
        System.out.println("\nPattern 7:");
        for (int i = 1; i <= 4; i++) {
            for (int s = 1; s <= 4 - i; s++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 8
    ---------------------------------------------------------
    *********
     *******
      *****
       ***
        *
    ---------------------------------------------------------
    Logic:
    - Reverse pyramid
    =========================================================
    */
    static void pattern8() {
        System.out.println("\nPattern 8:");
        for (int i = 4; i >= 1; i--) {
            for (int s = 1; s <= 4 - i; s++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 9 (Diamond)
    ---------------------------------------------------------
        *
       ***
      *****
     *******
      *****
       ***
        *
    ---------------------------------------------------------
    Logic:
    - Combine pattern 7 and 8
    =========================================================
    */
    static void pattern9() {
        System.out.println("\nPattern 9:");
        for (int i = 1; i <= 4; i++) {
            for (int s = 1; s <= 4 - i; s++) System.out.print(" ");
            for (int j = 1; j <= 2 * i - 1; j++) System.out.print("*");
            System.out.println();
        }
        for (int i = 3; i >= 1; i--) {
            for (int s = 1; s <= 4 - i; s++) System.out.print(" ");
            for (int j = 1; j <= 2 * i - 1; j++) System.out.print("*");
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 10
    ---------------------------------------------------------
    *
    **
    ***
    ****
    *****
    ****
    ***
    **
    *
    ---------------------------------------------------------
    Logic:
    - Increasing triangle + decreasing triangle
    =========================================================
    */
    static void pattern10() {
        System.out.println("\nPattern 10:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        for (int i = 4; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
    }

        /*
    =========================================================
    PATTERN 11 (Binary Pattern)
    ---------------------------------------------------------
    1
    0 1
    1 0 1
    0 1 0 1
    1 0 1 0 1
    ---------------------------------------------------------
    Logic:
    - If (row + column) is even → print 1
    - Else → print 0
    =========================================================
    */
    static void pattern11() {
        System.out.println("\nPattern 11:");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                if ((i + j) % 2 == 0)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 12
    ---------------------------------------------------------
    1        1
    12      21
    123    321
    1234  4321
    12344321
    ---------------------------------------------------------
    Logic:
    - Left side increasing numbers
    - Middle spaces decrease
    - Right side decreasing numbers
    =========================================================
    */
    static void pattern12() {
        System.out.println("\nPattern 12:");
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) System.out.print(j);
            for (int s = 1; s <= 2 * (n - i); s++) System.out.print(" ");
            for (int j = i; j >= 1; j--) System.out.print(j);
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 13 (Number Continuation)
    ---------------------------------------------------------
    1
    2 3
    4 5 6
    7 8 9 10
    11 12 13 14 15
    ---------------------------------------------------------
    Logic:
    - Use a counter variable
    - Increment after every print
    =========================================================
    */
    static void pattern13() {
        System.out.println("\nPattern 13:");
        int num = 1;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 14 (Alphabet Triangle)
    ---------------------------------------------------------
    A
    AB
    ABC
    ABCD
    ABCDE
    ---------------------------------------------------------
    Logic:
    - Print characters using (char) conversion
    =========================================================
    */
    static void pattern14() {
        System.out.println("\nPattern 14:");
        for (int i = 1; i <= 5; i++) {
            char ch = 'A';
            for (int j = 1; j <= i; j++) {
                System.out.print(ch);
                ch++;
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 15 (Reverse Alphabet Triangle)
    ---------------------------------------------------------
    ABCDE
    ABCD
    ABC
    AB
    A
    ---------------------------------------------------------
    Logic:
    - Reduce character count each row
    =========================================================
    */
    static void pattern15() {
        System.out.println("\nPattern 15:");
        for (int i = 5; i >= 1; i--) {
            char ch = 'A';
            for (int j = 1; j <= i; j++) {
                System.out.print(ch);
                ch++;
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 16 (Repeated Alphabet)
    ---------------------------------------------------------
    A
    BB
    CCC
    DDDD
    EEEEE
    ---------------------------------------------------------
    Logic:
    - Same character repeated row times
    =========================================================
    */
    static void pattern16() {
        System.out.println("\nPattern 16:");
        char ch = 'A';
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(ch);
            }
            ch++;
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 17 (Alphabet Palindrome)
    ---------------------------------------------------------
    A
    ABA
    ABCBA
    ABCDCBA
    ---------------------------------------------------------
    Logic:
    - First half increasing letters
    - Second half decreasing letters
    =========================================================
    */
    static void pattern17() {
        System.out.println("\nPattern 17:");
        for (int i = 1; i <= 4; i++) {
            char ch = 'A';
            for (int j = 1; j <= i; j++) {
                System.out.print(ch++);
            }
            ch -= 2;
            for (int j = 1; j < i; j++) {
                System.out.print(ch--);
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 18 (Alphabet Pyramid Shifted)
    ---------------------------------------------------------
    E
    D E
    C D E
    B C D E
    A B C D E
    ---------------------------------------------------------
    Logic:
    - Start character decreases each row
    =========================================================
    */
    static void pattern18() {
        System.out.println("\nPattern 18:");
        for (int i = 1; i <= 5; i++) {
            char ch = (char) ('E' - i + 1);
            for (int j = 1; j <= i; j++) {
                System.out.print(ch + " ");
                ch++;
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 19 (Hollow Square)
    ---------------------------------------------------------
    *********
    **** ****
    ***   ***
    **     **
    *       *
    **     **
    ***   ***
    **** ****
    *********
    ---------------------------------------------------------
    Logic:
    - Stars on boundary
    - Spaces inside
    =========================================================
    */
    static void pattern19() {
        System.out.println("\nPattern 19:");
        int n = 5;
        for (int i = 1; i <= 2 * n - 1; i++) {
            for (int j = 1; j <= 2 * n - 1; j++) {
                if (i == 1 || i == 2 * n - 1 || j == 1 || j == 2 * n - 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 20 (Butterfly Pattern)
    ---------------------------------------------------------
    *        *
    **      **
    ***    ***
    ****  ****
    **********
    ****  ****
    ***    ***
    **      **
    *        *
    ---------------------------------------------------------
    Logic:
    - Upper and lower symmetric triangles
    =========================================================
    */
    static void pattern20() {
        System.out.println("\nPattern 20:");
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            for (int s = 1; s <= 2 * (n - i); s++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            for (int s = 1; s <= 2 * (n - i); s++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
    }

        /*
    =========================================================
    PATTERN 21 (Hollow Square)
    ---------------------------------------------------------
    ****
    *  *
    *  *
    ****
    ---------------------------------------------------------
    Logic:
    - Stars on the boundary (first row, last row,
      first column, last column)
    - Spaces inside
    =========================================================
    */
    static void pattern21() {
        System.out.println("\nPattern 21:");
        int n = 4;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                // Boundary condition
                if (i == 1 || i == n || j == 1 || j == n)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    /*
    =========================================================
    PATTERN 22 (Concentric Number Square)
    ---------------------------------------------------------
    4 4 4 4 4 4 4
    4 3 3 3 3 3 4
    4 3 2 2 2 3 4
    4 3 2 1 2 3 4
    4 3 2 2 2 3 4
    4 3 3 3 3 3 4
    4 4 4 4 4 4 4
    ---------------------------------------------------------
    Logic:
    - Find minimum distance from all four borders
    - Value printed = n - minimumDistance
    =========================================================
    */
    static void pattern22() {
        System.out.println("\nPattern 22:");
        int n = 4;                 // Maximum number
        int size = 2 * n - 1;      // Total rows & columns

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int top = i;
                int left = j;
                int right = size - 1 - j;
                int bottom = size - 1 - i;

                int minDistance = Math.min(
                        Math.min(top, bottom),
                        Math.min(left, right)
                );

                System.out.print((n - minDistance) + " ");
            }
            System.out.println();
        }
    }


}
