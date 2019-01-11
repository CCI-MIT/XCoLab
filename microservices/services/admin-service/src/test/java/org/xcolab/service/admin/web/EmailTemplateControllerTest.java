package org.xcolab.service.admin.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.service.admin.AdminTestUtils;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(AdminController.class)
@ComponentScan("org.xcolab.service.admin")
@ComponentScan("com.netflix.discovery")
@ActiveProfiles("test")
@WebAppConfiguration
public class EmailTemplateControllerTest {

    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Mock
    private EmailTemplateDao emailTemplateDao;

    @InjectMocks
    private EmailTemplateController controller;

    @Autowired
    private ObjectMapper objectMapper;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Mockito.when(emailTemplateDao.getEmailTemplate(anyString()))
                .thenAnswer(invocation -> AdminTestUtils.getContestEmailTemplate());
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }


    @Test
    public void shouldCreateNewEmailTemplatesInPost() throws Exception {
        IEmailTemplate contestEmailTemplate = AdminTestUtils.getContestEmailTemplate();
        this.mockMvc.perform(
                post("/emailTemplates")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(contestEmailTemplate)))
                .andExpect(status().isOk());
        Mockito.verify(emailTemplateDao, atLeast(1)).createEmailTemplate(anyObject());
    }

    @Test
    public void shouldUpdateEmailTemplatesInPost() throws Exception {
        IEmailTemplate contestEmailTemplate = AdminTestUtils.getContestEmailTemplate();
        this.mockMvc.perform(
                put("/emailTemplates")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(contestEmailTemplate)))
                .andExpect(status().isOk());

        Mockito.verify(emailTemplateDao, atLeast(1)).updateEmailTemplate(anyObject());
    }

    @Test
    public void listEmailTemplates() throws Exception {
        this.mockMvc.perform(
                get("/emailTemplates")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().contentType(contentType));
        Mockito.verify(emailTemplateDao, atLeast(1)).listAllEmailTemplates();
    }
}
