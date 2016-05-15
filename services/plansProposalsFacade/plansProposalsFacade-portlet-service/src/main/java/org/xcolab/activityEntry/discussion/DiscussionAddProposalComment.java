package org.xcolab.activityEntry.discussion;

import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xcolab.client.members.pojo.Member;

public class DiscussionAddProposalComment extends DiscussionBaseActivityEntry {

    private static final Log _log = LogFactoryUtil.getLog(DiscussionAddProposalComment.class);

    public static final String HYPERLINK_FORMAT = "<a href=\"%s\">%s</a>";

    @Override
    public Integer getSecondaryType() {
        return null;
    }

    @Override
    public String getBody() {
        String template = "%s added a comment to %s";

        return String.format(template, getUserLink(this.activityEntry.getMemberId()),
                getCategoryGroupLink();

    }

    public String getCategoryGroupLink() {
        return String.format(HYPERLINK_FORMAT, StringEscapeUtils.escapeHtml4(categoryGroup.getUrl()), categoryGroup.getDescription());
    }

    private String getUserLink(Long memberId) {
        try {
            return CommunityUtil.generateUserURL(memberId);
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
