package org.xcolab.view.pages.proposals.view.proposal.json.picker;

public class CountsResult {
    private final int numberOfSubscriptions;
    private final int numberOfSupporting;
    private final int numberOfProposals;
    private final int numberOfSubscriptionsSupporting;
    private final int numberOfContests;

    public CountsResult(int numberOfSubscriptions, int numberOfSupporting,
        int numberOfProposals, int numberOfSubscriptionsSupporting, int numberOfContests) {
        this.numberOfSubscriptions = numberOfSubscriptions;
        this.numberOfSupporting = numberOfSupporting;
        this.numberOfProposals = numberOfProposals;
        this.numberOfSubscriptionsSupporting = numberOfSubscriptionsSupporting;
        this.numberOfContests = numberOfContests;
    }

    public int getNumberOfSubscriptions() {
        return numberOfSubscriptions;
    }

    public int getNumberOfSupporting() {
        return numberOfSupporting;
    }

    public int getNumberOfProposals() {
        return numberOfProposals;
    }

    public int getNumberOfSubscriptionsSupporting() {
        return numberOfSubscriptionsSupporting;
    }

    public int getNumberOfContests() {
        return numberOfContests;
    }
}
