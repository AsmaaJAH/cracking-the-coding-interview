/*
5.2 Binary to String:
Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
print the binary representation. If the number cannot be represented accurately
in binary with at most 32 characters, print "ERROR".
*/

package ch5bit_manipulation;

public class BinaryToString2 {

    public static String printBinary(double num) {
        if (num >= 1 || num <= 0) {
            return "ERROR";
        }

        StringBuilder binary = new StringBuilder();
        binary.append(".");

        while (num > 0) {
            if (binary.length() >= 32) {
                return "ERROR"; // Exceeded 32 characters
            }

            double r = num * 2;

            if (r >= 1) {
                binary.append("1");
                num = r - 1;
            } else {
                binary.append("0");
                num = r;
            }
        }

        return binary.toString();
    }

    public static void main(String[] args) {
        System.out.println("5.2 Binary to String:");
        System.out.println("Binary of 0.625: " + printBinary(0.625));   // Expected: 0.101
        System.out.println("Binary of 0.72: " + printBinary(0.72));     // ERROR
        System.out.println("Binary of 0.5: " + printBinary(0.5));       // Expected: 0.1
    }
}

