package org.xcolab.view.activityentry.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.commons.activities.enums.ActivityType;
import org.xcolab.commons.activities.enums.ContestActivityType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class ProposalCreatedActivityEntry extends ContestBaseActivityEntry {

    private static final String MESSAGE_CODE = "activities.proposal.created.message";

    private final ResourceMessageResolver resourceMessageResolver;

    private Proposal proposal;

    @Autowired
    public ProposalCreatedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    protected void initializeInternal() throws ActivityInitializationException {
        super.initializeInternal();

        try {
            proposal = ProposalClientUtil.getProposal(getActivityEntry().getAdditionalId(), true);
        } catch (ProposalNotFoundException e) {
            throw new ActivityInitializationException(getActivityEntry().getActivityEntryId(), e);
        }
    }

    @Override
    public ActivityType getActivityType() {
        return ContestActivityType.PROPOSAL_CREATED;
    }

    @Override
    public String getBody() {
        //TODO COLAB-2497: include contest?
        String[] params = {getUserLink(), getContestType().getProposalName(), getProposalLink()};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return String.format("New %s in %s", getContestType().getProposalNameLowercase(),
                getContest().getContestShortName());
    }

    private String getProposalLink() {
        return "<a href='" + proposal.getProposalUrl() + "'>" + proposal.getName() + "</a>";
    }
}
