package org.xcolab.view.pages.proposals.utils.context;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.ContestTeamMemberClient;
import org.xcolab.client.contest.ContestTeamMemberClientUtil;
import org.xcolab.client.contest.ImpactClient;
import org.xcolab.client.contest.ImpactClientUtil;
import org.xcolab.client.contest.OntologyClient;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.ProposalTemplateClient;
import org.xcolab.client.contest.ProposalTemplateClientUtil;
import org.xcolab.client.proposals.MembershipClient;
import org.xcolab.client.proposals.MembershipClientUtil;
import org.xcolab.client.proposals.PointsClient;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalJudgeRatingClient;
import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.ProposalMemberRatingClientUtil;
import org.xcolab.client.proposals.ProposalMoveClient;
import org.xcolab.client.proposals.ProposalMoveClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;

public class ClientHelper {

    private final ProposalClient proposalClient;
    private final MembershipClient membershipClient;
    private final PointsClient pointsClient;
    private final ProposalPhaseClient proposalPhaseClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalMoveClient proposalMoveClient;
    private final ProposalJudgeRatingClient proposalJudgeRatingClient;
    private final ProposalMemberRatingClient proposalMemberRatingClient;

    // Contest
    private final ContestClient contestClient;
    private final ContestTeamMemberClient contestTeamMemberClient;
    private final ImpactClient impactClient;
    private final OntologyClient ontologyClient;
    private final ProposalTemplateClient proposalTemplateClient;

    // Activity
    private final ActivitiesClient activitiesClient;

    public ClientHelper() {
        proposalClient = ProposalClientUtil.getClient();
        membershipClient = MembershipClientUtil.getClient();
        pointsClient = PointsClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalMoveClient = ProposalMoveClientUtil.getClient();
        proposalJudgeRatingClient = ProposalJudgeRatingClientUtil.getClient();
        proposalMemberRatingClient = ProposalMemberRatingClientUtil.getClient();

        contestClient = ContestClientUtil.getClient();
        contestTeamMemberClient = ContestTeamMemberClientUtil.getClient();
        impactClient = ImpactClientUtil.getClient();
        ontologyClient = OntologyClientUtil.getClient();
        proposalTemplateClient = ProposalTemplateClientUtil.getClient();

        activitiesClient = ActivitiesClientUtil.getClient();
    }

    public ActivitiesClient getActivitiesClient() {
        return activitiesClient;
    }

    public ProposalClient getProposalClient() {
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

    public ProposalJudgeRatingClient getProposalJudgeRatingClient() {
        return proposalJudgeRatingClient;
    }

    public ProposalMemberRatingClient getProposalMemberRatingClient() {
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
