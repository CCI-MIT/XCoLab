package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;

    private StaticProposalContext() {}

    public static void setClients(PointsClient pointsClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
    }

    public static PointsClient getPointsClient() {
        return pointsClient;
    }
}
