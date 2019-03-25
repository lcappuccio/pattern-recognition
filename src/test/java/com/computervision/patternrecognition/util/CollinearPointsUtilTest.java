package com.computervision.patternrecognition.util;

import com.computervision.patternrecognition.model.Point;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * 25/03/2019 11:11
 */
public class CollinearPointsUtilTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void invalid_argument() {

		expectedException.expect(IllegalArgumentException.class);
		//noinspection ConstantConditions
		CollinearPointsUtil.isPointSetCollinear(null);
	}

	@Test
	public void empty_list() {
		expectedException.expect(IllegalArgumentException.class);
		CollinearPointsUtil.isPointSetCollinear(Collections.<Point>emptyList());
	}

	@Test
	public void point_is_collinear_with_itself() {
		final Point point = new Point(0, 0);
		final List<Point> pointList = Collections.singletonList(point);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertTrue("Point is collinear with itself", isPointSetCollinear);
	}

	@Test
	public void two_points_are_always_collinear() {
		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(0.1, 0.2);
		final List<Point> pointList = Arrays.asList(pointA, pointB);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertTrue("Two points are always collinear", isPointSetCollinear);
	}

	@Test
	public void should_be_collinear() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(1, 1);
		final Point pointC = new Point(2, 2);
		final List<Point> pointList = Arrays.asList(pointA, pointB, pointC);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertTrue("Point set is collinear but returns false", isPointSetCollinear);
	}

	@Test
	public void should_be_collinear_with_negative() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(1, 1);
		final Point pointC = new Point(2, 2);
		final Point pointNegatives = new Point(-2, -2);
		final List<Point> pointList = Arrays.asList(pointA, pointB, pointC, pointNegatives);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertTrue("Point set is collinear but returns false", isPointSetCollinear);
	}

	@Test
	public void should_be_collinear_only_negatives() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(-1, -1);
		final Point pointC = new Point(-2, -2);
		final Point pointNegatives = new Point(-8, -8);
		final List<Point> pointList = Arrays.asList(pointA, pointB, pointC, pointNegatives);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertTrue("Point set is collinear but returns false", isPointSetCollinear);
	}

	@Test
	public void should_not_be_collinear() {

		final Point pointA = new Point(0, 0);
		final Point pointB = new Point(1, 1);
		final Point pointC = new Point(1, 3);
		final List<Point> pointList = Arrays.asList(pointA, pointB, pointC);

		boolean isPointSetCollinear = CollinearPointsUtil.isPointSetCollinear(pointList);

		assertFalse("Point set is not collinear but returns true", isPointSetCollinear);
	}
}
