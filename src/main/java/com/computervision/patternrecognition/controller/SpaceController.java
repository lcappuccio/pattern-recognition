package com.computervision.patternrecognition.controller;

import com.computervision.patternrecognition.model.Point;
import com.computervision.patternrecognition.model.Points;
import com.computervision.patternrecognition.util.CollinearPointsUtil;
import com.computervision.patternrecognition.util.SetCombinatoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class SpaceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpaceController.class);
	private final Points points;
	private final CollinearPointsUtil collinearPointsUtil;
	private final SetCombinatoryUtil setCombinatoryUtil;

	@Autowired
	public SpaceController(
			final Points points,
			final CollinearPointsUtil collinearPointsUtil,

			final SetCombinatoryUtil setCombinatoryUtil) {
		this.points = points;
		this.collinearPointsUtil = collinearPointsUtil;
		this.setCombinatoryUtil = setCombinatoryUtil;
	}

	@RequestMapping(value = "point", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> addPoint(@RequestBody @Valid final Point point) {

		try {
			points.addPoint(point);
			LOGGER.info(String.format("Added point x: %s, y: %s", point.getX(), point.getY()));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IllegalArgumentException exception) {
			LOGGER.error(String.format("Point x: %s, y: %s already exists", point.getX(), point.getY()));
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "space", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Point>> getPoints() {

		final List<Point> pointList = points.getPointList();
		return new ResponseEntity<>(pointList, HttpStatus.OK);
	}

	@RequestMapping(value = "space", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Point>> removeAllPoints() {

		points.clear();
		LOGGER.info("Points in space removed");
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "lines/{n}", method = RequestMethod.GET, produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<List<Point>>> getLongestLineSegmentOfAtLeast(
			@PathVariable(value = "n") final int collinearityGrade) {

		final List<Points> collinearPointSet = new ArrayList<>();
		final List<Points> subsetsOfGivenSizeOrMore =
				setCombinatoryUtil.getSubsetsOfGivenSizeOrMore(collinearityGrade, points);
		for (final Points points : subsetsOfGivenSizeOrMore) {
			if (collinearPointsUtil.isPointSetCollinear(points)) {
				collinearPointSet.add(points);
			}
		}

		final List<Points> largestCollinearPointSet = getLargestCollinearSetsFrom(collinearPointSet);

		// extract raw list as specified in requirement
		final List<List<Point>> formattedPointsWithoutJsonProperty = new ArrayList<>();
		for (final Points points : largestCollinearPointSet) {
			formattedPointsWithoutJsonProperty.add(points.getPointList());
		}

		return new ResponseEntity<>(formattedPointsWithoutJsonProperty, HttpStatus.OK);
	}

	/**
	 * Extracts largets Points set from a {@link List<Points>}
	 *
	 * @param collinearPointSet a collinear {@link List<Points>} set
	 * @return
	 */
	private List<Points> getLargestCollinearSetsFrom(final List<Points> collinearPointSet) {

		int largestSetSize = 0;
		for (final Points collinearPoints : collinearPointSet) {
			final List<Point> collinearPointList = collinearPoints.getPointList();
			if (collinearPointList.size() > largestSetSize) {
				largestSetSize = collinearPointList.size();
			}
		}

		final List<Points> largestCollinearPointSet = new ArrayList<>();
		for (final Points pointsInSet : collinearPointSet) {
			if (pointsInSet.getPointList().size() >= largestSetSize) {
				largestCollinearPointSet.add(pointsInSet);
			}
		}
		return largestCollinearPointSet;
	}
}