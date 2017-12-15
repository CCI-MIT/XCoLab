package org.xcolab.view.activityentry.proposal;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

public abstract class ProposalBaseActivityEntry extends AbstractActivityEntryContentProvider {

    private final ResourceMessageResolver resourceMessageResolver;

    private Proposal proposal;
    private ContestType contestType;

    public ProposalBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {
        try {
            proposal = ProposalClientUtil.getProposal(getActivityEntry().getCategoryId());
        } catch (ProposalNotFoundException e) {
            throw new ActivityInitializationException(getActivityEntry().getActivityEntryId(), e);
        }

        contestType = proposal.getContest().getContestType()
                .withLocale(LocaleContextHolder.getLocale().getLanguage());
    }

    protected Proposal getProposal() {
        return proposal;
    }

    @Override
    public String getBody() {
        String[] params = {getUserLink(), contestType.getProposalNameLowercase(), getProposalLink()};
        return resourceMessageResolver.getLocalizedMessage(getBodyTemplate(), params);
    }

    @Override
    public String getTitle() {
        return getTitleTemplate().replaceAll("<proposal/>", contestType.getProposalName());
    }

    protected abstract String getTitleTemplate();

    protected abstract String getBodyTemplate();

    private String getProposalLink() {
        return String.format(HYPERLINK_FORMAT, getProposalLinkUrl(), proposal.getName());
    }

    protected String getProposalLinkUrl() {
        return proposal.getProposalUrl();
    }
}
