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

	@Autowired
	public SpaceController(final Points points) {
		this.points = points;
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
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "lines/{n}", method = RequestMethod.GET, produces =
			MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<List<Point>>> getLongestLineSegmentOfAtLeast(
			@PathVariable(value = "n") final int collinearityGrade) {

		final List<List<Point>> collinearPointSet = new ArrayList<>();
		final List<Points> subsetsOfGivenSizeOrMore = SetCombinatoryUtil.getSubsetsOfGivenSizeOrMore(
				collinearityGrade,
				points);
		for(Points points: subsetsOfGivenSizeOrMore) {
			if (CollinearPointsUtil.isPointSetCollinear(points)) {
				collinearPointSet.add(points.getPointList());
			}
		}

		int largestSetSize = 0;
		for (List<Point> points1 : collinearPointSet) {
			if (points1.size() > largestSetSize) {
				largestSetSize = points1.size();
			}
		}

		final List<List<Point>> largestCollinearPointSet = new ArrayList<>();
		for (List<Point> pointsInSet : collinearPointSet) {
			if (pointsInSet.size() >= largestSetSize) {
				largestCollinearPointSet.add(pointsInSet);
			}
		}

		return new ResponseEntity<>(largestCollinearPointSet, HttpStatus.OK);
	}
}