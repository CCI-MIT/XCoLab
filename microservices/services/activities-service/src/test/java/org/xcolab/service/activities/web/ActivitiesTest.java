package org.xcolab.service.activities.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activities.enums.ActivityProvidersImpl;
import org.xcolab.service.activities.service.ActivitiesService;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ActivitiesController.class)
@ComponentScan("org.xcolab.service.activities")

@ComponentScan("com.netflix.discovery")



@TestPropertySource(
    properties = {
        "cache.active=false",
        "eureka.client.enabled=false"
    }
)
public class ActivitiesTest {

    private MockMvc mockMvc;

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
        MediaType.APPLICATION_JSON.getSubtype(),
        Charset.forName("utf8"));



    @Mock
    private ActivityEntryDao activityEntryDao;

    @Mock
    private ActivitySubscriptionDao activitySubscriptionDao;

    @Mock
    private ActivitiesService activitiesService;


    @InjectMocks
    private ActivitiesController controller;

    @Autowired
    ObjectMapper objectMapper;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @PrepareForTest({
        org.xcolab.service.activities.enums.ActivityProvidersImpl.class
    })

    @Before
    public void before(){

        PowerMockito.mockStatic(ActivityProvidersImpl.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        Mockito.when(activityEntryDao.create(anyObject()))
            .thenAnswer(new Answer<Optional<ActivityEntry>>() {
                @Override
                public Optional<ActivityEntry> answer(InvocationOnMock invocation)
                    throws Throwable {
                    return Optional.of(new ActivityEntry());
                }
            });
    }

    @Test
    public void shouldReturnConfigurationAttributeInGet() throws Exception {

        this.mockMvc.perform(
            post("/activityEntries/createActivityEntry")
            .param("memberId","123l")
            .param("classPrimaryKey","123l")
            .param("extraData","")
            .param("providerType","12")
            .contentType(contentType).accept(contentType))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.stringValue", is("CLIMATE_COLAB")));
    }
}
