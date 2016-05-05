package org.xcolab.service.members.test.it;

//TODO: broken test
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = MembersServiceApplication.class)
//@WebAppConfiguration
//@IntegrationTest({"server.port=0"})
//@WebIntegrationTest
//public class MembersIntegrationTest {
//
//	@Value("${local.server.port}")
//    private int port;
//
//	private URL base;
//	private RestTemplate template;
//
//	@Before
//	public void setUp() throws Exception {
//		this.base = new URL("http://localhost:" + port + "/");
//		template = new TestRestTemplate();
//	}
//
//	@Test
//	public void getHello() throws Exception {
//		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
//		assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
//	}
//}
