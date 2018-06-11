import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Finds the minimum number of coins needed to change the input
 *                  value (an integer) into coins with denominations 1, 5, and
 *                  10.
 *
 *  Compilation:    javac ChangingMoney.java
 *  Execution:      java ChangingMoney
 *
 *  Input:          The input consists of a single integer m.
 *  Constraints:    1 <= m <= 10^3
 *  Output:         Outputs the minimum number of coins with denominations 1, 5,
 *                  10 that changes 𝑚 on a single line.
 *
 *  Dependencies:   java.util.Scanner
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
public class ChangingMoney {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int m = scan.nextInt();
        int count = 0;
        while (m >= 10) { // add dimes when m >= 10
            count++;
            m -= 10;
        }
        while (m >= 5) { // add nickels when m >= 5
            count++;
            m -= 5;
        }
        while (m >= 1) { // add pennies when m >= 1
            count++;
            m -= 1;
        }
        System.out.println(count);

    }

}
