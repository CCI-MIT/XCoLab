package org.xcolab.view.pages.search.items;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.search.pojo.ISearchPojo;

public class ProposalSearchItem extends AbstractSearchItem {

    private ProposalWrapper proposal;

    private ProposalAttribute proposalAttribute;

    private ISearchPojo searchPojo;

    private String searchQuery;

    private String proposalName;

    @Override
    public void init(ISearchPojo pojo, String searchQuery) {
        try {
            searchPojo = pojo;
            this.searchQuery = searchQuery;
            proposalAttribute = StaticProposalContext.getProposalAttributeClient()
                    .getProposalAttribute(searchPojo.getClassPrimaryKey());
            proposal = new ProposalWrapper(StaticProposalContext.getProposalClient()
                    .getProposal(proposalAttribute.getProposalId(), true));
            ProposalAttributeHelper proposalAttributeHelper =
                    new ProposalAttributeHelper(proposal, StaticProposalContext
                            .getProposalAttributeClient());

            proposalName =
                    proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");

        } catch (ProposalAttributeNotFoundException | ProposalNotFoundException ignored) {
        }
    }

    @Override
    public String getPrintName() {
        final long contestTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        final ContestType contestType =
                StaticAdminContext.getContestTypeClient().getContestType(contestTypeId);
        return contestType.getProposalNamePlural();
    }

    @Override
    public String getTitle() {
        return highlight(proposalName, searchQuery);
    }

    @Override
    public String getLinkUrl() {
        if (proposal != null) {
            return proposal.getProposalUrl();
        } else {
            return "";
        }
    }

    @Override
    public String getContent() {
        return getContent(proposalAttribute.getStringValue(), searchQuery);
    }

    @Override
    public boolean isVisible() {
        return proposal.isVisible();
    }
}
