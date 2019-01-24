package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.IAdminClient;
import org.xcolab.client.admin.IContestTypeClient;
import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.IFileClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ImpactClient;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.ProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.proposals.PointsClient;
import org.xcolab.client.contest.proposals.ProposalAttributeClient;
import org.xcolab.client.contest.proposals.ProposalMoveClient;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.search.ISearchClient;
import org.xcolab.view.activityentry.discussion.DiscussionBaseActivityEntry;
import org.xcolab.view.pages.contestmanagement.wrappers.FlaggingReportWrapper;
import org.xcolab.view.pages.loginregister.ImageUploadUtils;
import org.xcolab.view.pages.modeling.admin.form.UpdateModelInputWidgetsBean;
import org.xcolab.view.pages.profile.utils.ActivitySubscriptionNameGenerator;
import org.xcolab.view.pages.search.items.DiscussionSearchItem;
import org.xcolab.view.pages.search.paging.SearchDataPage;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.LoadThreadStartTag;
import org.xcolab.view.tags.LoadContentArticleTag;
import org.xcolab.view.util.entity.activityEntry.ActivitySubscriptionEmailHelper;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(IFileClient fileClient, IContentClient contentClient,
            IThreadClient threadClient, ICommentClient commentClient,
            ICategoryClient categoryClient, ISearchClient searchClient,
            IModelingClient modelingClient, IAdminClient adminClient,
            IContestTypeClient contestTypeClient, IEmailTemplateClient emailTemplateClient,
            IEmailClient emailClient, ContestClient contestClient,
            ContestTeamMemberClient contestTeamMemberClient, ImpactClient impactClient,
            OntologyClient ontologyClient, ProposalTemplateClient proposalTemplateClient,
            PointsClient pointsClient, ProposalAttributeClient proposalAttributeClient,
            ProposalMoveClient proposalMoveClient, ProposalPhaseClient proposalPhaseClient) {
        // Module Internal
        ImageUploadUtils.setFileClient(fileClient);
        LoadContentArticleTag.setContentClient(contentClient);
        UpdateModelInputWidgetsBean.setModelingClient(modelingClient);
        ActivitySubscriptionNameGenerator.setThreadClient(threadClient);
        DiscussionBaseActivityEntry.setThreadClient(threadClient);
        LoadThreadStartTag.setThreadClient(threadClient);
        FlaggingReportWrapper.setClients(commentClient, threadClient);
        ActivitySubscriptionEmailHelper.setCommentClient(commentClient);
        DiscussionSearchItem.setClients(commentClient, threadClient);
        LoadThreadStartTag.setCategoryClient(categoryClient);
        SearchDataPage.setSearchClient(searchClient);

        // Module External
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);
        StaticContestContext.setClients(commentClient, categoryClient, threadClient, contestClient,
                contestTeamMemberClient, impactClient, ontologyClient, proposalTemplateClient);
        StaticAdminContext.setClients(adminClient, contestTypeClient, emailTemplateClient);
        StaticEmailContext.setEmailClient(emailClient);
        StaticProposalContext.setClients(pointsClient, proposalAttributeClient, proposalMoveClient,
                proposalPhaseClient);
    }
}
