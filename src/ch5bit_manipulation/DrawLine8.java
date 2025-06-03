/*
5.8 Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive
pixels to be stored in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will
be split across rows). The height of the screen, of course, can be derived from the length of the array
and the width. Implement a function that draws a horizontal line from (xl, y) to (x2, y).
The method signature should look something like:
drawLine(byte[] screen, int width, int xl, int x2, int y)
*/
package ch5bit_manipulation;

public class DrawLine8 {

    public void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        int startOffset = x1 % 8;
        int firstFullByte = x1 / 8;
        if (startOffset != 0) {
            firstFullByte++;
        }

        int endOffset = x2 % 8;
        int lastFullByte = x2 / 8;
        if (endOffset != 7) {
            lastFullByte--;
        }

        // Set full bytes to 0xFF
        for (int b = firstFullByte; b <= lastFullByte; b++) {
            screen[(width / 8) * y + b] = (byte) 0xFF;
        }

        byte startMask = (byte) (0xFF >> startOffset);
        byte endMask = (byte) ~(0xFF >> (endOffset + 1)); 

        if ((x1 / 8) == (x2 / 8)) { // start and end of line within the same byte
            int byteNumber = (width / 8) * y + (x1 / 8);
            byte mask = (byte) (startMask & endMask);
            screen[byteNumber] |= mask;
        } else {
            if (startOffset != 0) {
                int byteNumber = (width / 8) * y + firstFullByte - 1;
                screen[byteNumber] |= startMask;
            }
            if (endOffset != 7) {
                int byteNumber = (width / 8) * y + lastFullByte + 1;
                screen[byteNumber] |= endMask;
            }
        }
    }

   public static void main(String[] args) {
        System.out.println("5.8 Draw Line:");
        int width = 32; // Must be divisible by 8 (ex, 4 bytes per row) from bit 0 to bit num 31
        int height = 2; //2 rows

        int bytesPerRow = width / 8;
        byte[] screen = new byte[bytesPerRow * height];
        System.out.println("The screen before drawing the line:");
        printScreen(screen, width);

        DrawLine8 drawer = new DrawLine8();
        drawer.drawLine(screen, width, 5, 27, 0); // Draw a horizontal line on row 0 from pixel 5 to 27 including 5 and 27 .. any row starts at bit 0 and ends at bit 31 (total is 32 bits)
        System.out.println("The screen after drawing the line:");
        printScreen(screen, width);
    }

   public static void printScreen(byte[] screen, int width) {
        int bytesPerRow = width / 8; //4 bytes here
        int height= screen.length / bytesPerRow; // 2 here

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < bytesPerRow; col++) {
                byte singleByte = screen[row * bytesPerRow + col];
                String binaryString = String.format("%8s", Integer.toBinaryString(singleByte & 0xFF)).replace(' ', '0');            //% – starts the format specifier.
                                                                                                                                    //8 – specifies the minimum width of the output string (in this case, 8 characters wide).
                                                                                                                                   //s – specifies that the argument is a string.
                System.out.print(binaryString + " ");
            }
            System.out.println();
        }
    }

}
