package org.xcolab.service.admin.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.xcolab.model.tables.pojos.ConfigurationAttribute;
import org.xcolab.service.admin.AdminTestUtils;
import org.xcolab.service.admin.domain.configurationattribute.ConfigurationAttributeDao;
import org.xcolab.service.admin.pojo.Notification;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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



@TestPropertySource(
    properties = {
        "cache.enabled=false",
        "eureka.client.enabled=false"
    }
)

@WebAppConfiguration
public class AdminControllerTest {


    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));

    @Mock
    private ConfigurationAttributeDao configurationAttributeDao;

    @InjectMocks
    private AdminController controller;

    @Autowired
    ObjectMapper objectMapper;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Before
    public void before(){

       this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        Mockito.when(configurationAttributeDao.getConfigurationAttribute(anyString()))
            .thenAnswer(new Answer<Optional<ConfigurationAttribute>>() {
                @Override
                public Optional<ConfigurationAttribute> answer(InvocationOnMock invocation)
                    throws Throwable {
                    if("ACTIVE_THEMEZ".equals(invocation.getArguments()[0])){
                        return Optional.of(getConfigAttribute());
                    }else{
                        return Optional.empty();
                    }


                }
            });

        //.thenAnswer( Optional.of(getConfigAttribute()));

        Mockito.when(configurationAttributeDao.create(anyObject()))
            .thenAnswer(invocationOnMock -> getConfigAttribute());


    }
    private ConfigurationAttribute getConfigAttribute(){
        ConfigurationAttribute ca = AdminTestUtils.getConfigurationAttribute("a");
        ca.setStringValue("CLIMATE_COLAB");
        return ca;
    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
            this.mappingJackson2HttpMessageConverter);
    }


    @Test
    public void shouldReturnConfigurationAttributeInGet() throws Exception {

        this.mockMvc.perform(get("/attributes/ACTIVE_THEMEZ").contentType(contentType).accept(contentType))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.stringValue", is("CLIMATE_COLAB")));
    }

    @Test
    public void shouldReturn404ForNotFoundConfigurationAttributeInGet() throws Exception {

        this.mockMvc.perform(get("/attributes/ACTIVE_THEME")
            .contentType(contentType).accept(contentType))
            .andExpect(status().isNotFound());
    }


    @Test
    public void shouldCreateNewConfigurationAttributeInPost() throws Exception {


        ConfigurationAttribute ca = getConfigAttribute();
        this.mockMvc.perform(
                post("/attributes")
                    .contentType(contentType).accept(contentType)
                    .content(objectMapper.writeValueAsString(ca)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType));
        Mockito.verify(configurationAttributeDao,atLeast(1)).create(anyObject());
    }

    @Test
    public void shouldUpdateConfigurationAttributeInPut() throws Exception {

        ConfigurationAttribute ca = getConfigAttribute();
        this.mockMvc.perform(
            put("/attributes/"+ca.getName())
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(ca)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType));
        Mockito.verify(configurationAttributeDao,atLeast(1)).update(anyObject());
    }


    private Notification getNotification(Long id ){
        Notification notification = new Notification();
        notification.setBeginTime(new Date());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);

        notification.setEndTime(cal.getTime());
        notification.setNotificationId(id);
        notification.setNotificationText("Notification text");
        return notification;
    }

    @Test
    public void shouldCreateAndDeleteNotificationInPost() throws Exception {

        Notification notification = getNotification(123l);
        this.mockMvc.perform(
            get("/notifications/")
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(notification)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(content().contentType(contentType));
        this.mockMvc.perform(
            post("/notifications/")
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(notification)))
            .andExpect(status().isOk());

        this.mockMvc.perform(
            get("/notifications/")
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(notification)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(content().contentType(contentType));

        this.mockMvc.perform(
            delete("/notifications/"+ notification.getNotificationId())
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(notification)))
            .andExpect(status().isOk());

        this.mockMvc.perform(
            get("/notifications/")
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(notification)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty())
            .andExpect(content().contentType(contentType));

    }

}
