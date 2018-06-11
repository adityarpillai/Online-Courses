import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    27 May 2017
 *
 *  Description:    You are given a primitive calculator that can perform the
 *                  following three operations with the current number x:
 *                  multiply x by 2, multiply x by 3, or add 1 to x. Your goal is given a
 *                  positive integer n, find the minimum number of operations
 *                  needed to obtain the number n starting from the number 1.
 *
 *                  Given an integer n, compute the minimum number of
 *                  operations needed to obtain the number n starting from
 *                  the number 1.
 *
 *  Compilation:    javac PrimitiveCalculator.java
 *  Execution:      java PrimitiveCalculator
 *
 *  Input:          The input consists of a single integer 1 ≤ n ≤ 10^6.
 *  Constraints:    1 <= n <= 10^6
 *  Output:         In the first line, output the minimum number k of
 *                  operations needed to get n from 1. In the second line
 *                  output a sequence of intermediate numbers. That is, the
 *                  second line should contain positive integers a0, a2, . .
 *                  . , ak−1 such that a0 = 1, ak−1 = n and for all 0 ≤ i < k
 *                  − 1, ai+1 is equal to either ai + 1, 2ai , or 3ai . If
 *                  there are many such sequences, output any one of them.
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
public class PrimitiveCalculator {

    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence_dynamic(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }

    private static List<Integer> optimal_sequence_dynamic(int n) {

        // create nums int
        int[] nums = new int[n + 1];
        nums[1] = 0;

        for (int i = 2; i < nums.length; i++) {
            // iterate through the three different cases
            // case 1: * 3
            int val = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                val = nums[i / 3] + 1;
            }
            if (i % 2 == 0) {
                val = Math.min(val, nums[i / 2] + 1);
            }
            val = Math.min(val, nums[i - 1] + 1);
            nums[i] = val;
        }

        return getList(nums);

    }

    private static List<Integer> getList(int[] nums) {

        int i = nums.length - 1;
        List<Integer> list = new ArrayList<>();

        while (i > 0) {
            if (i % 3 == 0 && nums[i / 3] + 1 == nums[i]) {
                list.add(i);
                i /= 3;
            } else if (i % 2 == 0 && nums[i / 2] + 1 == nums[i]) {
                list.add(i);
                i /= 2;
            } else {
                list.add(i);
                i--;
            }
        }

        Collections.reverse(list);
        return list;

    }
}
