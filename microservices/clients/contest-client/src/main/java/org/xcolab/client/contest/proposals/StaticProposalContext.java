package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;
    private static ProposalAttributeClient proposalAttributeClient;
    private static ProposalMoveClient proposalMoveClient;
    private static ProposalPhaseClient proposalPhaseClient;
    private static IProposalClient proposalClient;
    private static IMembershipClient membershipClient;
    private static IProposalMemberRatingClient proposalMemberRatingClient;
    private static IProposalJudgeRatingClient proposalJudgeRatingClient;

    private StaticProposalContext() {}

    public static void setClients(PointsClient pointsClient,
            ProposalAttributeClient proposalAttributeClient, ProposalMoveClient proposalMoveClient,
            ProposalPhaseClient proposalPhaseClient, IProposalClient proposalClient,
            IMembershipClient membershipClient,
            IProposalMemberRatingClient proposalMemberRatingClient,
            IProposalJudgeRatingClient proposalJudgeRatingClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        Assert.notNull(proposalAttributeClient, "proposalAttributeClient must not be null!");
        Assert.notNull(proposalMoveClient, "proposalMoveClient must not be null!");
        Assert.notNull(proposalPhaseClient, "proposalPhaseClient must not be null!");
        Assert.notNull(proposalClient, "proposalClient must not be null!");
        Assert.notNull(membershipClient, "membershipClient must not be null!");
        Assert.notNull(proposalMemberRatingClient, "proposalMemberRatingClient must not be null!");
        Assert.notNull(proposalJudgeRatingClient, "proposalJudgeRatingClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
        StaticProposalContext.proposalAttributeClient = proposalAttributeClient;
        StaticProposalContext.proposalMoveClient = proposalMoveClient;
        StaticProposalContext.proposalPhaseClient = proposalPhaseClient;
        StaticProposalContext.proposalClient = proposalClient;
        StaticProposalContext.membershipClient = membershipClient;
        StaticProposalContext.proposalMemberRatingClient = proposalMemberRatingClient;
        StaticProposalContext.proposalJudgeRatingClient = proposalJudgeRatingClient;
    }

    public static PointsClient getPointsClient() {
        return pointsClient;
    }

    public static ProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }

    public static ProposalMoveClient getProposalMoveClient() {
        return proposalMoveClient;
    }

    public static ProposalPhaseClient getProposalPhaseClient() {
        return proposalPhaseClient;
    }

    public static IProposalClient getProposalClient() {
        return proposalClient;
    }

    public static IMembershipClient getMembershipClient() {
        return membershipClient;
    }

    public static IProposalMemberRatingClient getProposalMemberRatingClient() {
        return proposalMemberRatingClient;
    }

    public static IProposalJudgeRatingClient getProposalJudgeRatingClient() {
        return proposalJudgeRatingClient;
    }
}
