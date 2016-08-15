package org.xcolab.service.members.test.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class MembersControllerTests {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
//		mvc = MockMvcBuilders.standaloneSetup(new MembersController()).build();
	}

//	@Test
//	public void getHello() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get("/members"))
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
//	}
	
}
