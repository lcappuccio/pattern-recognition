package com.computervision.patternrecognition.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * 25/03/2019 14:25
 */
public class PointsTest {

	private final Points sut = new Points(Collections.emptyList());

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_add() {
		final Point point = new Point(0,0);
		sut.addPoint(point);

		assertTrue(sut.getPointList().contains(point));
		assertEquals(1, sut.getPointList().size());
	}

	@Test
	public void should_not_add_duplicates() {
		final Point point = new Point(0,0);
		sut.addPoint(point);

		expectedException.expect(IllegalArgumentException.class);
		sut.addPoint(point);
	}

	@Test
	public void should_not_add_duplicates_from_constructor() {
		final Point point = new Point(0,0);
		final List<Point> pointList = Arrays.asList(point, point);

		expectedException.expect(IllegalArgumentException.class);
		final Points sut = new Points(pointList);
	}

	@Test
	public void should_be_unmodifiable() {

		expectedException.expect(UnsupportedOperationException.class);
		sut.getPointList().clear();
	}

	@Test
	public void should_be_cleared() {
		final Point point = new Point(0,0);
		sut.addPoint(point);
		sut.clear();

		assertEquals(0, sut.getPointList().size());
	}
}
