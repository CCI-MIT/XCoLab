package org.xcolab.view.activityentry.proposal;

import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

import java.util.Arrays;
import java.util.List;

public abstract class ProposalBaseActivityEntry extends AbstractActivityEntryContentProvider {

    private final ResourceMessageResolver resourceMessageResolver;

    private ProposalWrapper proposal;
    private ContestType contestType;

    public ProposalBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void initializeInternal() throws ActivityInitializationException {
        try {
            proposal = new ProposalWrapper(StaticProposalContext.getProposalClient()
                    .getProposal(getActivityEntry().getCategoryId()));
        } catch (ProposalNotFoundException e) {
            throw new ActivityInitializationException(getActivityEntry().getId(), e);
        }

        contestType = proposal.getContest().getContestType()
                .withLocale(LocaleContextHolder.getLocale().getLanguage());
    }

    protected ProposalWrapper getProposal() {
        return proposal;
    }

    protected List<Object> getBodyTemplateParams() {
        return Arrays.asList(getUserLink(), contestType.getProposalNameLowercase(),
                getProposalLink());
    }

    @Override
    public String getBody() {
        final List<Object> params = getBodyTemplateParams();
        return resourceMessageResolver.getLocalizedMessage(getBodyTemplate(), params.toArray());
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
