package org.xcolab.portlets.search.items;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import java.io.IOException;

public class ProposalSearchItem extends AbstractSearchItem {

    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content", "pitch", "sections"};

    @Override
    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        return concatFields(TITLE_FIELDS, doc, highlighter);
    }

    @Override
    public String getLinkUrl(Document doc) {
        try {
            return ProposalLocalServiceUtil.getProposalLinkUrl(getProposalId(doc));
        } catch (PortalException | SystemException e) {
            return "/contests";
        }
    }

    @Override
    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(CONTENT_FIELDS, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }


    public long getProposalId(Document doc) {
        String idStr = doc.get(Field.ENTRY_CLASS_PK);
        return Long.parseLong(idStr);
    }

    public Proposal getProposal(Document doc) throws SystemException, PortalException {
        return ProposalLocalServiceUtil.getProposal(getProposalId(doc));
    }
}
