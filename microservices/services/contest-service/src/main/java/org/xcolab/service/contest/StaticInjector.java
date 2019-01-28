package org.xcolab.service.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.comment.ICategoryClient;
import org.xcolab.client.comment.ICommentClient;
import org.xcolab.client.comment.IThreadClient;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ImpactClient;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.ProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.proposals.PointsClient;
import org.xcolab.client.contest.proposals.ProposalAttributeClient;
import org.xcolab.client.contest.proposals.ProposalMoveClient;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;

@Component
public class StaticInjector {

    @Autowired
    public StaticInjector(ICommentClient commentClient, ICategoryClient categoryClient,
            IThreadClient threadClient, ContestClient contestClient,
            ContestTeamMemberClient contestTeamMemberClient, ImpactClient impactClient,
            OntologyClient ontologyClient, ProposalTemplateClient proposalTemplateClient,
            PointsClient pointsClient, ProposalAttributeClient proposalAttributeClient,
            ProposalMoveClient proposalMoveClient, ProposalPhaseClient proposalPhaseClient,
            IProposalClient proposalClient, IMembershipClient membershipClient,
            IProposalMemberRatingClient proposalMemberRatingClient,
            IProposalJudgeRatingClient proposalJudgeRatingClient) {
        StaticCommentContext.setClients(commentClient, categoryClient, threadClient);

        StaticContestContext.setClients(commentClient, categoryClient, threadClient, contestClient,
                contestTeamMemberClient, impactClient, ontologyClient, proposalTemplateClient);
        StaticProposalContext.setClients(pointsClient, proposalAttributeClient, proposalMoveClient,
                proposalPhaseClient, proposalClient, membershipClient, proposalMemberRatingClient,
                proposalJudgeRatingClient);
    }
}
