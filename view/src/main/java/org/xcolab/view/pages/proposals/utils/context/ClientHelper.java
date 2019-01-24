package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ImpactClient;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.ProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.proposals.MembershipClient;
import org.xcolab.client.contest.proposals.MembershipClientUtil;
import org.xcolab.client.contest.proposals.PointsClient;
import org.xcolab.client.contest.proposals.ProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.ProposalMoveClient;
import org.xcolab.client.contest.proposals.ProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;

public class ClientHelper {

    private final IProposalClient proposalClient;
    private final MembershipClient membershipClient;
    private final PointsClient pointsClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalMoveClient proposalMoveClient;
    private final IProposalJudgeRatingClient proposalJudgeRatingClient;
    private final IProposalMemberRatingClient proposalMemberRatingClient;

    // Contest
    private final ContestClient contestClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final ImpactClient impactClient;
    private final OntologyClient ontologyClient;
    private final ProposalTemplateClient proposalTemplateClient;

    // Activity
    private final ActivitiesClient activitiesClient;

    public ClientHelper() {
        proposalClient = StaticProposalContext.getProposalClient();
        membershipClient = MembershipClientUtil.getClient();
        pointsClient = StaticProposalContext.getPointsClient();
        proposalPhaseClient = StaticProposalContext.getProposalPhaseClient();
        proposalAttributeClient = StaticProposalContext.getProposalAttributeClient();
        proposalMoveClient = StaticProposalContext.getProposalMoveClient();
        proposalJudgeRatingClient = StaticProposalContext.getProposalJudgeRatingClient();
        proposalMemberRatingClient = StaticProposalContext.getProposalMemberRatingClient();

        contestClient = StaticContestContext.getContestClient();
        contestTeamMemberClient = StaticContestContext.getContestTeamMemberClient();
        impactClient = StaticContestContext.getImpactClient();
        ontologyClient = StaticContestContext.getOntologyClient();
        proposalTemplateClient = StaticContestContext.getProposalTemplateClient();

        activitiesClient = ActivitiesClientUtil.getClient();
    }

    public ActivitiesClient getActivitiesClient() {
        return activitiesClient;
    }

    public IProposalClient getProposalClient() {
        return proposalClient;
    }

    public MembershipClient getMembershipClient() {
        return membershipClient;
    }

    public PointsClient getPointsClient() {
        return pointsClient;
    }

    public ProposalPhaseClient getProposalPhaseClient() {
        return proposalPhaseClient;
    }

    public ProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }

    public ProposalMoveClient getProposalMoveClient() {
        return proposalMoveClient;
    }

    public IProposalJudgeRatingClient getProposalJudgeRatingClient() {
        return proposalJudgeRatingClient;
    }

    public IProposalMemberRatingClient getProposalMemberRatingClient() {
        return proposalMemberRatingClient;
    }

    public ContestClient getContestClient() {
        return contestClient;
    }

    public ContestTeamMemberClient getContestTeamMemberClient() {
        return contestTeamMemberClient;
    }

    public ImpactClient getImpactClient() {
        return impactClient;
    }

    public OntologyClient getOntologyClient() {
        return ontologyClient;
    }

    public ProposalTemplateClient getProposalTemplateClient() {
        return proposalTemplateClient;
    }

}
