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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@PrepareForTest({
    org.xcolab.client.proposals.ProposalClientUtil.class,
    org.xcolab.client.contest.ContestClientUtil.class,
    org.xcolab.client.contest.ContestClient.class,
    org.xcolab.client.proposals.pojo.Proposal.class,
    org.xcolab.client.contest.pojo.Contest.class
})
@ComponentScan("org.xcolab.service.activities")
@ComponentScan("org.xcolab.client")
@ActiveProfiles("test")
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
            activitiesService.subscribe(1111, ActivityCategory.DISCUSSION, 222);

        assertTrue(ActivitySubscriptionDao
            .isSubscribed(ActivityCategory.DISCUSSION, 1111, 222L));

    }
    @Test
    public void shouldSubscribeOnlyOnceForProposal() throws Exception {
        ActivitySubscription asp1 =
            activitiesService.subscribe(11112, ActivityCategory.PROPOSAL, 2221);
        ActivitySubscription asp2 =
            activitiesService.subscribe(11112, ActivityCategory.PROPOSAL, 2221);
        assertEquals(asp1.getId(), asp2.getId());
    }

    @Test
    public void shouldSubscribeOnlyOnceForContest() throws Exception {
        ActivitySubscription asp3 =
            activitiesService.subscribe(111132, ActivityCategory.CONTEST,22241);
        ActivitySubscription asp4 =
            activitiesService.subscribe(111132, ActivityCategory.CONTEST,22241);
        assertEquals(asp3.getId(),asp4.getId());

    }
    @Test
    public void shouldUnsubscribeDiscussion() throws Exception {

        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityCategory.DISCUSSION, 222);
            activitiesService.unsubscribe(1111, ActivityCategory.DISCUSSION, 222);

        assertFalse(ActivitySubscriptionDao
            .isSubscribed(ActivityCategory.DISCUSSION, 1111, 222L));

    }

    @Test
    public void shouldUnsubscribeProposal() throws Exception {

        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityCategory.PROPOSAL, 222);

        activitiesService.unsubscribe(1111, ActivityCategory.PROPOSAL, 222);

        assertFalse(ActivitySubscriptionDao
            .isSubscribed(ActivityCategory.PROPOSAL, 1111, 222L));

    }
    @Test
    public void shouldUnsubscribeContest() throws Exception {

        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityCategory.CONTEST, 222);

        activitiesService.unsubscribe(1111, ActivityCategory.CONTEST, 222);

        assertFalse(ActivitySubscriptionDao
            .isSubscribed(ActivityCategory.CONTEST, 1111, 222L));

    }
}
