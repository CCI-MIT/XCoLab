package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.activity.IActivityClient;
import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.contest.IContestClient;
import org.xcolab.client.contest.IContestTeamMemberClient;
import org.xcolab.client.contest.IImpactClient;
import org.xcolab.client.contest.IOntologyClient;
import org.xcolab.client.contest.IProposalTemplateClient;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.proposals.IMembershipClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalMemberRatingClient;
import org.xcolab.client.contest.proposals.IPointsClient;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalJudgeRatingClient;
import org.xcolab.client.contest.proposals.IProposalMoveClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.StaticProposalContext;

public class ClientHelper {

    private final IMembershipClient membershipClient;
    private final IProposalClient proposalClient;
    private final IPointsClient pointsClient;
    private final IProposalPhaseClient proposalPhaseClient;
    private final IProposalAttributeClient proposalAttributeClient;
    private final IProposalMoveClient proposalMoveClient;
    private final IProposalJudgeRatingClient proposalJudgeRatingClient;
    private final IProposalMemberRatingClient proposalMemberRatingClient;

    // Contest
    private final IContestClient contestClient;
    private final IContestTeamMemberClient contestTeamMemberClient;
    private final IImpactClient impactClient;
    private final IOntologyClient ontologyClient;
    private final IProposalTemplateClient proposalTemplateClient;

    private final IActivityClient activityClient;

    public ClientHelper() {
        membershipClient = StaticProposalContext.getMembershipClient();
        proposalClient = StaticProposalContext.getProposalClient();
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

        activityClient = StaticActivityContext.getActivityClient();
    }

    public IActivityClient getActivitiesClient() {
        return activityClient;
    }

    public IProposalClient getProposalClient() {
        return proposalClient;
    }

    public IMembershipClient getMembershipClient() {
        return membershipClient;
    }

    public IPointsClient getPointsClient() {
        return pointsClient;
    }

    public IProposalPhaseClient getProposalPhaseClient() {
        return proposalPhaseClient;
    }

    public IProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }

    public IProposalMoveClient getProposalMoveClient() {
        return proposalMoveClient;
    }

    public IProposalJudgeRatingClient getProposalJudgeRatingClient() {
        return proposalJudgeRatingClient;
    }

    public IProposalMemberRatingClient getProposalMemberRatingClient() {
        return proposalMemberRatingClient;
    }

    public IContestClient getContestClient() {
        return contestClient;
    }

    public IContestTeamMemberClient getContestTeamMemberClient() {
        return contestTeamMemberClient;
    }

    public IImpactClient getImpactClient() {
        return impactClient;
    }

    public IOntologyClient getOntologyClient() {
        return ontologyClient;
    }

    public IProposalTemplateClient getProposalTemplateClient() {
        return proposalTemplateClient;
    }
}
