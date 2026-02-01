package bit_manipulation.intro;

/**
 * Introduction to Bit Manipulation - Theory
 * 
 * Bit Manipulation is the process of performing operations on individual bits.
 * 
 * Key Concepts:
 * 1. Bitwise AND (&): Returns 1 if both bits are 1, otherwise 0
 * 2. Bitwise OR (|): Returns 1 if at least one bit is 1
 * 3. Bitwise XOR (^): Returns 1 if bits are different
 * 4. Bitwise NOT (~): Flips all bits
 * 5. Left Shift (<<): Multiplies by 2^n
 * 6. Right Shift (>>): Divides by 2^n
 * 
 * Examples:
 * - 5 in binary: 0101
 * - 3 in binary: 0011
 * - 5 & 3 = 0001 = 1
 * - 5 | 3 = 0111 = 7
 * - 5 ^ 3 = 0110 = 6
 * - 5 << 1 = 1010 = 10
 * - 5 >> 1 = 0010 = 2
 */

public class BitManipulationTheory {
    
    public static void main(String[] args) {
        System.out.println("=== Bit Manipulation Theory ===\n");
        
        int a = 5;  // Binary: 0101
        int b = 3;  // Binary: 0011
        
        System.out.println("a = " + a + " (binary: " + Integer.toBinaryString(a) + ")");
        System.out.println("b = " + b + " (binary: " + Integer.toBinaryString(b) + ")\n");
        
        System.out.println("Bitwise AND (a & b): " + (a & b) + " (binary: " + Integer.toBinaryString(a & b) + ")");
        System.out.println("Bitwise OR (a | b): " + (a | b) + " (binary: " + Integer.toBinaryString(a | b) + ")");
        System.out.println("Bitwise XOR (a ^ b): " + (a ^ b) + " (binary: " + Integer.toBinaryString(a ^ b) + ")");
        System.out.println("Bitwise NOT (~a): " + (~a) + " (binary: " + Integer.toBinaryString(~a) + ")");
        System.out.println("Left Shift (a << 1): " + (a << 1) + " (binary: " + Integer.toBinaryString(a << 1) + ")");
        System.out.println("Right Shift (a >> 1): " + (a >> 1) + " (binary: " + Integer.toBinaryString(a >> 1) + ")");
    }
}
