import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    06 May 2017
 *
 *  Description:    Finds the greatest common divisor GCD(a, b) of two
 *                  non-negative integers a and b (which are not both equal to
 *                  0) utilizing the Euclidean algorithm for calculating the
 *                  greatest common divisor.
 *
 *  Compilation:    javac GCD.java
 *  Execution:      java GCD
 *
 *  Input:          Two integers a, b separated by a space
 *  Constraints:    1 <= a, b <= 2 * 10^9
 *  Output:         The greatest common divisor of a and b
 *
 *  Dependencies:   java.util.Scanner
 *  Package:        None
 *
 *  Copyright 2017 Aditya Pillai
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a 
 *  copy of this software and associated documentation files (the "Software"), 
 *  to deal in the Software without restriction, including without limitation 
 *  the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 *  and/or sell copies of the Software, and to permit persons to whom the 
 *  Software is furnished to do so, subject to the following conditions:
 *  The above copyright notice and this permission notice shall be included in 
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 *  IN THE SOFTWARE.
 ******************************************************************************/
public class GCD {
//    private static int gcd_naive(int a, int b) {
//        int current_gcd = 1;
//        for(int d = 2; d <= a && d <= b; ++d) {
//            if (a % d == 0 && b % d == 0) {
//                if (d > current_gcd) {
//                    current_gcd = d;
//                }
//            }
//        }
//
//        return current_gcd;
//    }

    private static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(getGCD(a, b));
    }
}
