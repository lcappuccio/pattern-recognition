package com.computervision.patternrecognition.util;

import com.computervision.patternrecognition.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * 25/03/2019 12:24
 */
public class SetCombinatoryUtil {

	/**
	 * Extracts sets of size {@param setSize} or more from {@link List<Point>}
	 *
	 * @param setSize   the minimun size of of {@link Point} in each set
	 * @param pointList
	 * @return all sets of size {@param setSize} or more than can be obtained from {@param pointList}
	 */
	public static List<List<Point>> getSubsetsOfGivenSizeOrMore(final int setSize, final List<Point> pointList) {

		final List<List<Point>> allPossibleSubsets = new ArrayList<>();

		for (int i = setSize; i < pointList.size(); i++) {
			allPossibleSubsets.addAll(getSubsetsOfGivenSize(i, pointList));
		}

		return allPossibleSubsets;
	}

	/**
	 * Returns all set combinations with a specified amount of items for a given set of {@link List<Point>}
	 * Does not use recursion, see:
	 * https://stackoverflow.com/questions/29910312/algorithm-to-get-all-the-combinations-of-size-n-from-an-array-java
	 *
	 * @param combinationSize the amount of {@link Point} in each set
	 * @param pointList
	 * @return
	 */
	static List<List<Point>> getSubsetsOfGivenSize(final int combinationSize, final List<Point> pointList) {

		final List<List<Point>> subsets = new ArrayList<>();

		// indexes pointing to elements
		int[] positionIndex = new int[combinationSize];

		if (combinationSize < pointList.size()) {
			for (int i = 0; (positionIndex[i] = i) < combinationSize - 1; i++) ;
			final List<Point> subset = getSubset(pointList, positionIndex);
			subsets.add(subset);
			for (; ; ) {
				int i;
				// find position of item that can be incremented
				for (i = combinationSize - 1;
				     i >= 0 && positionIndex[i] == pointList.size() - combinationSize + i;
				     i--);
				if (i < 0) {
					break;
				}
				// increment this item
				positionIndex[i]++;
				// fill up remaining items
				for (++i; i < combinationSize; i++) {
					positionIndex[i] = positionIndex[i - 1] + 1;
				}
				final List<Point> subsetOther = getSubset(pointList, positionIndex);
				subsets.add(subsetOther);
			}
		}

		return subsets;
	}

	/**
	 * Generates subset with the positions in {@param subset}
	 *
	 * @param pointList the full {@link List<Point>}
	 * @param subset    the array with the index position of the set (e.g. 0-1-2, 0-1-3, 0-2-3, 1-2-3)
	 * @return
	 */
	private static List<Point> getSubset(List<Point> pointList, int[] subset) {
		final List<Point> result = new ArrayList<>(subset.length);
		for (int i = 0; i < subset.length; i++)
			result.add(i, pointList.get(subset[i]));

		return result;
	}
}
