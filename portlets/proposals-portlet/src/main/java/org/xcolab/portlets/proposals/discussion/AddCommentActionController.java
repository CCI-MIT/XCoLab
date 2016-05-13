package org.xcolab.portlets.proposals.discussion;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.Activity.DiscussionActivityKeys;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.jspTags.discussion.actions.AddDiscussionMessageActionController;

import javax.portlet.ActionRequest;

@Controller
@RequestMapping("view")
public class AddCommentActionController extends AddDiscussionMessageActionController {
    private static final String DEFAULT_GROUP_NAME = "Guest";

    @Override
    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId, ActionRequest request)
            throws SystemException, PortalException {
        super.updateAnalyticsAndActivities(thread, comment, userId, request);
        if (! thread.getIsQuiet()) {
            Group scopeGroup = GroupLocalServiceUtil.getGroup(
                    CompanyLocalServiceUtil.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID)).getCompanyId(),
                    DEFAULT_GROUP_NAME);
            SocialActivityLocalServiceUtil.addActivity(userId, scopeGroup.getGroupId(),
                    DiscussionCategoryGroup.class.getName(), thread.getCategory().getGroupId(),
                    DiscussionActivityKeys.ADD_PROPOSAL_DISCUSSION_COMMENT.id(),
                    ActivityUtil.getExtraDataForIds(thread.getCategoryId(),
                            thread.getThreadId(),
                            comment.getCommentId()), 0);
        }
    }
}
