package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;
    private static ProposalAttributeClient proposalAttributeClient;
    private static ProposalMoveClient proposalMoveClient;

    private StaticProposalContext() {}

    public static void setClients(PointsClient pointsClient,
            ProposalAttributeClient proposalAttributeClient,
            ProposalMoveClient proposalMoveClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        Assert.notNull(proposalAttributeClient, "proposalAttributeClient must not be null!");
        Assert.notNull(proposalMoveClient, "proposalMoveClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
        StaticProposalContext.proposalAttributeClient = proposalAttributeClient;
        StaticProposalContext.proposalMoveClient = proposalMoveClient;
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
}
