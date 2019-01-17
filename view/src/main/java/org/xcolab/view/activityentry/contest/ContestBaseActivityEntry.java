package org.xcolab.view.activityentry.contest;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.util.activities.enums.ContestActivityType;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;

public abstract class ContestBaseActivityEntry extends AbstractActivityEntryContentProvider {

    private Contest contest;
    private ContestType contestType;

    @Override
    protected void initializeInternal() throws ActivityInitializationException {
        try {
            contest = ContestClientUtil.getContest(getActivityEntry().getCategoryId());
        } catch (ContestNotFoundException e) {
            //TODO COLAB-2486: This won't be needed once legacy activities are fixed
            if (ContestActivityType.PROPOSAL_CREATED.equals(getActivityType())) {
                contest = ProposalClientUtil.getProposal(getActivityEntry().getAdditionalId(), true)
                        .getContest();
            } else {
                throw new ActivityInitializationException(getActivityEntry().getId(), e);
            }
        }

        contestType = contest.getContestType()
                .withLocale(LocaleContextHolder.getLocale().getLanguage());
    }

    protected Contest getContest() {
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
