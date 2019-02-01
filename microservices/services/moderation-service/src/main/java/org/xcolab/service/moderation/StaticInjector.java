package org.xcolab.service.moderation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;
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
import org.xcolab.client.moderation.IModerationClient;
import org.xcolab.client.moderation.StaticModerationContext;

@Component
@Profile("!test")
public class StaticInjector {

    @Autowired
    public StaticInjector(IModerationClient moderationClient, ICommentClient commentClient,
            ICategoryClient categoryClient, IThreadClient threadClient,
            IContestClient contestClient, IContestTeamMemberClient contestTeamMemberClient,
            IImpactClient impactClient, IOntologyClient ontologyClient,
            IProposalTemplateClient proposalTemplateClient, IPointsClient pointsClient,
            IProposalAttributeClient proposalAttributeClient,
            IProposalMoveClient proposalMoveClient, IProposalPhaseClient proposalPhaseClient,
            IProposalClient proposalClient, IMembershipClient membershipClient,
            IProposalMemberRatingClient proposalMemberRatingClient,
            IProposalJudgeRatingClient proposalJudgeRatingClient) {

        StaticModerationContext.setClients(moderationClient);
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);
        StaticContestContext.setClients(contestClient, contestTeamMemberClient, impactClient,
                ontologyClient, proposalTemplateClient);
        StaticProposalContext.setClients(pointsClient, proposalAttributeClient, proposalMoveClient,
                proposalPhaseClient, proposalClient, membershipClient, proposalMemberRatingClient,
                proposalJudgeRatingClient);
    }
}
