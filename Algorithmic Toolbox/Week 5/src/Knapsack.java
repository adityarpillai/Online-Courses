import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    28 May 2017
 *
 *  Description:    In this problem, you are given a set of bars of gold and
 *                  your goal is to take as much gold as possible into your
 *                  bag. There is just one copy of each bar and for each bar
 *                  you can either take it or not (hence you cannot take a
 *                  fraction of a bar).
 *
 *  Compilation:    javac Knapsack.java
 *  Execution:      java Knapsack
 *
 *  Input:          The first line of the input contains the capacity W of a
 *                  knapsack and the number n of bars of gold. The next line
 *                  contains n integers w0, w1, . . . , wn−1 defining the
 *                  weights of the bars of gold.
 *  Constraints:    1 ≤ W ≤ 104; 1 ≤ n ≤ 300; 0 ≤ w0, . . . , wn−1 ≤ 10^5
 *  Output:         Output the maximum weight of gold that fits into a
 *                  knapsack of capacity W.
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
public class Knapsack {

    static int optimalWeight(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
            if (result + w[i] <= W) {
                result += w[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(getOptimalWeight(W, w));
    }

    private static int getOptimalWeight(int W, int[] bars) {
        int[][] val = new int[W + 1][bars.length];

        for (int j = 0; j < W + 1; j++) {
            val[j][0] = 0;
        }
        for (int i = 0; i < bars.length; i++) {
            val[0][i] = 0;
        }

        for (int i = 1; i < bars.length; i++) {
            for (int w = 1; w <= W; w++) {
                val[w][i] = val[w][i - 1];
                if (bars[i] <= w) {
                    int v = val[w - bars[i]][i - 1] + bars[i];
                    val[w][i] = Math.max(val[w][i], v);
                }

            }
        }

        return val[W][bars.length - 1];
    }

}
