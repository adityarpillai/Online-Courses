import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Composes the largest number out of a set of integers.
 *
 *  Compilation:    javac MaxSalary.java
 *  Execution:      java MaxSalary
 *
 *  Input:          The first line of the input contains an integer 𝑛. The
 *                  second line contains integers 𝑎1, 𝑎2, . . . , 𝑎𝑛.
 *  Constraints:    1 ≤ 𝑛 ≤ 100; 1 ≤ 𝑎𝑖 ≤ 103 for all 1 ≤ 𝑖 ≤ 𝑛.
 *  Output:         Outputs the largest number that can be composed out of
 *                  𝑎1, 𝑎2, . . . , 𝑎𝑛.
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
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
 *  DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
public class MaxSalary {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<sortInts> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new sortInts(scan.nextInt()));
        }
        Collections.sort(arr);
//    System.out.println(Arrays.toString(arr.toArray(new sortInts[arr.size()])));
        for (int i = arr.size() - 1; i >= 0; i--) {
            System.out.print(arr.get(i));
        }
//    for (sortInts s : arr) {
//      System.out.print(s);
//    }

    }

}

class sortInts implements Comparable<sortInts> {

    private int num;

    sortInts(int num) {
        this.num = num;
    }

    private int getNum() {
        return num;
    }

    public String toString() {
        return "" + num;
    }

    public int compareTo(@NotNull sortInts that) {
        String thisTemp = Integer.toString(num);
        String thatTemp = Integer.toString(that.getNum());

        return thisComparedToThat(thisTemp, thatTemp);

    }

    private int thisComparedToThat(String thisStr, String thatStr) {

        String xy = thisStr + thatStr;
        String yx = thatStr + thisStr;

        return xy.compareTo(yx);

    }


}