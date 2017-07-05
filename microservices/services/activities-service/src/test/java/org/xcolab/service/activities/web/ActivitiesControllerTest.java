package org.xcolab.service.activities.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.activityentry.member.MemberJoinedActivityEntry;
import org.xcolab.service.activities.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activities.enums.ActivityProvidersImpl;
import org.xcolab.service.activities.service.ActivitiesService;
import org.xcolab.service.activities.utils.Utils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.activity.ActivityEntryType;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)

@WebMvcTest(ActivitiesController.class)
@ComponentScan("org.xcolab.service.activities")

@ComponentScan("com.netflix.discovery")
@PrepareForTest({
    org.xcolab.service.activities.enums.ActivityProvidersImpl.class

})


@TestPropertySource(
    properties = {
        "cache.enabled=false",
        "eureka.client.enabled=false"
    }
)
public class ActivitiesControllerTest {

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
    public void before() {

        PowerMockito.mockStatic(ActivityProvidersImpl.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        MemberJoinedActivityEntry mjae = Mockito.mock(MemberJoinedActivityEntry.class);
        Mockito.when(ActivityProvidersImpl.getActivityEntryContentProviderByType(anyInt()))
            .thenAnswer(invocation -> mjae);

        Mockito.when(activityEntryDao.create(anyObject()))
            .thenAnswer(invocation -> new ActivityEntry());

        Mockito.when(activitySubscriptionDao.get(anyLong()))
            .thenAnswer(invocation -> Optional.of(new ActivitySubscription()));
    }

    @Test
    public void shouldCreateNewActivityEntryInPost() throws Exception {

        this.mockMvc.perform(
            post("/activityEntries/createActivityEntry")
            .param("memberId","123")
            .param("classPrimaryKey","123")
            .param("extraData","")
            .param("providerType","12")
            .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());
        Mockito.verify(activityEntryDao,Mockito.times(1));
    }
    @Test
    public void shouldGetActivityEntry() throws Exception {

        this.mockMvc.perform(
            get("/activityEntries/1234")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());
        Mockito.verify(activityEntryDao,Mockito.times(1)).get(1234L);
    }

    @Test
    public void shouldGetActivityEntriesWithActivitiesAfter() throws Exception {

        String activitiesAfter = "2016-01-01 00:00:00";
        this.mockMvc.perform(get("/activityEntries").param("activitiesAfter", activitiesAfter).contentType(contentType).accept(contentType)).andExpect(status().isOk());
        Mockito.verify(activityEntryDao, Mockito.times(1))
            .getActivitiesAfter(Utils.parseDate(activitiesAfter));
    }
    @Test
    public void shouldGetActivityEntries() throws Exception {
        PaginationHelper ph = new PaginationHelper(0,10,"");
        this.mockMvc.perform(
            get("/activityEntries")
                .param("startRecord","0")
                .param("limitRecord","10")
                .param("sort","")
        .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());
        Mockito.verify(activityEntryDao,Mockito.times(1))
            .findByGiven(anyObject(),anyObject(),anyObject());
    }

    @Test
    public void shouldCountActivityEntries() throws Exception {
        this.mockMvc.perform(
            get("/activityEntries/count")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());
        Mockito.verify(activityEntryDao,Mockito.times(1))
            .countByGiven(anyObject(),anyObject());
    }

    @Test
    public void shouldCreateActivitySubscription() throws Exception {

        ActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
            post("/activitySubscriptions")
                .contentType(contentType).accept(contentType)
                .content(objectMapper.writeValueAsString(activitySubscription)))
            .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao,Mockito.times(1))
            .create(anyObject());
    }

    @Test
    public void shouldSubscribeToActivity() throws Exception {

        ActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
            post("/activitySubscriptions/subscribe")
        .param("receiverId","8")
        .param("activityEntryType",ActivityEntryType.MEMBER.toString())
        .param("classPK","89")
        .param("extraInfo","")
        .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitiesService,Mockito.times(1))
            .subscribe(anyLong(),anyObject(),anyLong(),anyString());
    }
    @Test
    public void shouldGetActivitySubscription() throws Exception {

        ActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
            get("/activitySubscriptions/2343")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao,Mockito.times(1))
            .get(anyLong());
    }
    @Test
    public void shouldDeleteActivitySubscription() throws Exception {

        ActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
            delete("/activitySubscriptions/2343")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao,Mockito.times(1))
            .delete(anyLong());
    }

    @Test
    public void shouldDeleteIfSubscribed() throws Exception {


        this.mockMvc.perform(
            delete("/activitySubscriptions/deleteIfSubscribed")
                .param("receiverId","8")
                .param("activityEntryType",ActivityEntryType.MEMBER.toString())
                .param("classPK","89")
                .param("extraInfo","")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitiesService,Mockito.times(1))
            .unsubscribe(anyLong(),anyObject(),anyLong(),anyString());
    }

    @Test
    public void shouldCheckIfSubscribed() throws Exception {


        this.mockMvc.perform(
            get("/activitySubscriptions/isSubscribed")
                .param("receiverId","8")
                .param("classNameId","2")
                .param("classPK","89")
                .param("type","89")
                .param("extraInfo","")
                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao,Mockito.times(1))
            .isSubscribed(anyLong(),anyLong(),anyLong(),anyInt(),anyString());
    }

    @Test
    public void shouldGetSubscriptions() throws Exception {


        this.mockMvc.perform(
            get("/activitySubscriptions")
                .param("receiverId","8")
                .param("classNameId","2")

                .contentType(contentType).accept(contentType))
            .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao,Mockito.times(1))
            .getActivitySubscribers(anyLong(),anyLong(),anyLong());
    }
}
