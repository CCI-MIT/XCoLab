package org.xcolab.portlets.search.items;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;

import java.io.IOException;

public class ContestSearchItem extends AbstractSearchItem {
    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

    @Override
    public String getPrintName() {
        try {
            final long contestTypeId =
                    ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.getLongValue();
            final ContestType contestType = ContestTypeLocalServiceUtil
                    .getContestType(contestTypeId);
            return contestType.getContestNamePlural();
        } catch (PortalException | SystemException e) {
            return "Contests";
        }
    }

    @Override
    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        return concatFields(TITLE_FIELDS, doc, highlighter);
    }

    @Override
    public String getLinkUrl(Document doc) throws SystemException {
        try {
            return ContestLocalServiceUtil.getContestLinkUrl(getContest(doc));
        } catch (PortalException e) {
            return "/contests";
        }
    }

    @Override
    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(CONTENT_FIELDS, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }

    public long getContestId(Document doc) {
        String idStr = doc.get(Field.ENTRY_CLASS_PK);
        return Long.parseLong(idStr);
    }

    public Contest getContest(Document doc) throws SystemException, PortalException {
        return ContestLocalServiceUtil.getContest(getContestId(doc));
    }
}
