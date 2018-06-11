import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    06 May 2017
 *
 *  Description:    Find the last digit of n-th Fibonacci number.
 *
 *  Compilation:    javac FibonacciLastDigit.java
 *  Execution:      java FibonacciLastDigit
 *
 *  Input:          Single integer n
 *  Constraints:    0 <= n <= 10^7
 *  Output:         The last digit of Fn, where Fi is the i-th fibonacci number
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
public class FibonacciLastDigit {
//    private static int getFibonacciLastDigitNaive(int n) {
//        if (n <= 1)
//            return n;
//
//        int previous = 0;
//        int current  = 1;
//
//        for (int i = 0; i < n - 1; ++i) {
//            int tmp_previous = previous;
//            previous = current;
//            current = tmp_previous + current;
//        }
//
//        return current % 10;
//    }

    private static int getFibonacciLastDigit(int n) {
        int first = 0;
        int second = 1;
        int oldFirst;
        for (int i = 0; i < n - 1; i++) {
            oldFirst = first;
            first = second;
            second = (oldFirst + first) % 10;
        }

        return second;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getFibonacciLastDigit(n));
    }
}


