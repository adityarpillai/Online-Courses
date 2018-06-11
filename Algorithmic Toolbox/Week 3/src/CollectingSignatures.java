import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/******************************************************************************
 *  Name:    Aditya Pillai
 *  Date:    14 May 2017
 *
 *  Description:    Given a set of 𝑛 segments {[𝑎0, 𝑏0], [𝑎1, 𝑏1], . . . , [𝑎𝑛−1,
 *                  𝑏𝑛−1]} with integer coordinates on a line, find the minimum
 *                  number 𝑚 of points such that each segment contains at least
 *                  one point. That is, find a set of integers 𝑋 of the minimum
 *                  size such that for any segment [𝑎𝑖 , 𝑏𝑖] there is a point
 *                  𝑥 ∈ 𝑋 such that 𝑎𝑖 ≤ 𝑥 ≤ 𝑏𝑖.
 *
 *  Compilation:    javac CollectingSignatures.java
 *  Execution:      java CollectingSignatures
 *
 *  Input:          The first line of the input contains the number 𝑛 of
 *                  segments. Each of the following 𝑛 lines contains two
 *                  integers 𝑎𝑖 and 𝑏𝑖 (separated by a space) defining the
 *                  coordinates of endpoints of the 𝑖-th segment.
 *  Constraints:    1 ≤ 𝑛 ≤ 100; 0 ≤ 𝑎𝑖 ≤ 𝑏𝑖 ≤ 109 for all 0 ≤ 𝑖 < 𝑛.
 *  Output:         Output the minimum number 𝑚 of points on the first line and
 *                  the integer coordinates of 𝑚 points (separated by spaces)
 *                  on the second line. You can output the points in any order.
 *                  If there are many such sets of points, you can output any
 *                  set. (It is not difficult to see that there always exist a
 *                  set of points of the minimum size such that all the
 *                  coordinates of the points are integers.)
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
public class CollectingSignatures {

    private static ArrayList<Integer> optimalPoints
            (ArrayList<Segment> segments) {

        ArrayList<Integer> ret = new ArrayList<>();

        while (segments.size() > 0) {
            Segment first = segments.get(0);
            for (int i = segments.size() - 1; i >= 0; i--) {
                if (segments.get(i).start <= first.end
                        && segments.get(i).end >= first.end) {
                    segments.remove(i);
                }
            }
            ret.add(first.end);
        }
        return ret;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        Collections.sort(segments);
        ArrayList<Integer> ans = optimalPoints(segments);
        System.out.println(ans.size());
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }

    private static class Segment implements Comparable<Segment> {

        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(@NotNull Segment that) {
            return end - that.end;
        }

    }

}
