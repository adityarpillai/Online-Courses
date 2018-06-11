import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    25 May 2017
 *
 *  Description:    In this problem, you will implement the binary search
 *                  algorithm that allows searching very efficiently (even huge)
 *                  lists, provided that the list is sorted.
 *
 *                  The goal in this code problem is to implement the binary
 *                  search algorithm.
 *
 *  Compilation:    javac BinarySearch.java
 *  Execution:      java BinarySearch
 *
 *  Input:          The first line of the input contains an integer 𝑛 and a
 *                  sequence 𝑎0 < 𝑎1 < . . . < 𝑎𝑛−1 of 𝑛 pairwise distinct
 *                  positive integers in increasing order. The next line
 *                  contains an integer 𝑘 and 𝑘 positive integers
 *                  𝑏0, 𝑏1, . . . , 𝑏𝑘−1.
 *  Constraints:    1 ≤ 𝑛, 𝑘 ≤ 105; 1 ≤ 𝑎𝑖 ≤ 109 for all 0 ≤ 𝑖 < 𝑛; 1 ≤ 𝑏𝑗 ≤ 109
 *                  for all 0 ≤ 𝑗 < 𝑘;
 *  Output:         For all 𝑖 from 0 to 𝑘 − 1, output an index 0 ≤ 𝑗 ≤ 𝑛 − 1
 *                  such that 𝑎𝑗 = 𝑏𝑖 or −1 if there is no such index/
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
public class BinarySearch {

    private static int binarySearch(int[] a, int x) {
        return binarySearch(x, a, 0, a.length);
    }

    private static int binarySearch(int x, int[] a, int firstIndex, int endIndex) {
        int mid = firstIndex + (endIndex - firstIndex) / 2;
        if (firstIndex >= endIndex)
            return -1;
        int diff = x - a[mid];
        if (diff == 0) {
            return mid;
        }
        if (diff < 0) {
            return binarySearch(x, a, firstIndex, mid);
        } else {
            return binarySearch(x, a, mid + 1, endIndex);
        }
    }

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch(a, b[i]) + " ");
            System.out.println();
            System.out.print(linearSearch(a, b[i]) + " ");
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
