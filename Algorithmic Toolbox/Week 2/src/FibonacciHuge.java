import java.util.ArrayList;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    06 May 2017
 *
 *  Description:    Given two integers 𝑛 and 𝑚, outputs 𝐹𝑛 mod 𝑚 (that is, the
 *                  remainder of 𝐹𝑛 when divided by 𝑚).
 *
 *  Compilation:    javac FibonacciHuge.java
 *  Execution:      java FibonacciHuge
 *
 *  Input:          The input consists of two integers 𝑛 and 𝑚 given on the
 *                  same line (separated by a space).
 *  Constraints:    1 ≤ 𝑛 ≤ 10^18, 2 ≤ 𝑚 ≤ 105
 *  Output:         Outputs 𝐹𝑛 mod 𝑚 on a single line.
 *
 *
 *  Dependencies:   java.util.ArrayList
 *                  java.util.Scanner
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
public class FibonacciHuge {

//    private static long getFibonacciHugeNaive(long n, long m) {
//        if (n <= 1)
//            return n;
//
//        long previous = 0;
//        long current  = 1;
//
//        for (long i = 0; i < n - 1; ++i) {
//            long tmp_previous = previous;
//            previous = current;
//            current = tmp_previous + current;
//        }
//
//        return current % m;
//    }

//    private static BigInteger getFibonacciNumber(long i) {
//        BigInteger[] arr = new BigInteger[(int) (i + 1)];
//        if (i == 0) return BigInteger.valueOf(0);
//        if (i == 1) return BigInteger.valueOf(1);
//
//        arr[0] = BigInteger.valueOf(0);
//        arr[1] = BigInteger.valueOf(1);
//
//        for (int j = 2; j < arr.length; j++) {
//            arr[j] = arr[j - 1].add(arr[j - 2]);
//        }
//
//        return arr[(int) i];
//
//        // 0 1 *1 2 3 5*
//    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        BigInteger n = BigInteger.valueOf(scanner.nextLong());
//        BigInteger m = BigInteger.valueOf(scanner.nextLong());
////        ArrayList<BigInteger> fibMod = getFibArray(n, m);
////        System.out.println(fibMod.get(n.mod(m).intValue()));
//        System.out.println(getFibonacciNumber((n).mod(m).longValue())
//            .mod(m));
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacci(n, m));
        scanner.close();
    }

    private static long getFibonacci(long n, long m) {
        long period = n + 1;
        long size = Math.min(n + 1, m * m + 1);
        ArrayList<Long> arr = new ArrayList<>();

        arr.add(0L);
        arr.add(1L);
        arr.add(1L);

        for (int i = 3; i < size; i++) {
            arr.add((arr.get(i - 2) + arr.get(i - 1)) % m);
            if (arr.get(i) == 1 && arr.get(i - 1) == 0) {
                period = i - 1;
                break;
            }
        }

        return arr.get((int) (n % period));
    }

    // mod 2 = 3
    // mod 3 = 6
    // mod 4 = 0 1 1 2 3 1 = 6
    // mod 5 = 0 1 1 2 3 0 3 3 1 4 0 4 4 3 7 0
}

