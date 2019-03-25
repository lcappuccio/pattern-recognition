package com.computervision.patternrecognition.controller;

import com.computervision.patternrecognition.model.Point;
import com.computervision.patternrecognition.model.Points;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
	public ResponseEntity<HttpStatus> addPoint(@RequestBody @Valid final Point point)  {

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
	public ResponseEntity<Points> getPoints()  {

		return new ResponseEntity<>(points, HttpStatus.OK);
	}
}
