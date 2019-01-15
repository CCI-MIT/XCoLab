package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.flagging.IFlaggingClient;
import org.xcolab.client.search.ISearchClient;
import org.xcolab.view.moderation.FlaggingController;
import org.xcolab.view.pages.contestmanagement.controller.manager.FlaggingTabController;
import org.xcolab.view.pages.contestmanagement.wrappers.FlaggingReportWrapper;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.proposals.view.proposal.tabs.ProposalDescriptionTabController;
import org.xcolab.view.pages.search.paging.SearchDataPage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.LoadThreadStartTag;
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
    private IFlaggingClient flaggingClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        SearchDataPage.setSearchClient(searchClient);
        ImageUploadUtils.setFileClient(fileClient);
        LoadContentArticleTag.setContentClient(contentClient);

        FlaggingTabController.setFlaggingClient(flaggingClient);
        FlaggingController.setFlaggingClient(flaggingClient);
        LoadThreadStartTag.setFlaggingClient(flaggingClient);
        FlaggingReportWrapper.setFlaggingClient(flaggingClient);
        ProposalDescriptionTabController.setFlaggingClient(flaggingClient);
        DiscussionPermissions.setFlaggingClient(flaggingClient);
    }
}
