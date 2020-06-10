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

package com.samrash.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IterativeMergeSort
{
    private IterativeMergeSort() {}

    public static <T extends Comparable<? super T>> List<T> sort(List<T> input)
    {
        return sort(input, Comparator.naturalOrder());
    }

    public static <T> List<T> sort(List<T> input, Comparator<T> comparator)
    {
        List<List<T>> listOfLists1 = split(input);
        List<List<T>> listOfLists2 = new ArrayList<>(input.size() / 2);

        while (listOfLists1.size() > 1) {
            for (int i = 0; i < listOfLists1.size(); i += 2) {
                if (i == listOfLists1.size() - 1) {
                    listOfLists2.add(listOfLists1.get(i));
                }
                else {
                    listOfLists2.add(merge(listOfLists1.get(i), listOfLists1.get(i + 1), comparator));
                }
            }

            List<List<T>> tmp = listOfLists1;

            listOfLists1 = listOfLists2;
            listOfLists2 = tmp;
            listOfLists2.clear();
        }

        if (listOfLists1.size() == 1) {
            return listOfLists1.get(0);
        }
        else {
            return Collections.emptyList();
        }
    }

    private static <T> List<List<T>> split(List<T> input)
    {
        List<List<T>> listOfLists = new ArrayList<>();

        for (T item : input) {
            List<T> list = new ArrayList<T>();

            list.add(item);
            listOfLists.add(list);
        }

        return listOfLists;
    }

    private static <T> List<T> merge(List<T> left, List<T> right, Comparator<T> comparator)
    {
        List<T> result = new ArrayList<T>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() || rightIndex < right.size()) {
            if (leftIndex >= left.size()) {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
            else if (rightIndex >= right.size()) {
                result.add(left.get(leftIndex));
                leftIndex++;
            }
            else if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                result.add(left.get(leftIndex));
                leftIndex++;
            }
            else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        return result;
    }

    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1, 14, 143, 14, 31, 34, 51, 5, 1, 5, 1, 2);

        System.err.println(list);
        System.err.println(sort(list));
    }
}
