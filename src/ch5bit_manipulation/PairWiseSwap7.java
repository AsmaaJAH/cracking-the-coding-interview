/* 
5.7 Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as
possible (e.g., bit O and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
*/
package ch5bit_manipulation;

public class PairWiseSwap7 {

    public int swapOddEvenBits(int x) {
        // 0xAAAAAAAA = 101010...10 (mask for even bits)
        // 0x55555555 = 010101...01 (mask for odd bits)
        return ((x & 0xAAAAAAAA) >>> 1) | ((x & 0x55555555) << 1);
    }

    public static void main(String[] args) {
        System.out.println("5.7 Pairwise Swap:");
        PairWiseSwap7 swapper = new PairWiseSwap7();

        int original = 0b11100011;
        int swapped = swapper.swapOddEvenBits(original);

        System.out.println("Original: " + Integer.toBinaryString(original));
        System.out.println("Swapped : " + Integer.toBinaryString(swapped));
        System.out.println("Swapped (decimal): " + swapped);
    }
}
