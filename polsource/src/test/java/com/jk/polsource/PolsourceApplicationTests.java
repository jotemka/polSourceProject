package com.jk.polsource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@TestPropertySource(locations = "classpath:application-test.properties")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class PolsourceApplicationTests {

//	private static MariaDB4jSpringService DB;
//
//	@Autowired
//	private MockMvc mvc;
//
//	@MockBean
//	private
//
//	@BeforeClass
//	public static void init() throws ManagedProcessException {
//		DB = new MariaDB4jSpringService();
//		DB.start();
//		DB.getDB().createDB("notes");
//		DB.getDB().source("schema.sql");
//	}

//	@AfterClass
//	public static void cleanup(){
//		if(DB != null) DB.stop();
//	}



	@Test
	void contextLoads() {
		assertTrue(true);
	}




}
