package org.xcolab.portlets.search.items;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.xcolab.jspTags.discussion.wrappers.ThreadWrapper;

import java.io.IOException;

public class DiscussionSearchItem extends AbstractSearchItem {

    private final static String[] TITLE_FIELDS = {"title"};
    private final static String[] CONTENT_FIELDS = {"content"};

    @Override
    public String getTitle(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String title = concatFields(TITLE_FIELDS, doc, highlighter);
        if (StringUtils.isBlank(title)) {
            try {
                DiscussionCategoryGroup dcg = getCategoryGroup(doc);
                return "Discussion for " + dcg.getDescription();
            } catch (SystemException | PortalException ignored) { }
        }
        return "Comment";
    }

    @Override
    public String getLinkUrl(Document doc) throws SystemException {
        String idStr = doc.get(Field.ENTRY_CLASS_PK);
        Long messageId = Long.parseLong(idStr);
        try {
            DiscussionMessage msg = DiscussionMessageLocalServiceUtil.getDiscussionMessage(messageId);

            Long threadId = msg.getThreadId();
            if (threadId == 0) {
                threadId = messageId;
            }
            return new ThreadWrapper(threadId).getLinkUrl();
        } catch (SystemException | PortalException e) {
            return "";
        }
    }

    @Override
    public String getContent(Document doc, Highlighter highlighter) throws IOException, InvalidTokenOffsetsException {
        String content = concatFields(CONTENT_FIELDS, doc, highlighter);
        return content.substring(0, Math.min(content.length(), MAX_CONTENT_LENGTH)) + " ...";
    }

    public long getMessageId(Document doc) {
        String idStr = doc.get(Field.ENTRY_CLASS_PK);
        return Long.parseLong(idStr);
    }

    public DiscussionMessage getMessage(Document doc) throws SystemException, PortalException {
        return DiscussionMessageLocalServiceUtil.getDiscussionMessage(getMessageId(doc));
    }

    public DiscussionCategoryGroup getCategoryGroup(Document doc) throws SystemException, PortalException {
        DiscussionMessage message = getMessage(doc);
        return DiscussionCategoryGroupLocalServiceUtil
                .getDiscussionCategoryGroup(message.getCategoryGroupId());
    }

    @Override
    public boolean isVisible(Document doc) {
        try {
            DiscussionMessage message = getMessage(doc);
            DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil
                    .getDiscussionCategoryGroup(message.getCategoryGroupId());
            return !dcg.isIsQuiet();
        } catch (SystemException | PortalException e) {
            return false;
        }
    }
}
