package basics;

/****************************************************************************************
 * FILE NAME : Maths.java
 * PURPOSE   : Learn and implement basic mathematical problems in Java
 * LEVEL     : Beginner (DSA & Exam Oriented)
 ****************************************************************************************/

public class Maths {

    public static void main(String[] args) {

        int num = 153;

        countDigits(num);
        reverseNumber(num);
        checkPalindrome(121);
        gcdOrHcf(36, 60);
        checkArmstrong(num);
        printDivisors(36);
        checkPrime(29);
    }

    /*
    =====================================================================================
    PROBLEM 1: COUNT DIGITS IN A NUMBER
    -------------------------------------------------------------------------------------
    Counting digits means determining how many individual digits are present in a given
    number. This is done by repeatedly dividing the number by 10 until it becomes zero.
    Each division removes the last digit. The number of such divisions gives the total
    count of digits. This problem helps in understanding integer division and loop control,
    which are fundamental concepts in programming and number manipulation.
    =====================================================================================
    */
    static void countDigits(int n) {
        int count = 0;
        int temp = n;

        while (temp > 0) {
            count++;
            temp /= 10;
        }

        System.out.println("Digit count of " + n + " = " + count);
    }

    /*
    =====================================================================================
    PROBLEM 2: REVERSE A NUMBER
    -------------------------------------------------------------------------------------
    Reversing a number involves extracting digits one by one from the end and constructing
    a new number in reverse order. This is achieved using the modulo operator to get the
    last digit and integer division to remove it. This problem is important because it
    teaches digit extraction, accumulation logic, and loop-based number transformation.
    =====================================================================================
    */
    static void reverseNumber(int n) {
        int reverse = 0;
        int temp = n;

        while (temp > 0) {
            int digit = temp % 10;
            reverse = reverse * 10 + digit;
            temp /= 10;
        }

        System.out.println("Reverse of " + n + " = " + reverse);
    }

    /*
    =====================================================================================
    PROBLEM 3: CHECK PALINDROME NUMBER
    -------------------------------------------------------------------------------------
    A palindrome number is a number that remains the same when reversed. To check this,
    we reverse the number and compare it with the original value. If both are equal, the
    number is a palindrome. This problem combines the logic of reversing a number with
    conditional checking, reinforcing problem-solving and logical reasoning skills.
    =====================================================================================
    */
    static void checkPalindrome(int n) {
        int original = n;
        int reverse = 0;

        while (n > 0) {
            reverse = reverse * 10 + (n % 10);
            n /= 10;
        }

        if (original == reverse)
            System.out.println(original + " is a Palindrome");
        else
            System.out.println(original + " is NOT a Palindrome");
    }

    /*
    =====================================================================================
    PROBLEM 4: GCD / HCF OF TWO NUMBERS
    -------------------------------------------------------------------------------------
    The Greatest Common Divisor (GCD) or Highest Common Factor (HCF) of two numbers is the
    largest number that divides both without leaving a remainder. The Euclidean Algorithm
    efficiently finds the GCD by repeatedly replacing the larger number with the remainder
    of dividing the two numbers until the remainder becomes zero.
    =====================================================================================
    */
    static void gcdOrHcf(int a, int b) {
        int x = a, y = b;

        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }

        System.out.println("GCD/HCF of " + a + " and " + b + " = " + x);
    }

    /*
    =====================================================================================
    PROBLEM 5: CHECK ARMSTRONG NUMBER
    -------------------------------------------------------------------------------------
    An Armstrong number is a number where the sum of each digit raised to the power of the
    total number of digits equals the original number. For example, 153 is an Armstrong
    number because 1³ + 5³ + 3³ = 153. This problem strengthens understanding of loops,
    mathematical power operations, and digit processing.
    =====================================================================================
    */
    static void checkArmstrong(int n) {
        int original = n;
        int sum = 0;
        int digits = String.valueOf(n).length();

        while (n > 0) {
            int digit = n % 10;
            sum += Math.pow(digit, digits);
            n /= 10;
        }

        if (sum == original)
            System.out.println(original + " is an Armstrong Number");
        else
            System.out.println(original + " is NOT an Armstrong Number");
    }

    /*
    =====================================================================================
    PROBLEM 6: PRINT ALL DIVISORS OF A NUMBER
    -------------------------------------------------------------------------------------
    Divisors of a number are values that divide the number completely without leaving a
    remainder. To find all divisors efficiently, we iterate from 1 to the square root of
    the number. If a divisor is found, its corresponding pair is also a divisor. This
    approach reduces time complexity and improves performance.
    =====================================================================================
    */
    static void printDivisors(int n) {
        System.out.print("Divisors of " + n + ": ");

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                if (i != n / i)
                    System.out.print((n / i) + " ");
            }
        }

        System.out.println();
    }

    /*
    =====================================================================================
    PROBLEM 7: CHECK PRIME NUMBER
    -------------------------------------------------------------------------------------
    A prime number is a number greater than 1 that has exactly two divisors: 1 and itself.
    To check primality efficiently, we test divisibility only up to the square root of the
    number. If any divisor is found in this range, the number is not prime. This optimization
    significantly reduces unnecessary computations.
    =====================================================================================
    */
    static void checkPrime(int n) {
        if (n <= 1) {
            System.out.println(n + " is NOT Prime");
            return;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                System.out.println(n + " is NOT Prime");
                return;
            }
        }

        System.out.println(n + " is Prime");
    }
}
