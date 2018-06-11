import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    25 May 2017
 *
 *  Description:    The goal in this problem is to redesign a given
 *                  implementation of the randomized quick sort algorithm so
 *                  that it works fast even on sequences containing many
 *                  equal elements.
 *
 *                  To force the given implementation of the quick sort
 *                  algorithm to efficiently process sequences with few
 *                  unique elements, your goal is replace a 2-way partition
 *                  with a 3-way partition. That is, your new partition
 *                  procedure should partition the array into three parts:
 *                  < 𝑥 part, = 𝑥 part, and > 𝑥 part.
 *
 *  Compilation:    javac Sorting.java
 *  Execution:      java Sorting
 *
 *  Input:          The first line of the input contains an integer 𝑛. The
 *                  next line contains a sequence of 𝑛 integers
 *                  𝑎0, 𝑎1, ... , 𝑎𝑛−1.
 *  Constraints:    1 ≤ 𝑛 ≤ 105; 1 ≤ 𝑎𝑖 ≤ 109 for all 0 ≤ 𝑖 < 𝑛
 *  Output:         Output this sequence sorted in non-decreasing order.
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
public class Sorting {

    private static Random random = new Random();

    private static int partition3(int[] a, int l, int m) {
        // approach: a is the entire array, l is the first part of the array
        // and m is the pivot. every value that is the same as r should be
        // moved next to it, and we return the first index of the save value
        while (l < m) {
            if (a[l] == a[m]) {
                int temp = a[l];
                a[l] = a[m - 1];
                a[m - 1] = temp;
                m = m - 1;
            } else {
                l = l + 1;
            }
        }

        return l;

    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int m = partition2(a, l, r);
        int f = partition3(a, l, m);
        randomizedQuickSort(a, l, f - 1);
        randomizedQuickSort(a, m + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
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
