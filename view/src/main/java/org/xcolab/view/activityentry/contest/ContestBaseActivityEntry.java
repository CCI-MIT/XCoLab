package org.xcolab.view.activityentry.contest;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.util.activities.enums.ContestActivityType;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;

public abstract class ContestBaseActivityEntry extends AbstractActivityEntryContentProvider {

    private ContestWrapper contest;
    private ContestType contestType;

    @Override
    protected void initializeInternal() throws ActivityInitializationException {
        try {
            contest = new ContestWrapper(StaticContestContext.getContestClient().getContest(getActivityEntry()
                    .getCategoryId()));
        } catch (ContestNotFoundException e) {
            //TODO COLAB-2486: This won't be needed once legacy activities are fixed
            if (ContestActivityType.PROPOSAL_CREATED.equals(getActivityType())) {
                contest = new ProposalWrapper(StaticProposalContext.getProposalClient()
                        .getProposal(getActivityEntry().getAdditionalId(), true))
                        .getContest();
            } else {
                throw new ActivityInitializationException(getActivityEntry().getId(), e);
            }
        }

        contestType = contest.getContestType()
                .withLocale(LocaleContextHolder.getLocale().getLanguage());
    }

    protected ContestWrapper getContest() {
        return contest;
    }

    protected ContestType getContestType() {
        return contestType;
    }

    protected String getContestLink() {
        return String.format(HYPERLINK_FORMAT, getContest().getContestUrl() + "/discussion",
                getContest().getTitleWithEndYear());
    }
}
