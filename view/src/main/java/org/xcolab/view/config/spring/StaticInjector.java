package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.view.activityentry.discussion.DiscussionBaseActivityEntry;
import org.xcolab.view.pages.contestmanagement.wrappers.FlaggingReportWrapper;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.profile.utils.ActivitySubscriptionNameGenerator;
import org.xcolab.view.pages.search.items.DiscussionSearchItem;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.LoadThreadStartTag;
import org.xcolab.view.tags.LoadContentArticleTag;
import org.xcolab.view.util.entity.activityEntry.ActivitySubscriptionEmailHelper;

@Component
public class StaticInjector implements ApplicationRunner {

    @Autowired
    private IFileClient fileClient;

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IThreadClient threadClient;

    @Autowired
    private ICommentClient commentClient;

    @Autowired
    private ICategoryClient categoryClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        ImageUploadUtils.setFileClient(fileClient);
        LoadContentArticleTag.setContentClient(contentClient);
        ActivitySubscriptionNameGenerator.setThreadClient(threadClient);
        ActivitySubscriptionNameGenerator.setCategoryClient(categoryClient);
        DiscussionBaseActivityEntry.setThreadClient(threadClient);
        LoadThreadStartTag.setThreadClient(threadClient);
        FlaggingReportWrapper.setThreadClient(threadClient);
        FlaggingReportWrapper.setCommentClient(commentClient);
        ActivitySubscriptionEmailHelper.setCommentClient(commentClient);
        DiscussionSearchItem.setThreadClient(threadClient);
        DiscussionSearchItem.setCommentClient(commentClient);
        LoadThreadStartTag.setCategoryClient(categoryClient);
    }
}
