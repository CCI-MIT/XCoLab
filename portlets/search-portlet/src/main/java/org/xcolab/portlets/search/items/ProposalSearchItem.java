package org.xcolab.portlets.search.items;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.search.pojo.SearchPojo;
import org.xcolab.helpers.ProposalAttributeHelper;

import java.io.IOException;

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
            proposalAttribute = ProposalAttributeLocalServiceUtil.getProposalAttribute(searchPojo.getClassPrimaryKey());
            proposal = ProposalLocalServiceUtil.getProposal(proposalAttribute.getProposalId());
            ProposalAttributeHelper proposalAttributeHelper = new ProposalAttributeHelper(proposal);

            proposalName = proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");

        } catch (SystemException | PortalException ignored) {

        }
    }

    @Override
    public String getPrintName() {
        try {
            final long contestTypeId =
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue();
            final ContestType contestType = ContestTypeLocalServiceUtil
                    .getContestType(contestTypeId);
            return contestType.getProposalNamePlural();
        } catch (PortalException | SystemException e) {
            return "Proposals";
        }
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
