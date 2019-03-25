package com.computervision.patternrecognition.util;

import Jama.Matrix;
import com.computervision.patternrecognition.model.Point;

import java.util.List;

/**
 * 25/03/2019 11:06
 */
public class CollinearPointsUtil {

	/**
	 * A set of three or more distinct points are collinear if and only if, the matrix of the coordinates of these
	 * vectors is of rank 1 or less. (source: https://en.wikipedia.org/wiki/Collinearity)
	 *
	 * @param pointList a {@link List<Point>} to determine collinearity
	 * @return true if collinear
	 */
	public static boolean isPointSetCollinear(final List<Point> pointList) {

		if (pointList == null) {
			throw new IllegalArgumentException();
		}

		if (pointList.size() == 2) {
			return true;
		}

		double[][] pointMatrix = pointListToMatrix(pointList);
		final Matrix matrix = new Matrix(pointMatrix);
		int rank = matrix.rank();

		return rank <= 1;
	}

	/**
	 * Convert a {@link List<Point>} to a bidimensional Point matrix
	 *
	 * @param pointList
	 * @return
	 */
	private static double[][] pointListToMatrix(final List<Point> pointList) {

		double[][] pointsMatrix = new double[pointList.size()][2];
		for (int i = 0; i < pointList.size(); i++) {
			final Point point = pointList.get(i);
			pointsMatrix[i][0] = point.getX();
			pointsMatrix[i][1] = point.getY();
		}
		return pointsMatrix;
	}
}
