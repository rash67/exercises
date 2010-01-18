package samrash.sort;

import java.util.*;

public class QuickSort
{
	public static <T extends Comparable<? super T>> List<T> sort(List<T> input)
	{
		return sort(input, new Comparator<T>()
		{
			@Override
			public int compare(T o1, T o2)
			{
				return o1.compareTo(o2);
			}
		});
	}

	public static <T> List<T> sort(List<T> input, Comparator<T> comparator)
	{
		subSort(input, 0, input.size() - 1, comparator);

		return input;
	}

	private static <T> void subSort(List<T> input, int start, int end, Comparator<T> comparator)
	{
		if (start < end) {
//			int pivotIndex = new Random().nextInt(end - start + 1) + start;
			int pivotIndex = (start + end) / 2;
			int splitLocation = partition(input, start, end, pivotIndex, comparator);

			subSort(input, start, splitLocation - 1, comparator);
			subSort(input, splitLocation + 1, end, comparator);
		}
	}

	private static <T> int partition(List<T> input, int start, int end, int pivotIndex, Comparator<T> comparator)
	{
		T pivotValue = input.get(pivotIndex);
		int storeIndex = start;

		swap(input, pivotIndex, end);

		for (int i = start; i <= end - 1; i++) {
			T current = input.get(i);

			if (comparator.compare(current, pivotValue) <= 0) {
				swap(input, i, storeIndex++);
			}
		}

		swap(input, end, storeIndex);

		return storeIndex;
	}

	private static <T> void swap(List<T> list, int i, int j)
	{
		T tmp = list.get(i);

		list.set(i, list.get(j));
		list.set(j, tmp);
	}

	public static void main(String[] args)
	{
		List<Integer> list = Arrays.asList(1, 14, 143, 14, 31, 34, 51, 5, 1, 5, 1, 2);

		System.err.println(list);
		System.err.println(sort(list));
	}
}