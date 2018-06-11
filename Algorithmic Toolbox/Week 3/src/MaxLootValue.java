import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Finds the maximum value that can be fit in W weight by
 *                  taking in Weight and Value of items. Implements the
 *                  fractional knapsack greedy algorithm.
 *
 *  Compilation:    javac MaxLootValue.java
 *  Execution:      java MaxLootValue
 *
 *  Input:          The first line of the input contains the number 𝑛 of items
 *                  and the capacity 𝑊 of a knapsack. The next 𝑛 lines define
 *                  the values and weights of the items. The 𝑖-th line contains
 *                  integers 𝑣𝑖 and 𝑤𝑖—the value and the weight of 𝑖-th item,
 *                  respectively.
 *  Constraints:    1 ≤ 𝑛 ≤ 103, 0 ≤ 𝑊 ≤ 2 * 10^6, 0 ≤ 𝑣𝑖 ≤ 2 * 10^6,
 *                  0 < 𝑤𝑖 ≤ 2 * 10^6, for all 1 ≤ 𝑖 ≤ 𝑛. All the numbers are
 *                  integers.
 *  Output:         Outputs the maximal value of fractions of items that fit
 *                  into the knapsack. The absolute value of the difference
 *                  between the answer of your program and the optimal value is
 *                  at most 10^−3. The answer has four digits after the decimal
 *                  point to avoid floating point rounding issues.
 *
 *  Dependencies:   java.text.DecimalFormat
 *                  java.text.NumberFormat
 *                  java.util.ArrayList
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
public class MaxLootValue {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double W = scan.nextDouble();
        ArrayList<Loot> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new Loot(scan.nextInt(), scan.nextInt()));
        }
        Collections.sort(arr);

        NumberFormat format = new DecimalFormat("#0.0000");
        System.out.println(format.format(getAnswer(arr, W)));

    }

    private static double getAnswer(ArrayList<Loot> arr, double weight) {
        double W = weight;
        double val = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (W == 0)
                return val;
            double add = Math.min(arr.get(i).getWeight(), W);
            val += add * arr.get(i).getUnitValue();
            arr.get(i).removeWeight(add);
            if (arr.get(i).getWeight() == 0)
                arr.remove(i);
            W -= add;
        }
        return val;

    }

}

class Loot implements Comparable<Loot> {

    private double value;
    private double weight;

    Loot(double value, double weight) {
        this.weight = weight;
        this.value = value;
    }

    void removeWeight(double weight) {

        double unitVal = getUnitValue();
        this.weight -= weight;
        value = weight * unitVal;

    }

    double getWeight() {
        return weight;
    }

    double getUnitValue() {
        return value / weight;
    }

    public int compareTo(@NotNull Loot other) {
        double otherUnitValue = other.getUnitValue();
        double thisUnitValue = this.getUnitValue();
        if (thisUnitValue < otherUnitValue) {
            return -1;
        }
        if (thisUnitValue > otherUnitValue) {
            return 1;
        }
        return 0;
    }

}
