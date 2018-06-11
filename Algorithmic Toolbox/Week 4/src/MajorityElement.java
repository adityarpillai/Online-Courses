import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    25 May 2017
 *
 *  Description:    Majority rule is a decision rule that selects the
 *                  alternative which has a majority, that is, more than half
 *                  the votes. Given a sequence of elements 𝑎1, 𝑎2, . . . ,
 *                  𝑎𝑛, you would like to check whether it contains an
 *                  element that appears more than 𝑛/2 times. Your goal is to
 *                  use the divide-and-conquer technique to design an
 *                  𝑂(𝑛 log 𝑛) algorithm.
 *
 *                  The goal in this code problem is to check whether an
 *                  input sequence contains a majority element.
 *
 *  Compilation:    javac MajorityElement.java
 *  Execution:      java MajorityElement
 *
 *  Input:          The first line contains an integer 𝑛, the next one
 *                  contains a sequence of 𝑛 non-negative integers
 *                  𝑎0, 𝑎1, ... , 𝑎𝑛−1.
 *  Constraints:    1 ≤ 𝑛 ≤ 105; 0 ≤ 𝑎𝑖 ≤ 109 for all 0 ≤ 𝑖 < 𝑛.
 *  Output:         Output 1 if the sequence contains an element that appears
 *                  strictly more than 𝑛/2 times, and 0 otherwise.
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
public class MajorityElement {

    private static int getMajorityElement(int[] a) {

        if (a.length == 0)
            return 0;

        int numNeeded = a.length / 2;
        Arrays.sort(a);
        int count = 1;
        for (int i = 1; i < a.length; i++) {

            if (a[i - 1] == a[i]) {
                count++;
            } else {
                count = 1;
            }

            if (count > numNeeded) {
                return 1;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getMajorityElement(a));

    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


