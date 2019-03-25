package com.computervision.patternrecognition.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.*;

/**
 * 25/03/2019 14:25
 */
public class PointsTest {

	private final Points sut = new Points();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_not_add_duplicates() {
		final Point point = new Point(0,0);

		assertTrue(sut.addPoint(point));
		assertFalse(sut.addPoint(point));
		assertEquals(1, sut.getPointList().size());
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
