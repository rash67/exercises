package samrash.sort;

import java.util.*;

public class IterativeMergeSort
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
		List<List<T>> listOfLists1 = split(input);
		List<List<T>> listOfLists2 = new ArrayList<List<T>>(input.size() / 2);

		while (listOfLists1.size() > 1) {
			for (int i = 0; i < listOfLists1.size(); i += 2) {
				if (i == listOfLists1.size() - 1) {
					listOfLists2.add(listOfLists1.get(i));
				}
				else {
					listOfLists2.add(merge(listOfLists1.get(i), listOfLists1.get(i + 1), comparator));
				}
			}

			List<List<T>> tmp  = listOfLists1;

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
		List<List<T>> listOfLists = new ArrayList<List<T>>();

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
		List<Integer> list = Arrays.asList(1,14,143,14,31,34,51,5,1,5,1,2);

		System.err.println(list);
		System.err.println(sort(list));
	}
}
