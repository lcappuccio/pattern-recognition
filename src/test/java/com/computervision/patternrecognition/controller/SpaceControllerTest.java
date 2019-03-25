package com.computervision.patternrecognition.controller;

import com.computervision.patternrecognition.Application;
import com.computervision.patternrecognition.model.Point;
import com.computervision.patternrecognition.model.Points;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(locations = "classpath:application.properties")
public class SpaceControllerTest {

	@Autowired
	private Points mockPoints;

	private MockMvc sut;

	@Before
	public void setUp() {

		mockPoints = mock(Points.class);

		final SpaceController spaceController = new SpaceController(mockPoints);
		sut = MockMvcBuilders.standaloneSetup(spaceController).build();
	}

	@Test
	public void add_point_ok() throws Exception {
		final Point point = new Point(0,0);
		sut.perform(MockMvcRequestBuilders.post("/point")
				.contentType(MediaType.APPLICATION_JSON).content("{\"x\":\"0\", \"y\":\"0\"}".getBytes()))
				.andExpect(status().is(HttpStatus.OK.value()));

		verify(mockPoints).addPoint(point);
	}

	@Test
	public void add_point_ok_with_decimals() throws Exception {
		final Point point = new Point(0.32,0);
		sut.perform(MockMvcRequestBuilders.post("/point")
				.contentType(MediaType.APPLICATION_JSON).content("{\"x\":\"0.32\", \"y\":\"0\"}".getBytes()))
				.andExpect(status().is(HttpStatus.OK.value()));

		verify(mockPoints).addPoint(point);
	}

	@Test
	public void add_point_conflict() throws Exception {
		final Point point = new Point(0,0);
		doThrow(IllegalArgumentException.class).when(mockPoints).addPoint(point);
		sut.perform(MockMvcRequestBuilders.post("/point")
				.contentType(MediaType.APPLICATION_JSON).content("{\"x\":\"0\", \"y\":\"0\"}".getBytes()))
				.andExpect(status().is(HttpStatus.CONFLICT.value()));

		verify(mockPoints).addPoint(point);
	}
}
