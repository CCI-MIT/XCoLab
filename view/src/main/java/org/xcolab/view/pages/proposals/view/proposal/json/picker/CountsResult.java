package org.xcolab.view.pages.proposals.view.proposal.json.picker;

public class CountsResult {
    private final int numberOfProposals;
    private final int numberOfSubscriptionsSupporting;
    private final int numberOfContests;

    public CountsResult(int numberOfProposals, int numberOfSubscriptionsSupporting,
            int numberOfContests) {
        this.numberOfProposals = numberOfProposals;
        this.numberOfSubscriptionsSupporting = numberOfSubscriptionsSupporting;
        this.numberOfContests = numberOfContests;
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
