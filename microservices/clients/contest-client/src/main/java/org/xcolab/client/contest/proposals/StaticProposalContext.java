package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static IPointsClient pointsClient;
    private static IProposalAttributeClient proposalAttributeClient;
    private static IProposalMoveClient proposalMoveClient;
    private static IProposalPhaseClient proposalPhaseClient;
    private static IProposalClient proposalClient;
    private static IMembershipClient membershipClient;
    private static IProposalMemberRatingClient proposalMemberRatingClient;
    private static IProposalJudgeRatingClient proposalJudgeRatingClient;

    private StaticProposalContext() {}

    public static void setClients(IPointsClient pointsClient,
            IProposalAttributeClient proposalAttributeClient, IProposalMoveClient proposalMoveClient,
            IProposalPhaseClient proposalPhaseClient, IProposalClient proposalClient,
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

    public static IPointsClient getPointsClient() {
        return pointsClient;
    }

    public static IProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }

    public static IProposalMoveClient getProposalMoveClient() {
        return proposalMoveClient;
    }

    public static IProposalPhaseClient getProposalPhaseClient() {
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
