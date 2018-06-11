import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    06 May 2017
 *
 *  Description:    Given an integer n, find the nth Fibonacci number Fn.
 *
 *  Compilation:    javac Fibonacci.java
 *  Execution:      java Fibonacci
 *
 *  Input:          A single integer n
 *  Constraints:    0 <= n <= 45
 *  Output:         Fibonacci number Fn
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
public class Fibonacci {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        System.out.println(getFibonacciNumber(i));
    }

    /**
     * Returns ith fibonacci number with F0 = 0, F1 = 1, F2 = 1 ...
     *
     * @param i ith fibonacci number
     * @return long fibonacci number
     */
    private static long getFibonacciNumber(int i) {
        long[] arr = new long[i + 1];
        if (i == 0) return 0;
        if (i == 1) return 1;

        arr[0] = 0;
        arr[1] = 1;

        for (int j = 2; j < arr.length; j++) {
            arr[j] = arr[j - 1] + arr[j - 2];
        }

        return arr[i];

        // 0 1 *1 2 3 5*
    }

}
