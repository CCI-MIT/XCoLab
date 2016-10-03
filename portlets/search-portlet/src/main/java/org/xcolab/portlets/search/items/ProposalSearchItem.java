package org.xcolab.portlets.search.items;


import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalAttribute;
import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.helpers.ProposalAttributeHelper;

public class ProposalSearchItem extends AbstractSearchItem {

    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content", "pitch", "sections"};

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
            proposalAttribute = ProposalsClient.getProposalAttribute(searchPojo.getClassPrimaryKey());
            proposal = ProposalsClient.getProposal(proposalAttribute.getProposalId());
            ProposalAttributeHelper proposalAttributeHelper = new ProposalAttributeHelper(proposal);

            proposalName = proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");

        } catch (ProposalAttributeNotFoundException | ProposalNotFoundException ignored) {

        }
    }

    @Override
    public String getPrintName() {

            final long contestTypeId =
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get();
            final ContestType contestType = ContestClient
                    .getContestType(contestTypeId);
            return contestType.getProposalNamePlural();

    }

    @Override
    public String getTitle() {
        return highlight(proposalName, searchQuery);
    }

    @Override
    public String getLinkUrl() {
        try {
            return ProposalLocalServiceUtil.getProposalLinkUrl(proposal.getProposalId());
        } catch (PortalException | SystemException e) {
            return "/contests";
        }
    }

    @Override
    public String getContent() {
        String content = highlight(proposalAttribute.getStringValue(),searchQuery);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";

    }


}
