/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.samrash.math;

/**
 * "binary search" square root estimator. For explanatory purposes only
 */
public class SquareRootEstimator
{
    private SquareRootEstimator() {}

    private static final double THRESHOLD = Double.MIN_VALUE * 1000.0;
    private static final long MAX_ITERATIONS = 10000;

    public static double estimateSquareRoot(double x)
    {
        double x2 = x * x;
        double factor = 0.5;
        double left = 0.0;
        double right;

        if (x2 < x) {
            right = 1.0;
        }
        else if (x2 > x) {
            right = x;
        }
        else {
            return x;
        }

        double guess = factor * (left + right);
        long iterations = 0;

        double g2 = guess * guess;
        while (Math.abs(g2 - x) >= THRESHOLD && iterations++ < MAX_ITERATIONS) {
            if (g2 > x) {
                right = guess;
            }
            else {
                left = guess;
            }

            guess = factor * (left + right);
            g2 = guess * guess;
        }

        System.err.println("iter: " + iterations);
        return guess;
    }

    public static void main(String[] args)
    {
        System.err.println(estimateSquareRoot(0));
        System.err.println(estimateSquareRoot(1));
        System.err.println(estimateSquareRoot(1.1));
        System.err.println(estimateSquareRoot(2));
        System.err.println(estimateSquareRoot(4));
        System.err.println(estimateSquareRoot(25));
        System.err.println(estimateSquareRoot(16));
        System.err.println(estimateSquareRoot(100));
        System.err.println(estimateSquareRoot(105));
        System.err.println(estimateSquareRoot(0.5));
        System.err.println(estimateSquareRoot(Integer.MAX_VALUE));
    }
}
