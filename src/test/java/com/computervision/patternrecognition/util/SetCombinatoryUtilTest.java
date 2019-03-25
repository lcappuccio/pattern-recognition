package com.computervision.patternrecognition.util;

import com.computervision.patternrecognition.model.Point;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

/**
 * 25/03/2019 12:24
 */
public class SetCombinatoryUtilTest {

	private List<Point> points;
	private List<List<Point>> pointsSet_2, pointsSet_3;

	@Before
	public void setUp() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(1, 1);
		final Point pointC = new Point(2, 2);
		final Point pointD = new Point(3, 3);
		points = Arrays.asList(pointA, pointB, pointC, pointD);

		// Combinations of size 2 are A-B, A-C, A-D, B-C, B-D, C-D
		pointsSet_2.add(Arrays.asList(pointA, pointB));
		pointsSet_2.add(Arrays.asList(pointA, pointC));
		pointsSet_2.add(Arrays.asList(pointA, pointD));
		pointsSet_2.add(Arrays.asList(pointB, pointC));
		pointsSet_2.add(Arrays.asList(pointB, pointD));
		pointsSet_2.add(Arrays.asList(pointC, pointD));

		// Combinations of size 3 are A-B-C, A-B-D, A-C-D, B-C-D
		pointsSet_3.add(Arrays.asList(pointA, pointB, pointC));
		pointsSet_3.add(Arrays.asList(pointA, pointB, pointD));
		pointsSet_3.add(Arrays.asList(pointA, pointC, pointD));
		pointsSet_3.add(Arrays.asList(pointB, pointC, pointD));
	}
}
