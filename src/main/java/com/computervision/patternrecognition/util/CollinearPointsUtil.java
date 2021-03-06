package com.computervision.patternrecognition.util;

import Jama.Matrix;
import com.computervision.patternrecognition.model.Point;
import com.computervision.patternrecognition.model.Points;

import java.util.List;

/**
 * 25/03/2019 11:06
 */
public class CollinearPointsUtil {

	/**
	 * A set of three or more distinct points are collinear if and only if, the matrix of the coordinates of these
	 * vectors is of rank 2 or less. (source: https://en.wikipedia.org/wiki/Collinearity)
	 *
	 * @param points a {@link Points} to determine collinearity
	 * @return true if collinear
	 */
	public boolean isPointSetCollinear(final Points points) {

		if (points == null) {
			throw new IllegalArgumentException();
		}

		final List<Point> pointList = points.getPointList();

		if (pointList.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (pointList.size() <= 2) {
			return true;
		}

		double[][] pointMatrix = pointListToMatrix(pointList);
		final Matrix matrix = new Matrix(pointMatrix);
		int rank = matrix.rank();

		return rank <= 2;
	}

	/**
	 * Convert a {@link List<Point>} to a bidimensional Point matrix
	 *
	 * @param pointList
	 * @return
	 */
	private double[][] pointListToMatrix(final List<Point> pointList) {

		final double[][] pointsMatrix = new double[pointList.size()][pointList.size()];
		// initialize with 1
		for (int i = 0; i < pointsMatrix.length; i++) {
			for (int j = 0; j < pointsMatrix[i].length; j++) {
				pointsMatrix[i][j] = 1;
			}
		}
		for (int i = 0; i < pointList.size(); i++) {
			final Point point = pointList.get(i);
			pointsMatrix[i][0] = point.getX();
			pointsMatrix[i][1] = point.getY();
		}
		return pointsMatrix;
	}
}
