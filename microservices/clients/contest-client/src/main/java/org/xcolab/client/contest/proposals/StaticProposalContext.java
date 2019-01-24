package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;
    private static ProposalAttributeClient proposalAttributeClient;
    private static ProposalMoveClient proposalMoveClient;
    private static ProposalPhaseClient proposalPhaseClient;
    private static IProposalClient proposalClient;

    private StaticProposalContext() {}

    public static void setClients(PointsClient pointsClient,
            ProposalAttributeClient proposalAttributeClient, ProposalMoveClient proposalMoveClient,
            ProposalPhaseClient proposalPhaseClient, IProposalClient proposalClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        Assert.notNull(proposalAttributeClient, "proposalAttributeClient must not be null!");
        Assert.notNull(proposalMoveClient, "proposalMoveClient must not be null!");
        Assert.notNull(proposalPhaseClient, "proposalPhaseClient must not be null!");
        Assert.notNull(proposalClient, "proposalClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
        StaticProposalContext.proposalAttributeClient = proposalAttributeClient;
        StaticProposalContext.proposalMoveClient = proposalMoveClient;
        StaticProposalContext.proposalPhaseClient = proposalPhaseClient;
        StaticProposalContext.proposalClient = proposalClient;
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
}
