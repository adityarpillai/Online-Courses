import java.util.Arrays;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    25 May 2017
 *
 *  Description:    You are organizing an online lottery. To participate, a
 *                  person bets on a single integer. You then draw several
 *                  ranges of consecutive integers at random. A participant’s
 *                  payoff then is proportional to the number of ranges that
 *                  contain the participant’s number minus the number of
 *                  ranges that does not contain it. You need an efficient
 *                  algorithm for computing the payoffs for all participants.
 *                  A naive way to do this is to simply scan, for all
 *                  participants, the list of all ranges. However, you
 *                  lottery is very popular: you have thousands of
 *                  participants and thousands of ranges. For this reason,
 *                  you cannot afford a slow naive algorithm.
 *
 *                  You are given a set of points on a line and a set of
 *                  segments on a line. The goal is to compute, for each
 *                  point, the number of segments that contain this point.
 *
 *  Compilation:    javac PointsAndSegments.java
 *  Execution:      java PointsAndSegments
 *
 *  Input:          The first line contains two non-negative integers 𝑠 and 𝑝
 *                  defining the number of segments and the number of points
 *                  on a line, respectively. The next 𝑠 lines contain two
 *                  integers 𝑎𝑖 , 𝑏𝑖 defining the 𝑖-th segment [𝑎𝑖 , 𝑏𝑖 ].
 *                  The next line contains 𝑝 integers defining points
 *                  𝑥1, 𝑥2, ... , 𝑥𝑝.
 *  Constraints:    1 ≤ 𝑠, 𝑝 ≤ 50000; −108 ≤ 𝑎𝑖 ≤ 𝑏𝑖 ≤ 108 for all 0 ≤ 𝑖 < 𝑠;
 *                  −108 ≤ 𝑥𝑗 ≤ 108 for all 0 ≤ 𝑗 < 𝑝.
 *  Output:         Output 𝑝 non-negative integers 𝑘0, 𝑘1, . . . , 𝑘𝑝−1 where
 *                  𝑘𝑖 is the number of segments which contain 𝑥𝑖 . More
 *                  formally, 𝑘𝑖 = |{𝑗 : 𝑎𝑗 ≤ 𝑥𝑖 ≤ 𝑏𝑗}| .
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
public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Arrays.sort(starts);
        Arrays.sort(ends);
        for (int i = 0; i < points.length; i++) {
            cnt[i] = numPointsLeftStart(points[i], starts)
                    - numPointsLeftEnd(points[i], ends);
        }
        return cnt;
    }

    private static int numPointsLeftStart(int point, int[] arr) {
        int index = Arrays.binarySearch(arr, point);

        if (index < 0) {
            index = -1 * index - 1;
        }

        while (index >= arr.length) {
            index--;
        }
        while (index >= 0 && arr[index] > point) {
            index--;
        }

        while (index < arr.length - 1 && arr[index + 1] <= point) {
            index++;
        }

        return index + 1;
    }

    private static int numPointsLeftEnd(int point, int[] arr) {
        int index = Arrays.binarySearch(arr, point);

        if (index < 0) {
            index = -1 * index - 1;
        }

        while (index >= arr.length) {
            index--;
        }
        while (index >= 0 && arr[index] >= point) {
            index--;
        }

        while (index >= arr.length) {
            index--;
        }
        while (index < arr.length - 1 && arr[index + 1] < point) {
            index++;
        }

        return index + 1;
    }



//    private static int numStartsLeft(int point, int[] starts) {
//        int index = Arrays.binarySearch(starts, point);
//        if (index < 0) {
//            index = -1 * index - 1;
//        }
//        while (index >= 0 && starts[index] > point) {
//            index--;
//        }
//        while (index < starts.length - 1 && starts[index + 1] <= point) {
//            index++;
//        }
//        return index + 1;
//    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws InterruptedException {
//        stresstest();
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }

    private static void stresstest() throws InterruptedException {
        int t = 0;
        while (true) {
            int numSegments = randomWithRange(1, 15);
            int numPoints = randomWithRange(1, 15);

            int starts[] = new int[numSegments];
            int ends[] = new int[numSegments];
            int points[] = new int[numPoints];

            for (int i = 0; i < numSegments; i++) {
                starts[i] = randomWithRange((int) (-1 * Math.pow(10, 8)),
                        (int) (Math.pow(10, 8)) - 1);
                int rand;
                do {
                    rand = randomWithRange((int) (1 + (-1 * Math.pow(10, 8))),
                            (int) (Math.pow(10, 8)));
                } while (rand < starts[i]);
                ends[i] = rand;
            }

            for (int i = 0; i < numPoints; i++) {
                points[i] = randomWithRange((int) (-1 * Math.pow(10, 8)),
                        (int) (Math.pow(10, 8)));
            }

            int[] naiveCnt = naiveCountSegments(starts, ends, points);

            long time = System.currentTimeMillis();
            int[] fastCnt = fastCountSegments(starts, ends, points);
            long total = System.currentTimeMillis() - time;
            if (total > 4500) {
                System.out.println("TIME ELAPSED: " + (total / 1000) + " out " +
                        "of 4.50 seconds.");
                debugInfo(naiveCnt, fastCnt, numSegments, numPoints, starts,
                        ends, points);
                return;
            }
            if (!Arrays.equals(naiveCnt, fastCnt)) {
                debugInfo(naiveCnt, fastCnt, numSegments, numPoints, starts,
                        ends, points);
                return;
            }
            System.out.println("Successful test: " + ++t);
        }
    }

    private static void debugInfo(int[] naiveCnt, int[] fastCnt, int
            numSegments, int numPoints, int[] starts, int[] ends, int[]
            points) throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("ERROR. NAIVE ALGORITHM FOUND A DIFFERENT " +
                "SOLUTION THAN FAST ALGORITHM." +
                " \nDebugging:");
        System.out.println("Number of Segments: " + numSegments);
        System.out.println("Number of Points: " + numPoints);
        System.out.println("Start segments array: \n" + Arrays.toString
                (starts));
        System.out.println("End segments array: \n" + Arrays.toString
                (ends));
        System.out.println("Points array: \n" + Arrays.toString
                (points));
        int[] indices = new int[numPoints];
        for (int i = 0; i < numPoints; i++) {
            indices[i] = i;
        }
        System.out.println("Indices for reference: \n" + Arrays
                .toString(indices));
        System.out.println("Naive count array: \n" + Arrays.toString
                (naiveCnt));
        System.out.println("Fast count array: \n" + Arrays.toString
                (fastCnt));
        System.out.println("Differences in the indices:");
        for (int i = 0; i < fastCnt.length; i++) {
            if(fastCnt[i] != naiveCnt[i])
                System.out.print(i + ", ");

        }
    }


    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}