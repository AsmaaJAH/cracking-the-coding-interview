/*
5.1 Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j. 
Write a method to insert M into N such that M starts at bit j and ends at bit i. 
Assume j > i and M fits between j and i.
Example:
Input:  N = 10000000000 (binary), M = 10011 (binary), i = 2, j = 6
Output: N = 10001001100 (binary)
*/

package ch5bit_manipulation;

public class Insertion1 {

    public static int updateBits(int n, int m, int i, int j) {
        // a mask to clear bits i through j in n
        int allOnes = ~0; // Sequence of all 1s

        // 1s before position j, then 0s, ex =11111111111111100
        int left = allOnes << (j + 1);

        // 1s after position i, ex=00000000000000000000011
        int right = (1 << i) - 1;

        // All 1s except for 0s between i and j, ex=1111111111111100011111111111111111111
        int mask = left | right;

        // Clear bits j through i in n
        int n_cleared = n & mask;

        // Shift m so it lines up with bits i through j
        int m_shifted = m << i;

        // OR them together to merge
        return n_cleared | m_shifted;
    }

    public static void main(String[] args) {
        System.out.println("5.1 Insertion:");
        // Example:
        // N = 10000000000 (binary) = 1024 (decimal)
        // M = 10011 (binary) = 19 (decimal)
        // i = 2, j = 6

        int N = 1024; // 010000000000
        int M = 19;   // 010011
        int i = 2;
        int j = 6;

        int result = updateBits(N, M, i, j);

        System.out.println("Result (binary): " + Integer.toBinaryString(result));
        System.out.println("Result (decimal): " + result);
    }
}
