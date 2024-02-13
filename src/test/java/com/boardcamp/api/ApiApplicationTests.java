package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") // set application-test.properties
class ApiApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void getHealth() {
		ResponseEntity<String> response = restTemplate.exchange("/health", HttpMethod.GET, null, String.class);
		assertEquals("I'm Ok!", response.getBody());
	}

}
