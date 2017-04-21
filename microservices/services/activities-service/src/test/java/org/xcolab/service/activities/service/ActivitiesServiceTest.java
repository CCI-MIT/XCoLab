package org.xcolab.service.activities.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.UsersGroupsClientUtil;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivitySubscription;
import org.xcolab.service.activities.domain.activitySubscription.ActivitySubscriptionDao;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@org.powermock.modules.junit4.PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(
    properties = {
        "cache.active=false",
        "eureka.client.enabled=false",
        "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MYSQL"
    }
)

@PrepareForTest({
    org.xcolab.util.http.ServiceRequestUtils.class,
    org.xcolab.client.proposals.ProposalClientUtil.class,
    org.xcolab.client.proposals.pojo.Proposal.class

})
/*
*
*
* org.xcolab.client.contest.ContestClientUtil.class,

    org.xcolab.client.proposals.ProposalAttributeClientUtil.class,
    org.xcolab.client.proposals.ProposalPhaseClientUtil.class,
    org.xcolab.client.contest.ContestTeamMemberClientUtil.class,
    org.xcolab.client.comment.util.CommentClientUtil.class,
    org.xcolab.client.proposals.ProposalMemberRatingClientUtil.class,
    org.xcolab.client.proposals.MembershipClientUtil.class,
    org.xcolab.client.contest.PlanTemplateClientUtil.class,
    org.xcolab.client.proposals.ProposalJudgeRatingClientUtil.class,
    org.xcolab.client.members.UsersGroupsClientUtil.class
* */

@ComponentScan("org.xcolab.service.activities")
@ComponentScan("org.xcolab.client")

public class ActivitiesServiceTest {

    @Autowired
    ActivitiesService activitiesService;

    @Autowired
    ActivitySubscriptionDao ActivitySubscriptionDao;

    @Before
    public void setup() throws Exception {

        PowerMockito.mockStatic(ServiceRequestUtils.class);

        PowerMockito.mockStatic(ProposalClientUtil.class);

        /*PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(ProposalAttributeClientUtil.class);
        PowerMockito.mockStatic(ProposalPhaseClientUtil.class);
        PowerMockito.mockStatic(ContestTeamMemberClientUtil.class);
        PowerMockito.mockStatic(CommentClientUtil.class);
        PowerMockito.mockStatic(ProposalMemberRatingClientUtil.class);
        PowerMockito.mockStatic(MembershipClientUtil.class);
        PowerMockito.mockStatic(PlanTemplateClientUtil.class);
        PowerMockito.mockStatic(ProposalJudgeRatingClientUtil.class);
        PowerMockito.mockStatic(UsersGroupsClientUtil.class);
*/
        Mockito.when(ProposalClientUtil.getProposal(anyLong()))
            .thenAnswer(new Answer<Proposal>() {
                @Override
                public Proposal answer(InvocationOnMock invocation)
                    throws Throwable {
                    Proposal proposal = new Proposal();
                    proposal.setDiscussionId(123456l);
                    return proposal;

                }
            });
    }
    @Test
    public void shouldSubscribeOnlyOnceForSameMemberDiscussionClassPK() throws Exception {

        Mockito.mock(Proposal.class);
        ActivitySubscription as1 =
            activitiesService.subscribe(1111, ActivityEntryType.DISCUSSION,222,null);

        assertTrue(ActivitySubscriptionDao.isSubscribed(1111,ActivityEntryType.DISCUSSION.getPrimaryTypeId(),222l,0,null));


        ActivitySubscription asp1 =
            activitiesService.subscribe(11112, ActivityEntryType.PROPOSAL,2221,null);
        ActivitySubscription asp2 =
            activitiesService.subscribe(11112, ActivityEntryType.PROPOSAL,2221,null);
        assertEquals(asp1.getPk(),asp2.getPk());
    }
}
