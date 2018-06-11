import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    06 May 2017
 *
 *  Description:    Given an integer 𝑛, find the last digit of the sum 𝐹0 + 𝐹1
 *                  + · · · + 𝐹𝑛.
 *
 *  Compilation:    javac FibonacciSumLastDigit.java
 *  Execution:      java FibonacciSumLastDigit
 *
 *  Input:          The input consists of a single integer 𝑛.
 *  Constraints:    0 ≤ 𝑛 ≤ 10^14
 *  Output:         The last digit of 𝐹0 + 𝐹1 + · · · + 𝐹𝑛 on a single line.
 *
 *  Dependencies:   None
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
public class FibonacciSumLastDigit {
    private static long getFibonacciSum(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current = 1;
        long sum = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            sum += current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            long n = (long) (Math.random() * (Math.pow(10, 14)));
//            // scanner.nextLong();
//            long first = System.currentTimeMillis();
//            long s = getFibonacciSum(n);
//            long end = System.currentTimeMillis();
//            if (end - first > 500) {
//                System.out.println("time: " + (end - first));
//                System.out.println("searching: " + n);
//                System.out.println("result: " + s);
//                break;
//            }
//        }
    }
}

