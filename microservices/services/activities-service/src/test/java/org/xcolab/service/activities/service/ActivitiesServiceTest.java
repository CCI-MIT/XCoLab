package org.xcolab.service.activities.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
    properties = {
        "cache.enabled=false",
        "eureka.client.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
    }
)

@PrepareForTest({
    org.xcolab.client.proposals.ProposalClientUtil.class,
    org.xcolab.client.contest.ContestClientUtil.class,
    org.xcolab.client.contest.ContestClient.class,
    org.xcolab.client.proposals.pojo.Proposal.class,
    org.xcolab.client.contest.pojo.Contest.class
})


@ComponentScan("org.xcolab.service.activities")
@ComponentScan("org.xcolab.client")

public class ActivitiesServiceTest {

    @Autowired
    ActivitiesService activitiesService;

    @Autowired
    ActivitySubscriptionDao ActivitySubscriptionDao;

    @Before
    public void setup() throws Exception {

        ServiceRequestUtils.setInitialized(true);

        PowerMockito.mockStatic(ProposalClientUtil.class);

        PowerMockito.mockStatic(ContestClient.class);

        PowerMockito.mockStatic(ContestClientUtil.class);



        Mockito.mock(Proposal.class);

        Mockito.when(ProposalClientUtil.getProposal(anyLong()))
            .thenAnswer(invocation -> {
                Proposal proposal = Mockito.mock(Proposal.class);
                proposal.setDiscussionId(123456L);
                return proposal;

            });

        Mockito.when(ContestClientUtil.getContest(anyLong()))
            .thenAnswer(invocation -> {
                Contest contest = Mockito.mock(Contest.class);
                contest.setDiscussionGroupId(123123L);
                return contest;

            });
    }

    @Test
    public void shouldSubscribeOnlyOnceForDiscussion() throws Exception {


        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityEntryType.DISCUSSION, 222, null);

        assertTrue(ActivitySubscriptionDao
            .isSubscribed(1111, ActivityEntryType.DISCUSSION.getPrimaryTypeId(), 222L, 0, null));

    }
    @Test
    public void shouldSubscribeOnlyOnceForProposal() throws Exception {
        ActivitySubscription asp1 =
            activitiesService.subscribe(11112, ActivityEntryType.PROPOSAL, 2221, null);
        ActivitySubscription asp2 =
            activitiesService.subscribe(11112, ActivityEntryType.PROPOSAL, 2221, null);
        assertEquals(asp1.getPk(), asp2.getPk());
    }

    @Test
    public void shouldSubscribeOnlyOnceForContest() throws Exception {
        ActivitySubscription asp3 =
            activitiesService.subscribe(111132, ActivityEntryType.CONTEST,22241,null);
        ActivitySubscription asp4 =
            activitiesService.subscribe(111132, ActivityEntryType.CONTEST,22241,null);
        assertEquals(asp3.getPk(),asp4.getPk());

    }
    @Test
    public void shouldUnsubscribeDiscussion() throws Exception {


        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityEntryType.DISCUSSION, 222, null);
            activitiesService.unsubscribe(1111, ActivityEntryType.DISCUSSION, 222, null);

        assertFalse(ActivitySubscriptionDao
            .isSubscribed(1111, ActivityEntryType.DISCUSSION.getPrimaryTypeId(), 222L, 0, null));

    }

    @Test
    public void shouldUnsubscribeProposal() throws Exception {


        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityEntryType.PROPOSAL, 222, null);

        activitiesService.unsubscribe(1111, ActivityEntryType.PROPOSAL, 222, null);

        assertFalse(ActivitySubscriptionDao
            .isSubscribed(1111, ActivityEntryType.PROPOSAL.getPrimaryTypeId(), 222L, 0, null));

    }
    @Test
    public void shouldUnsubscribeContest() throws Exception {


        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityEntryType.CONTEST, 222, null);

        activitiesService.unsubscribe(1111, ActivityEntryType.CONTEST, 222, null);


        assertFalse(ActivitySubscriptionDao
            .isSubscribed(1111, ActivityEntryType.CONTEST.getPrimaryTypeId(), 222L, 0, null));

    }
}
