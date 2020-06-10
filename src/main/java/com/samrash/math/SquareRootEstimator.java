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

public class SquareRootEstimator
{
	private static final double THRESHOLD = Double.MIN_VALUE * 10.0;
	private static final long MAX_ITERATIONS = 1000000;

	public static double sqrt(double x)
	{
		double left = 1.0;
		double right = 0.5 * x;
		double guess = 0.5 * (left + right);
		double g2 = guess * guess;
		long iterations = 0;

		while (Math.abs(g2 - x) >= THRESHOLD && iterations++ < MAX_ITERATIONS) {
			if (g2 > x) {
				right = guess;
			}
			else {
				left = guess;
			}

			guess = 0.5 * (left + right);
			g2 = guess * guess;
		}

		return guess;
	}

	public static void main(String[] args)
	{
		System.err.println(sqrt(25));
		System.err.println(sqrt(16));
		System.err.println(sqrt(100));
		System.err.println(sqrt(105));
	}
}
