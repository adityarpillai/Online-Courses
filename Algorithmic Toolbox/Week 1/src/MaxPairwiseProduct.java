import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    03 May 2017
 *
 *  Description:    Finds the max pairwise product of a set of longs through
 *                  both naive and fast algorithms.
 *
 *
 *  Compilation:    javac AWeek.MaxPairwiseProduct.java
 *  Execution:      java AWeek.MaxPairWiseProduct
 *
 *  Input:          n number of ints in the set
 *                  (set of n numbers)
 *
 *  Output:         fast: (max pairwise product)
 *                  slow (max pairwise product through naive algorithm)
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
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 *  IN THE SOFTWARE.
 ******************************************************************************/
public class MaxPairwiseProduct {

    private static long getMaxPairwiseProductFast(int[] numbers) {

        int maxIndex1 = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > numbers[maxIndex1]) {
                maxIndex1 = i;
            }
        }
        int maxIndex2 = -1;
        for (int j = 0; j < numbers.length; j++) {
            if ((j != maxIndex1 && ((maxIndex2 == -1) || (numbers[j] >
                    numbers[maxIndex2])))) {
                maxIndex2 = j;
            }
        }

        return ((long) numbers[maxIndex1]) * ((long) numbers[maxIndex2]);
    }

    private static long getMaxPairwiseProduct(int[] numbers) {
        long result = 0;
        int n = numbers.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if ((long) numbers[i] * (long) numbers[j] > result) {
                    result = (long) numbers[i] * (long) numbers[j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println("fast: " + getMaxPairwiseProductFast(numbers));
        System.out.println("slow: " + getMaxPairwiseProduct(numbers));

//        while (true) {
//            int n = (int) (Math.random() * 10) + 2;
//            int[] arr = new int[n];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = (int)(Math.random() * 00000);
//            }
//            System.out.println("n = " + n);
//            long time = System.currentTimeMillis();
//            long res1 = getMaxPairwiseProduct(arr);
//            long res1Time = System.currentTimeMillis() - time;
//            time = System.currentTimeMillis();
//            long res2 = getMaxPairwiseProductFast(arr);
//            long res2Time = System.currentTimeMillis() - time;
//            System.out.println(res2Time - res1Time + " millisecond difference");
//            if (res1 != res2) {
//                System.out.println("Wrong answer: " + res1
//                    + " " + res2);
//                break;
//            }
//        }

    }

}