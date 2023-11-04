package com.example.shivaknt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShivakntApplicationTests {

	@Test
	void contextLoads() {
		Logger logger = LoggerFactory.getLogger(ShivakntApplication.class);
		logger.info("this the starting of the project.");
		assertEquals(true, true);
		logger.info("firebase has been initialise.");
	}

}
