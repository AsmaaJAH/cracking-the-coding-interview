/*
5.6 Conversion: Write a function to determine the number of bits you would need to flip to convert
integer A to integer B.
EXAMPLE
Input: 29 (or: 11101), 15 (or: 01111)
Output: 2
*/
package ch5bit_manipulation;

public class Conversion6 {

    // Function to count number of differing bits
    public int numOfbitSwapRequired(int a, int b) {
        int count = 0;

        int c = a ^ b; // XOR
        while (c != 0) {
            count += c & 1; // increment if last bit is 1
            c >>>= 1;        // logical shift right
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println("5.6 Conversion:");
        Conversion6 converter = new Conversion6();
        int a = 29; // Binary: 11101
        int b = 15; // Binary: 01111

        int result = converter.numOfbitSwapRequired(a, b);
        System.out.println("Number of bits to flip: " + result);
    }
}

