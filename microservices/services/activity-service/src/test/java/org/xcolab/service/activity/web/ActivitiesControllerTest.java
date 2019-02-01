package org.xcolab.service.activity.web;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.xcolab.client.activity.pojo.IActivityEntry;
import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.activity.pojo.tables.pojos.ActivityEntry;
import org.xcolab.client.activity.pojo.tables.pojos.ActivitySubscription;
import org.xcolab.service.activity.domain.activityEntry.ActivityEntryDao;
import org.xcolab.service.activity.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.service.activity.service.ActivitiesService;
import org.xcolab.service.activity.utils.Utils;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.activities.enums.ActivityCategory;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@WebMvcTest(ActivityController.class)
@ComponentScan("org.xcolab.service.activity")
@ComponentScan("org.xcolab.client.contest")
@ComponentScan("com.netflix.discovery")
@ActiveProfiles("test")
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
    private ActivityController controller;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Mockito.when(activityEntryDao.create(any(IActivityEntry.class)))
                .thenAnswer(invocation -> new ActivityEntry());

        Mockito.when(activitySubscriptionDao.get(anyLong()))
                .thenAnswer(invocation -> Optional.of(new ActivitySubscription()));
    }

    @Test
    public void shouldGetActivityEntry() throws Exception {
        this.mockMvc.perform(
                get("/activityEntries/1234")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());
        Mockito.verify(activityEntryDao, Mockito.times(1)).get(1234L);
    }

    @Test
    public void shouldGetActivityEntriesWithActivitiesAfter() throws Exception {
        String activitiesAfter = "2016-01-01 00:00:00";
        this.mockMvc.perform(get("/activityEntries").param("activitiesAfter", activitiesAfter)
                .contentType(contentType).accept(contentType)).andExpect(status().isOk());
        Mockito.verify(activityEntryDao, Mockito.times(1))
                .getActivitiesAfter(Utils.parseDate(activitiesAfter));
    }

    @Test
    public void shouldGetActivityEntries() throws Exception {
        PaginationHelper ph = new PaginationHelper(0, 10, "");
        this.mockMvc.perform(
                get("/activityEntries")
                        .param("startRecord", "0")
                        .param("limitRecord", "10")
                        .param("sort", "")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());
        Mockito.verify(activityEntryDao, Mockito.times(1))
                .findByGiven(any(PaginationHelper.class), isNull(), isNull(), isNull(), isNull());
    }

    @Test
    public void shouldCountActivityEntries() throws Exception {
        this.mockMvc.perform(
                get("/count/activityEntries")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());
        Mockito.verify(activityEntryDao, Mockito.times(1))
                .countByGiven(isNull(), isNull());
    }

    @Test
    public void shouldCreateActivitySubscription() throws Exception {
        IActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
                post("/activitySubscriptions")
                        .contentType(contentType).accept(contentType)
                        .content(objectMapper.writeValueAsString(activitySubscription)))
                .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao, Mockito.times(1))
                .create(any(IActivitySubscription.class));
    }

    @Test
    public void shouldSubscribeToActivity() throws Exception {
        this.mockMvc.perform(
                post("/activitySubscriptions/subscribe")
                        .param("receiverId", "8")
                        .param("activityCategory", ActivityCategory.CONTEST.name())
                        .param("categoryId", "89")
                        .param("extraInfo", "")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitiesService, Mockito.times(1))
                .subscribe(eq(8L), eq(ActivityCategory.CONTEST), eq(89L));
    }

    @Test
    public void shouldGetActivitySubscription() throws Exception {
        IActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
                get("/activitySubscriptions/2343")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao, Mockito.times(1))
                .get(2343L);
    }

    @Test
    public void shouldDeleteActivitySubscription() throws Exception {
        IActivitySubscription activitySubscription = new ActivitySubscription();
        this.mockMvc.perform(
                delete("/activitySubscriptions/2343")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao, Mockito.times(1))
                .delete(2343L);
    }

    @Test
    public void shouldDeleteIfSubscribed() throws Exception {
        this.mockMvc.perform(
                delete("/activitySubscriptions/deleteIfSubscribed")
                        .param("receiverId", "8")
                        .param("activityCategory", ActivityCategory.CONTEST.name())
                        .param("categoryId", "89")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitiesService, Mockito.times(1))
                .unsubscribe(eq(8L), eq(ActivityCategory.CONTEST), eq(89L));
    }

    @Test
    public void shouldCheckIfSubscribed() throws Exception {
        this.mockMvc.perform(
                get("/activitySubscriptions/isSubscribed")
                        .param("receiverId", "8")
                        .param("activityCategory", ActivityCategory.CONTEST.name())
                        .param("categoryId", "89")
                        .param("extraInfo", "")
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao, Mockito.times(1))
                .isSubscribed(eq(ActivityCategory.CONTEST), eq(8L), eq(89L));
    }

    @Test
    public void shouldGetSubscriptions() throws Exception {
        this.mockMvc.perform(
                get("/activitySubscriptions")
                        .param("receiverId", "8")
                        .param("activityCategory", ActivityCategory.CONTEST.name())
                        .contentType(contentType).accept(contentType))
                .andExpect(status().isOk());

        Mockito.verify(activitySubscriptionDao, Mockito.times(1))
                .getActivitySubscribers(eq(ActivityCategory.CONTEST), isNull(), eq(8L));
    }
}
