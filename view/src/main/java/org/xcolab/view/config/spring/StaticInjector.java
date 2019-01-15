package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.search.ISearchClient;
import org.xcolab.view.moderation.ModerationController;
import org.xcolab.view.pages.contestmanagement.controller.manager.FlaggingTabController;
import org.xcolab.view.pages.contestmanagement.wrappers.ModerationReportWrapper;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.proposals.view.proposal.tabs.ProposalDescriptionTabController;
import org.xcolab.view.pages.search.paging.SearchDataPage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.LoadThreadStartTag;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;
import org.xcolab.view.tags.LoadContentArticleTag;

@Component
public class StaticInjector implements ApplicationRunner {

    @Autowired
    private ISearchClient searchClient;

    @Autowired
    private IFileClient fileClient;

    @Autowired
    private IContentClient contentClient;

    @Autowired
    private IModerationClient moderationClient;

    @Autowired
    private IModelingClient modelingClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        SearchDataPage.setSearchClient(searchClient);
        ImageUploadUtils.setFileClient(fileClient);
        LoadContentArticleTag.setContentClient(contentClient);
        FlaggingTabController.setmoderationClient(moderationClient);
        ModerationController.setmoderationClient(moderationClient);
        LoadThreadStartTag.setmoderationClient(moderationClient);
        ModerationReportWrapper.setmoderationClient(moderationClient);
        ProposalDescriptionTabController.setmoderationClient(moderationClient);
        DiscussionPermissions.setmoderationClient(moderationClient);
        UpdateModelInputWidgetsBean.setModelingClient(modelingClient);
    }
}
