package org.xcolab.client.contest.proposals;

import org.springframework.util.Assert;

public class StaticProposalContext {

    private static PointsClient pointsClient;

    private static IMembershipClient membershipClient;

    private StaticProposalContext() {}

    public static void setPointsClient(PointsClient pointsClient) {
        Assert.notNull(pointsClient, "pointsClient must not be null!");
        StaticProposalContext.pointsClient = pointsClient;
    }

    public static void setMembershipClient(IMembershipClient membershipClient) {
        Assert.notNull(membershipClient, "membershipClient must not be null!");
        StaticProposalContext.membershipClient = membershipClient;
    }

    public static PointsClient getPointsClient() {
        return pointsClient;
    }

    public static IMembershipClient getMembershipClient() {
        return membershipClient;
    }
}
