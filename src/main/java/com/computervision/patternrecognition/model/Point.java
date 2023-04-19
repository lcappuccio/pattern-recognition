package com.computervision.patternrecognition.model;

import java.io.Serializable;
import java.util.Objects;

public class Point implements Serializable {

	private static final long serialVersionUID = -7230969758904327004L;
	private double x, y;

	public Point() {
	}

	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point point = (Point) o;
		return Double.compare(point.x, x) == 0 &&
				Double.compare(point.y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
