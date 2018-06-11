import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Given two sequences a1, a2, ..., an (ai is the profit per
 *                  click of the i-th ad) and b1, b2, ..., bn (bi is the average
 *                  number of clicks per day i-th slot), we need to partition
 *                  them into n pairs (ai, bj) such that the sum of their
 *                  products is maximized.
 *
 *  Compilation:    javac MaxAdRevenue.java
 *  Execution:      java MaxAdRevenue
 *
 *  Input:          The first line contains an integer n, the second contains a
 *                  sequence of integers a1, a2, ..., an, the third one contains
 *                  a sequence of integers b1, b2, ..., bn.
 *  Constraints:    1 ≤ 𝑛 ≤ 10^3; −10^5 ≤ 𝑎𝑖, 𝑏𝑖 ≤ 10^5 for all 1 ≤ 𝑖 ≤ 𝑛.
 *  Output:         Outputs the maximum value of ai * bi for all i.
 *
 *  Dependencies:   java.util.ArrayList
 *                  java.util.Collections
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
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 *  DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
public class MaxAdRevenue {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        ArrayList<Long> profit = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            profit.add(scan.nextLong());
        }
        Collections.sort(profit);

        ArrayList<Long> clicks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            clicks.add(scan.nextLong());
        }
        Collections.sort(clicks);

        long revenue = 0;
        for (int i = 0; i < clicks.size(); i++) {
            revenue += (clicks.get(i) * profit.get(i));
        }

        System.out.println(revenue);

    }

}
