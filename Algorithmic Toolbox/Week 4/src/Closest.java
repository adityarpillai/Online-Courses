import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    25 May 2017
 *
 *  Description:    In this problem, your goal is to find the closest pair of
 *                  points among the given 𝑛 points. This is a basic
 *                  primitive in computational geometry having applications
 *                  in, for example, graphics, computer vision,
 *                  traffic-control systems.
 *
 *                  Given 𝑛 points on a plane, find the smallest distance
 *                  between a pair of two (different) points. Recall that the
 *                  distance between points (𝑥1, 𝑦1) and (𝑥2, 𝑦2) is equal to
 *                  √︀ (𝑥1 − 𝑥2) 2 + (𝑦1 − 𝑦2) 2.
 *
 *  Compilation:    javac Closest.java
 *  Execution:      java Closest
 *
 *  Input:          The first line contains the number 𝑛 of points. Each of
 *                  the following 𝑛 lines defines a point (𝑥𝑖 , 𝑦𝑖).
 *  Constraints:    1 ≤ 𝑛 ≤ 105; −109 ≤ 𝑥𝑖 , 𝑦𝑖 ≤ 109 are integers
 *  Output:         Output the minimum distance. The absolute value of the
 *                  difference between the answer of your program and the
 *                  optimal value should be at most 10−3 . To ensure this,
 *                  output your answer with at least four digits after the
 *                  decimal point (otherwise your answer, while being
 *                  computed correctly, can turn out to be wrong because of
 *                  rounding issues).
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
public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(@SuppressWarnings("NullableProblems") Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    private static double minDistance(ArrayList<Point> points) {
        ArrayList<Point> sortedByX = new ArrayList<>(points);

        sortedByX.sort((o1, o2) -> {
            double diff = o1.x - o2.x;
            if (diff < 0)
                return -1;
            if (diff > 0)
                return +1;
            return 0;
        });

        ArrayList<Point> sortedByY = new ArrayList<>(points);

        sortedByY.sort((o1, o2) -> {
            double diff = o1.y - o2.y;
            if (diff < 0)
                return -1;
            if (diff > 0)
                return 1;
            return 0;
        });

        return minimalDistance(sortedByX);
    }

    private static double minimalDistance(ArrayList<Point> list) {
        int size = list.size();

        if (size <= 4)
            return naiveMinimalDistance(list);

        // recurse through both left and right sides
        int cutInd = size / 2;
        ArrayList<Point> left = new ArrayList<>();
        for (int i = 0; i < cutInd; i++) {
            left.add(list.get(i));
        }
        double minLeft = minimalDistance(left);
        ArrayList<Point> right = new ArrayList<>();
        for (int i = cutInd; i < size; i++) {
            right.add(list.get(i));
        }
        double minRight = minimalDistance(right);

        // get minimum of left and right sides
        double min = Math.min(minLeft, minRight);
        double center = list.get(cutInd).x;

        ArrayList<Point> tempSet = new ArrayList<>();
        for (Point p : list) {
            if (Math.abs(p.x - center) < min) {
                tempSet.add(p);
            }
        }

        return Math.min(min, minimalDistance(tempSet));
    }

    public static void main(String[] args) throws InterruptedException {
        stresstest();
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        ArrayList<Point> points = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            points.add(new Point(scan.nextInt(), scan.nextInt()));
//        }
//        System.out.println(minDistance(points));
//        System.out.println(naiveMinimalDistance(points));

    }

    private static void stresstest() throws InterruptedException {
        int test = 1;
        while (true) {
            Scanner scan = new Scanner(System.in);
            int n = getRandBetween(1, Math.pow(10, 5));
            ArrayList<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int x = getRandBetween((int) (-1 * Math.pow(10, 9)), Math.pow(10, 9));
                int y = getRandBetween((int) (-1 * Math.pow(10, 8)), Math.pow(10, 9));
                points.add(new Point(x, y));
            }
            double fast = minDistance(points);
            double slow = naiveMinimalDistance(points);
            if (fast != slow) {
                debug(points, fast, slow);
                return;
            }
            System.out.println("Test #" + test + ": SUCCESSFUL");
            test++;
        }
    }

    private static void debug(ArrayList<Point> points, double fast, double slow) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("BUG DISCOVERED, DEBUGGING: \n");
        System.out.println("Points array: ");

        StringBuilder pointsArr = new StringBuilder("[");
        for (int i = 0; i < points.size(); i++) {
            pointsArr.append(i).append(": (").append(points.get(i).x).append
                    (",").append(points.get(i).y).append(")");
            if (i != points.size() - 1) {
                pointsArr.append(", ");
            }
        }
        pointsArr.append("]");

        System.out.println(pointsArr);
        System.out.println("Fast Solution: " + fast);
        System.out.println("Slow solution: " + slow);
    }

    private static int getRandBetween(int min, double max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    private static double naiveMinimalDistance(ArrayList<Point> points) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.size() - 1; i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double dist = calcDist(points.get(i), points.get(j));
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    private static double calcDist(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y
                - point2.y, 2));
    }
}

