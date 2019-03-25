package com.computervision.patternrecognition.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 25/03/2019 14:23
 */
public class Points {

	private final List<Point> pointList = new ArrayList<>();

	public boolean addPoint(final Point point) {
		if (!pointList.contains(point)) {
			pointList.add(point);
			return true;
		}
		return false;
	}

	public List<Point> getPointList() {
		return Collections.unmodifiableList(pointList);
	}

	public void clear() {
		pointList.clear();
	}
}
