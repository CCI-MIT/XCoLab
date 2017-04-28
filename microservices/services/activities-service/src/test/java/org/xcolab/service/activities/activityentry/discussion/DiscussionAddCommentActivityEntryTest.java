package org.xcolab.service.activities.activityentry.discussion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.xcolab.client.activities.helper.ActivityEntryHelper;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.util.CategoryClientUtil;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.activityentry.provider.ActivityEntryContentProvider;
import org.xcolab.service.activities.enums.ActivityProvidersImpl;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.http.ServiceRequestUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

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

})
public class DiscussionAddCommentActivityEntryTest {

    @Mock
    CommentClient commentClient;


    @Before
    public void setup() throws Exception {
        ServiceRequestUtils.setInitialized(true);

        PowerMockito.mockStatic(ContestClientUtil.class);
        PowerMockito.mockStatic(CategoryClientUtil.class);
        PowerMockito.mockStatic(CommentClientUtil.class);
        PowerMockito.mockStatic(ThreadClientUtil.class);
        PowerMockito.mockStatic(MessagingClient.class);

        Mockito.when(ContestClientUtil.getAllContestTypes()).thenReturn(
            new ArrayList()
        );
    }
    @Test
    public void discussionCommentActivityEntry() throws Exception {

        DiscussionAddCommentActivityEntry provider = new DiscussionAddCommentActivityEntry();

        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setMemberId(1234l);
        activityEntry.setClassPrimaryKey(1234l);
        activityEntry.setExtraData("");
        java.util.Date date = new java.util.Date();
        activityEntry.setCreateDate(new Timestamp(date.getTime()));

        provider.setActivityEntry(activityEntry);

        PowerMockito.verifyStatic(Mockito.times(2));
        CategoryClientUtil.getCategory(Mockito.anyLong());

        PowerMockito.verifyStatic(Mockito.times(1));
        CommentClientUtil.getComment(Mockito.anyLong());

        PowerMockito.verifyStatic(Mockito.times(1));
        CategoryClientUtil.getCategory(Mockito.anyLong());


        activityEntry.setPrimaryType(provider.getPrimaryType());
        activityEntry.setSecondaryType(provider.getSecondaryType());

        activityEntry.setActivityEntryBody(provider.getBody());
        activityEntry.setActivityEntryTitle(provider.getTitle());
        activityEntry.setActivityEntryName(provider.getName());
    }

}
