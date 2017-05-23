package org.xcolab.service.activities.activityentry.discussion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.activityentry.ActivityEntryTestHelper;

import java.sql.Timestamp;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PowerMockIgnore("javax.management.*")

@ComponentScan("org.xcolab.view.theme")
@ComponentScan("org.xcolab.view.auth")
@ComponentScan("org.xcolab.view.pages.proposals.interceptors")
@ComponentScan("org.xcolab.view.pages.proposals.utils.context")
@ComponentScan("org.xcolab.view.config")
@TestPropertySource(
        properties = {
                "cache.active=false"
        }
)

@PrepareForTest({
        org.xcolab.client.admin.EmailTemplateClientUtil.class,
        org.xcolab.client.members.MembersClient.class,
        org.xcolab.client.admin.AdminClient.class,
        org.xcolab.client.contest.ContestClientUtil.class,
        org.xcolab.client.comment.CommentClient.class,
        org.xcolab.client.members.MessagingClient.class,
        org.xcolab.client.comment.util.CategoryClientUtil.class,
        org.xcolab.client.comment.util.CommentClientUtil.class,
        org.xcolab.client.comment.util.ThreadClientUtil.class,
        org.xcolab.util.http.client.RestService.class,
        org.xcolab.client.members.MembersClient.class,
        org.xcolab.client.proposals.ProposalClientUtil.class,
        org.xcolab.client.proposals.ProposalAttributeClientUtil.class
})
public class DiscussionAddProposalCommentActivityEntryTest {

    @Before
    public void setup() throws Exception{
        ActivityEntryTestHelper.setupBasic();
    }

    @Test
    public void discussionCommentActivityEntry() throws Exception {

        CommentClient commentClient = Mockito.mock(CommentClient.class);
        PowerMockito.whenNew(CommentClient.class).withArguments(Mockito.anyObject()).thenReturn(commentClient);

        DiscussionAddProposalCommentActivityEntry provider = new DiscussionAddProposalCommentActivityEntry();

        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setMemberId(1234l);
        activityEntry.setClassPrimaryKey(1234l);
        activityEntry.setExtraData("1234");
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));

        provider.setActivityEntry(activityEntry);




        PowerMockito.verifyStatic(Mockito.times(1));
        ThreadClientUtil.getThread(Mockito.anyLong());


        activityEntry.setPrimaryType(provider.getPrimaryType());
        activityEntry.setSecondaryType(provider.getSecondaryType());

        activityEntry.setActivityEntryBody(provider.getBody());
        activityEntry.setActivityEntryTitle(provider.getTitle());
        activityEntry.setActivityEntryName(provider.getName());
    }
}
