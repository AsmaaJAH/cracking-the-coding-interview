/*5.5 Debugger: Explain what the following code does: ((n & (n-1 )) == 0). */
package ch5bit_manipulation;

public class Debugger5 {
    // This expression returns true if and only if n is a power of 2 or n is 0
    boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println("5.5 Debugger");
        Debugger5 debugger = new Debugger5();

        int[] testValues = {0, 1, 2, 3, 4, 5, 8, 10, 16, 31, 32, 33};

        for (int num : testValues) {
            boolean result = debugger.isPowerOfTwo(num);
            System.out.println("Is " + num + " a power of two? " + result);
        }
    }
}
