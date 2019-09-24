package org.xcolab.service.activity.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.activity.pojo.IActivitySubscription;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.service.activity.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.activities.enums.ActivityCategory;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyLong;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@OverrideAutoConfiguration(enabled = false)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@PrepareForTest({
        IContestClient.class,
        IProposalClient.class,
        ProposalWrapper.class,
        ContestWrapper.class
})
@ComponentScan("org.xcolab.service.activity")
@ComponentScan("org.xcolab.client")
@ActiveProfiles("test")
@Ignore
public class ActivitiesServiceTest {

    @Autowired
    ActivitiesService activitiesService;

    @Autowired
    ActivitySubscriptionDao ActivitySubscriptionDao;

    @Mock
    IContestClient contestClient;

    @Mock
    IProposalClient proposalClient;

    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);



        Mockito.when(proposalClient.getProposal(anyLong())).thenCallRealMethod();
        Mockito.when(proposalClient.getProposal(anyLong(),anyBoolean()))
                .thenAnswer(invocation -> {
                    ProposalWrapper proposal = Mockito.mock(ProposalWrapper.class);
                    proposal.setDiscussionId(123456L);
                    return proposal;
                });

        Mockito.when(contestClient.getContest(anyLong())).thenCallRealMethod();
        Mockito.when(contestClient.getContest(anyLong(),anyString()))//,anyString()
                .thenAnswer(invocation -> {
                    ContestWrapper contest = Mockito.mock(ContestWrapper.class);
                    contest.setDiscussionGroupId(123123L);
                    return contest;
                });

        StaticContestContext.setClients(contestClient, Mockito.mock(IContestTeamMemberClient.class),
                Mockito.mock(IImpactClient.class), Mockito.mock(IOntologyClient.class),
                Mockito.mock(IProposalTemplateClient.class));
    }

    @Test
    public void shouldSubscribeOnlyOnceForDiscussion() throws Exception {
        IActivitySubscription as1 =
                activitiesService.subscribe(1111, ActivityCategory.DISCUSSION, 222);

        assertTrue(ActivitySubscriptionDao
                .isSubscribed(ActivityCategory.DISCUSSION, 1111, 222L));
    }

    @Test
    @Ignore
    public void shouldSubscribeOnlyOnceForProposal() throws Exception {
        IActivitySubscription asp1 =
                activitiesService.subscribe(11112, ActivityCategory.PROPOSAL, 2221);
        IActivitySubscription asp2 =
                activitiesService.subscribe(11112, ActivityCategory.PROPOSAL, 2221);
        assertEquals(asp1.getId(), asp2.getId());
    }

    @Test
    @Ignore
    public void shouldSubscribeOnlyOnceForContest() throws Exception {
        IActivitySubscription asp3 =
                activitiesService.subscribe(111132, ActivityCategory.CONTEST, 22241);
        IActivitySubscription asp4 =
                activitiesService.subscribe(111132, ActivityCategory.CONTEST, 22241);
        assertEquals(asp3.getId(), asp4.getId());
    }

    @Test
    public void shouldUnsubscribeDiscussion() throws Exception {
        IActivitySubscription as1 =
                activitiesService.subscribe(1111, ActivityCategory.DISCUSSION, 222);
        activitiesService.unsubscribe(1111, ActivityCategory.DISCUSSION, 222);

        assertFalse(ActivitySubscriptionDao
                .isSubscribed(ActivityCategory.DISCUSSION, 1111, 222L));
    }

    @Test
    @Ignore
    public void shouldUnsubscribeProposal() throws Exception {
        IActivitySubscription as1 =
                activitiesService.subscribe(1111, ActivityCategory.PROPOSAL, 222);

        activitiesService.unsubscribe(1111, ActivityCategory.PROPOSAL, 222);

        assertFalse(ActivitySubscriptionDao
                .isSubscribed(ActivityCategory.PROPOSAL, 1111, 222L));
    }

    @Test
    @Ignore
    public void shouldUnsubscribeContest() throws Exception {
        IActivitySubscription as1 =
                activitiesService.subscribe(1111, ActivityCategory.CONTEST, 222);

        activitiesService.unsubscribe(1111, ActivityCategory.CONTEST, 222);

        assertFalse(ActivitySubscriptionDao
                .isSubscribed(ActivityCategory.CONTEST, 1111, 222L));
    }

}
