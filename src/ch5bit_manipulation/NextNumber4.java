/*
5.4 Next Number: Given a positive integer, print the next smallest and the next largest number that
have the same number of 1 bits in their binary representation.
 */
package ch5bit_manipulation;

public class NextNumber4 {

    // the next largest number with the same number of 1s
    public static int getNext(int n) {
        int c = n;
        int c0 = 0;
        int c1 = 0;

        // count trailing zeros (c0)
        while (((c & 1) == 0) && (c != 0)) {
            c0++;
            c >>= 1;
        }

        // count ones (c1)
        while ((c & 1) == 1) {
            c1++;
            c >>= 1;
        }

        // If n == 11..1100..00, then there is no bigger number with same number of 1s
        if (c0 + c1 == 31 || c0 + c1 == 0) return -1;

        int p = c0 + c1; // position of rightmost non-trailing zero

        n |= (1 << p); // Flip rightmost non-trailing zero
        n &= ~((1 << p) - 1); // Clear all bits to the right of p
        n |= (1 << (c1 - 1)) - 1; // Insert (c1-1) ones on the right

        return n;
    }

    // the next smallest number with the same number of 1s
    public static int getPrev(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;

        // count trailing ones (c1)
        while ((temp & 1) == 1) {
            c1++;
            temp >>= 1;
        }

        // If all bits are 1s (e.g., 1111...), no smaller number with same number of 1s
        if (temp == 0) return -1;

        // count zeros before the trailing ones (c0)
        while (((temp & 1) == 0) && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        int p = c0 + c1; // position of rightmost non-trailing 1

        // Clear from bit p onwards
        n &= (~0) << (p + 1);

        // Sequence of (c1+1) ones
        int mask = (1 << (c1 + 1)) - 1;

        // Move the sequence to just to the left of cleared bits
        n |= mask << (c0); //gives me the correct output:  (11011001111100), while->   n |= mask << (c0 - 1); gives me 1 bit error in the output: (11011001111010)


        return n;
    }


    public static void main(String[] args) {
        System.out.println("5.4 Next Number:");
        int num = 13948; // Binary: 0011 0100 1011 1100
        int next = getNext(num);
        int prev = getPrev(num);

        System.out.println("Original: " + num + " (" + Integer.toBinaryString(num) + ")");
        System.out.println("Next:     " + next + " (" + Integer.toBinaryString(next) + ")");
        System.out.println("Prev:     " + prev + " (" + Integer.toBinaryString(prev) + ")");
    }
}
