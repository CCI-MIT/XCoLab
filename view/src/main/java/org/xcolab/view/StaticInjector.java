package org.xcolab.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
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
import org.xcolab.client.content.StaticContentContext;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.IPointsClient;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.proposals.IProposalMoveClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.StaticModelingContext;
import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.moderation.StaticModerationContext;
import org.xcolab.client.search.ISearchClient;
import org.xcolab.client.search.StaticSearchContext;
import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.ITrackingClient;
import org.xcolab.client.tracking.StaticTrackingContext;
import org.xcolab.client.user.IAnalyticsClient;
import org.xcolab.client.user.ILoginLogClient;
import org.xcolab.client.user.ILoginTokenClient;
import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.IPlatformTeamClient;
import org.xcolab.client.user.ISsoClientDetailsClient;
import org.xcolab.client.user.IUserCategoryClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.IUserLoginRegisterClient;
import org.xcolab.client.user.StaticUserContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(ITrackingClient trackingClient, IBalloonClient balloonClient,
            IFileClient fileClient, IContentClient contentClient,
            IThreadClient threadClient, ICommentClient commentClient,
            ICategoryClient categoryClient, ISearchClient searchClient,
            IModelingClient modelingClient, IAdminClient adminClient,
            IContestTypeClient contestTypeClient, IEmailTemplateClient emailTemplateClient,
            IEmailClient emailClient, IContestClient contestClient,
            IContestTeamMemberClient contestTeamMemberClient, IImpactClient impactClient,
            IOntologyClient ontologyClient, IProposalTemplateClient proposalTemplateClient,
            IPointsClient pointsClient, IProposalAttributeClient proposalAttributeClient,
            IProposalMoveClient proposalMoveClient, IProposalPhaseClient proposalPhaseClient,
            IProposalClient proposalClient, IMembershipClient membershipClient,
            IProposalMemberRatingClient proposalMemberRatingClient,
            IProposalJudgeRatingClient proposalJudgeRatingClient,
            IModerationClient moderationClient, IActivityClient activityClient,
            IAnalyticsClient analyticsClient,
            ILoginTokenClient loginTokenClient,
            ILoginLogClient loginLogClient,
            IMessagingClient messagingClient,
            IPermissionClient permissionClient,
            IPlatformTeamClient platformTeamClient,
            ISsoClientDetailsClient ssoClientDetailsClient,
            IUserCategoryClient userCategoryClient,
            IUserClient userClient,
            IUserLoginRegisterClient userLoginRegisterClient
            ) {

        StaticTrackingContext.setClients(trackingClient, balloonClient);
        StaticContentContext.setClients(contentClient, fileClient);
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);
        StaticSearchContext.setClients(searchClient);
        StaticModelingContext.setClients(modelingClient);
        StaticContestContext.setClients(contestClient, contestTeamMemberClient, impactClient,
                ontologyClient, proposalTemplateClient);
        StaticAdminContext.setClients(adminClient, contestTypeClient, emailTemplateClient);
        StaticEmailContext.setClients(emailClient);
        StaticProposalContext.setClients(pointsClient, proposalAttributeClient, proposalMoveClient,
                proposalPhaseClient, proposalClient, membershipClient, proposalMemberRatingClient,
                proposalJudgeRatingClient);
        StaticModerationContext.setClients(moderationClient);
        StaticActivityContext.setClients(activityClient);

        StaticUserContext.setAnalyticsClient(analyticsClient);
        StaticUserContext.setLoginLogClient(loginLogClient);
        StaticUserContext.setLoginTokenClient(loginTokenClient);
        StaticUserContext.setMessagingClient(messagingClient);
        StaticUserContext.setPermissionClient(permissionClient);
        StaticUserContext.setPlatformTeamClient(platformTeamClient);
        StaticUserContext.setSsoClientDetailsClient(ssoClientDetailsClient);
        StaticUserContext.setUserCategoryClient(userCategoryClient);
        StaticUserContext.setUserClient(userClient);
        StaticUserContext.setUserLoginRegister(userLoginRegisterClient);
    }
}
