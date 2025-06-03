/*
5.3 Flip Bit to Win: You have an integer and you can flip exactly one bit from a O to a 1. Write code to
find the length of the longest sequence of 1 s you could create.
EXAMPLE
Input: 1775
Output: 8 
*/

package ch5bit_manipulation;

public class FlipBitToWin3 {

    public static int flipBit(int a) {
        if (a == -1) return Integer.BYTES * 8; // 4 bytes * 8 = 32 bits

        int currentLength = 0;
        int previousLength = 0;
        int maxLength = 1; //we can flip at least one bit

        while (a != 0) {
            if ((a & 1) == 1) {
                currentLength++;
            } else {
                // Check next bit:
                previousLength = (a & 2) == 0 ? 0 : currentLength;
                currentLength = 0;
            }

            maxLength = Math.max(previousLength + currentLength + 1, maxLength);
            a >>>= 1; // Logical right shift (fills with 0s)
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("5.3 Flip Bit to Win:");
        int input = 1775; // binary: 11011101111
        System.out.println("Longest sequence: " + flipBit(input)); // Output: 8
    }
}
