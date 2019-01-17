package org.xcolab.client.contest.resources;

import org.xcolab.util.http.client.CoLabService;
import org.xcolab.util.http.client.enums.ResourceEnum;

public enum ProposalResource implements ResourceEnum {
    PROPOSAL("proposals"),
    PROPOSAL_2_PHASE("proposal2Phases"),
    PROPOSAL_CONTEST_PHASE_ATTRIBUTE("proposalContestPhaseAttributes"),
    PROPOSAL_SUPPORTER("proposalSupporters"),
    PROPOSAL_VOTE("proposalVotes"),
    PROPOSAL_REFERENCE("proposalReferences"),
    PROPOSAL_VERSION("proposalVersions"),
    PROPOSAL_MOVE_HISTORY("proposalMoveHistories"),
    PROPOSAL_ATTRIBUTE("proposalAttributes"),
    PROPOSAL_UNVERSIONED_ATTRIBUTE("proposalUnversionedAttributes"),

    PROPOSAL_RATING("proposalRatings"),
    PROPOSAL_RATING_VALUE("proposalRatingValues"),
    PROPOSAL_RATING_TYPE("proposalRatingTypes"),

    SUPPORTED_PROPOSALS("supportedProposals"),

    GROUP("groups"),
    MEMBERSHIP_REQUEST("membershipRequests"),

    //TODO COLAB-2594: rethink these endpoints
    PROPOSAL_ID("proposalIds"),
    PROPOSAL_THREAD_ID("proposalThreadIds");

    private final String resourceName;

    ProposalResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getResourceName() {
        return resourceName;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.CONTEST;
    }
}
