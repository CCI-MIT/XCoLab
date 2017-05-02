package org.xcolab.service.activities.activityentry.discussion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

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
    org.xcolab.client.members.MembersClient.class

})
public class DiscussionAddCommentActivityEntryTest {

    @Mock
    CommentClient commentClient;

    @Mock
    RestService restService;


    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);

        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(CategoryClientUtil.class);
        PowerMockito.mockStatic(CommentClientUtil.class);
        PowerMockito.mockStatic(ThreadClientUtil.class);
        PowerMockito.mockStatic(MessagingClient.class);
        PowerMockito.mockStatic(MembersClient.class);

        Mockito.when(MembersClient.getMember(anyLong()))
                .thenAnswer(new Answer<Member>() {
                    @Override
                    public Member answer(InvocationOnMock invocation)
                            throws Throwable {
                        Member member = new Member();
                        member.setScreenName("");
                        return member;

                    }
                });


        Mockito.when(CommentClientUtil
                .getComment(Mockito.anyLong()))
                .thenAnswer(new Answer<Comment>() {
                    @Override
                    public Comment answer(InvocationOnMock invocation)
                            throws Throwable {
                        Comment c = new Comment();
                        c.setThreadId(1234l);
                        return c;

                    }
         });

        Mockito.when(ThreadClientUtil.getThread(Mockito.anyLong()))
                .thenAnswer(new Answer<CommentThread>() {
                    @Override
                    public CommentThread answer(InvocationOnMock invocation)
                            throws Throwable {
                        CommentThread c = new CommentThread();
                        c.setCategoryId(1234l);
                        return c;

                    }
                });
        Mockito.when(ContestClientUtil.getAllContestTypes()).thenReturn(
            new ArrayList()
        );
    }
    @Test
    public void discussionCommentActivityEntry() throws Exception {

        CommentClient commentClient = Mockito.mock(CommentClient.class);
        PowerMockito.whenNew(CommentClient.class).withArguments(Mockito.anyObject()).thenReturn(commentClient);

        DiscussionAddCommentActivityEntry provider = new DiscussionAddCommentActivityEntry();

        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setMemberId(1234l);
        activityEntry.setClassPrimaryKey(1234l);
        activityEntry.setExtraData("1234");
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));

        provider.setActivityEntry(activityEntry);

        PowerMockito.verifyStatic(Mockito.times(2));
        CategoryClientUtil.getCategory(Mockito.anyLong());

        PowerMockito.verifyStatic(Mockito.times(1));
        CommentClientUtil.getComment(Mockito.anyLong());

        PowerMockito.verifyStatic(Mockito.times(1));
        ThreadClientUtil.getThread(Mockito.anyLong());


        activityEntry.setPrimaryType(provider.getPrimaryType());
        activityEntry.setSecondaryType(provider.getSecondaryType());

        activityEntry.setActivityEntryBody(provider.getBody());
        activityEntry.setActivityEntryTitle(provider.getTitle());
        activityEntry.setActivityEntryName(provider.getName());
    }

}
