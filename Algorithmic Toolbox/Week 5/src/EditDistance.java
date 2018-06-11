import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    28 May 2017
 *
 *  Description:    Description
 *
 *  Compilation:    javac EditDistance.java
 *  Execution:      java EditDistance
 *
 *  Input:          Input
 *  Constraints:    Constraints
 *  Output:         Output
 *
 *  Dependencies:   Dependencies
 *  Package:        PACKAGE_NAME
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
public class EditDistance {

    public static int EditDistance(String s, String t) {
        int[][] arr = new int[s.length() + 1][t.length() + 1];

        // initialize top-left corner and left and top sides as 0
        arr[0][0] = 0;
        for (int i = 0; i < arr[0].length; i++)
            arr[0][i] = i;
        for (int i = 0; i < arr.length; i++)
            arr[i][0] = i;

        // traverse the array column by column, filling down each row
        for (int c = 1; c < arr[0].length; c++) {
            // column corresponds to string t
            // row corresponds to string s
            for (int r = 1; r < arr.length; r++) {
                int ins = arr[r][c - 1] + 1;
                int del = arr[r - 1][c] + 1;
                int val = Math.min(ins, del);
                if (s.charAt(r - 1) == t.charAt(c - 1)) {
                    val = Math.min(val, arr[r - 1][c - 1]);
                } else {
                    val = Math.min(val, arr[r - 1][c - 1] + 1);
                }
                arr[r][c] = val;
            }
        }

        return arr[s.length()][t.length()];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }


}
