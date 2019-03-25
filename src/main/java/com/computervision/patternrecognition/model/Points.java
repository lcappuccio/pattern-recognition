package com.computervision.patternrecognition.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 25/03/2019 14:23
 */
public class Points {

	private final List<Point> pointList = new ArrayList<>();

	public Points(final List<Point> pointList) {
		for (final Point point: pointList) {
			addPoint(point);
		}
	}

	public void addPoint(final Point point) {
		if (!pointList.contains(point)) {
			pointList.add(point);
			return;
		}
		throw new IllegalArgumentException();
	}

	public List<Point> getPointList() {
		return Collections.unmodifiableList(pointList);
	}

	public void clear() {
		pointList.clear();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Points points = (Points) o;
		return Objects.equals(pointList, points.pointList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pointList);
	}
}
