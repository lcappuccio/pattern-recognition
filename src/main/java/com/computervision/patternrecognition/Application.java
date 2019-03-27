package com.computervision.patternrecognition;

import com.computervision.patternrecognition.model.Points;
import com.computervision.patternrecognition.util.CollinearPointsUtil;
import com.computervision.patternrecognition.util.SetCombinatoryUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Points getPoints() {
		return new Points(Collections.emptyList());
	}

	@Bean
	public CollinearPointsUtil getCollinearPointsUtil() {
		return new CollinearPointsUtil();
	}

	@Bean
	public SetCombinatoryUtil getSetCombinatoryUtil() {
		return new SetCombinatoryUtil();
	}
}
