package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;
    private static ProposalAttributeClient proposalAttributeClient;

    private StaticProposalContext() {}

    public static void setClients(PointsClient pointsClient,
            ProposalAttributeClient proposalAttributeClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        Assert.notNull(proposalAttributeClient, "proposalAttributeClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
        StaticProposalContext.proposalAttributeClient = proposalAttributeClient;
    }

    public static PointsClient getPointsClient() {
        return pointsClient;
    }

    public static ProposalAttributeClient getProposalAttributeClient() {
        return proposalAttributeClient;
    }
}
