import java.util.ArrayList;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Represents a given positive integer 𝑛 as a sum of as many
 *                  pairwise distinct positive integers as possible. That is,
 *                  to find the maximum 𝑘 such that 𝑛 can be written as 𝑎1 +
 *                  𝑎2 + · · · + 𝑎𝑘 where 𝑎1, . . . , 𝑎𝑘 are positive
 *                  integers and 𝑎𝑖 ̸= 𝑎𝑗 for all 1 ≤ 𝑖 < 𝑗 ≤ 𝑘.
 *
 *  Compilation:    javac MaxPrizePlaces.java
 *  Execution:      java MaxPrizePlaces
 *
 *  Input:          The input consists of a single integer 𝑛.
 *  Constraints:    1 ≤ 𝑛 ≤ 10^9
 *  Output:         In the first line, outputs the maximum number 𝑘 such that 𝑛
 *                  can be represented as a sum of 𝑘 pairwise distinct positive
 *                  integers. In the second line, outputs 𝑘 pairwise distinct
 *                  positive integers that sum up to 𝑛 (if there are many such
 *                  representations, output any of them).
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
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 *  DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
public class MaxPrizePlaces {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        long n = scan.nextLong();

        ArrayList<Long> arr = getArrs(n);
        for (long i : arr) {
            System.out.print(i + " ");
        }

    }

    private static ArrayList<Long> getArrs(long n) {

        ArrayList<Long> ret = new ArrayList<>();

        long sum = 0;
        for (long i = 1; true; i++) {
            ret.add(i);
            sum += i;
            if (sum > n) {
//        System.out.println("sum > n");
//        System.out.println("sum = " + sum);
//        System.out.println("i = " + i);
                sum -= i;
//        System.out.println("");
                ret.remove(ret.size() - 1);
                long toAdd = (n - sum);
                while (ret.size() >= 1 && toAdd <= ret.get(ret.size() - 1)) {
                    toAdd += ret.remove(ret.size() - 1);
                }
                if (toAdd != 0)
                    ret.add(toAdd);
                System.out.println(i - 1);
                break;
            }
        }
        return ret;
    }

}
