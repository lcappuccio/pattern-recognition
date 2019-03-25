package com.computervision.patternrecognition.util;

import com.computervision.patternrecognition.model.Point;
import com.computervision.patternrecognition.model.Points;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * 25/03/2019 12:24
 */
public class SetCombinatoryUtilTest {

	private Points points;
	private List<Points> pointsSet_2 = new ArrayList<>(), pointsSet_3 = new ArrayList<>();

	@Before
	public void setUp() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(1, 1);
		final Point pointC = new Point(2, 2);
		final Point pointD = new Point(3, 3);
		points = new Points(Arrays.asList(pointA, pointB, pointC, pointD));

		// Combinations of size 2 are A-B, A-C, A-D, B-C, B-D, C-D
		pointsSet_2.add(new Points(Arrays.asList(pointA, pointB)));
		pointsSet_2.add(new Points(Arrays.asList(pointA, pointC)));
		pointsSet_2.add(new Points(Arrays.asList(pointA, pointD)));
		pointsSet_2.add(new Points(Arrays.asList(pointB, pointC)));
		pointsSet_2.add(new Points(Arrays.asList(pointB, pointD)));
		pointsSet_2.add(new Points(Arrays.asList(pointC, pointD)));

		// Combinations of size 3 are A-B-C, A-B-D, A-C-D, B-C-D
		pointsSet_3.add(new Points(Arrays.asList(pointA, pointB, pointC)));
		pointsSet_3.add(new Points(Arrays.asList(pointA, pointB, pointD)));
		pointsSet_3.add(new Points(Arrays.asList(pointA, pointC, pointD)));
		pointsSet_3.add(new Points(Arrays.asList(pointB, pointC, pointD)));
	}

	@Test
	public void should_generate_all_combinations_size_2() {

		final List<Points> subsetsOfSizeN = SetCombinatoryUtil.getSubsetsOfGivenSize(2, points);

		assertEquals(6, subsetsOfSizeN.size());
		assertEquals(pointsSet_2, subsetsOfSizeN);
	}

	@Test
	public void should_generate_all_combinations_size_3() {

		final List<Points> subsetsOfSizeN = SetCombinatoryUtil.getSubsetsOfGivenSize(3, points);

		assertEquals(4, subsetsOfSizeN.size());
		assertEquals(subsetsOfSizeN, pointsSet_3);
	}

	@Test
	public void should_generate_all_combinations_from_size_2_to_3() {

		final List<Points> subsetsOfSizeN = SetCombinatoryUtil.getSubsetsOfGivenSizeOrMore(2, points);

		assertEquals(10, subsetsOfSizeN.size());
		assertTrue(subsetsOfSizeN.containsAll(pointsSet_2));
		assertTrue(subsetsOfSizeN.containsAll(pointsSet_3));
	}
}
