package org.xcolab.view.pages.search.items;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.helpers.ProposalAttributeHelper;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.search.pojo.SearchPojo;

public class ProposalSearchItem extends AbstractSearchItem {

    private Proposal proposal;

    private ProposalAttribute proposalAttribute;

    private SearchPojo searchPojo;

    private String searchQuery;

    private String proposalName;

    @Override
    public void init(SearchPojo pojo, String searchQuery) {
        try {
            searchPojo = pojo;
            this.searchQuery = searchQuery;
            proposalAttribute = ProposalAttributeClientUtil
                    .getProposalAttribute(searchPojo.getClassPrimaryKey());
            proposal = ProposalClientUtil.getProposal(proposalAttribute.getProposalId(), true);
            ProposalAttributeHelper proposalAttributeHelper =
                    new ProposalAttributeHelper(proposal, ProposalAttributeClientUtil.getClient());

            proposalName =
                    proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");

        } catch (ProposalAttributeNotFoundException | ProposalNotFoundException ignored) {

        }
    }

    @Override
    public String getPrintName() {

        final long contestTypeId = ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
        final ContestType contestType = ContestTypeClient.getContestType(contestTypeId);
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
        return proposal.getVisible();
    }

}
