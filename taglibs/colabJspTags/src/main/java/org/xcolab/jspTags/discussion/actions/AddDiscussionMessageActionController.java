package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {

    private final static Log _log = LogFactoryUtil.getLog(AddDiscussionMessageActionController.class);
    
    private final static String COMMENT_ANALYTICS_KEY = "COMMENT_CONTEST_ENTRIES";
    private final static String COMMENT_ANALYTICS_CATEGORY = "User";
    private final static String COMMENT_ANALYTICS_ACTION = "Comment on contest entry";
    private final static String COMMENT_ANALYTICS_LABEL = "";

    @RequestMapping(params = "action=addDiscussionMessage")
    public void handleAction(ActionRequest request, ActionResponse response, NewMessageWrapper newMessage)
            throws IOException, PortalException, SystemException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {
            long threadId = Long.parseLong(newMessage.getThreadId());
            long discussionCategoryGroupId = Long.parseLong(newMessage.getDiscussionId());

            DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(discussionCategoryGroupId);

            checkPermissions(request, "User isn't allowed to add comment", discussionCategoryGroupId, 0L);
            long userId = themeDisplay.getUser().getUserId();
            if (threadId == 0) {
                threadId = dcg.getCommentsThread();
            }

            final String title = HtmlUtil.cleanAll(newMessage.getTitle());
            final String body = HtmlUtil.cleanSome(newMessage.getDescription(), LinkUtils.getBaseUri(request));

            final DiscussionMessage comment;
            if (threadId == 0) {
                //create new thread
                comment = DiscussionMessageLocalServiceUtil.addThread(dcg.getId(), 0L, title, body, themeDisplay.getUser());
                dcg.setCommentsThread(comment.getMessageId());
                dcg.persist();

            } else {
                final DiscussionMessage thread = DiscussionMessageLocalServiceUtil.getThreadByThreadId(threadId);
                comment = DiscussionMessageLocalServiceUtil.addThreadMessage(thread, title, body, themeDisplay.getUser());
            }

            updateAnalyticsAndActivities(dcg, comment, userId, request);

            //delete the cached comment cookie, if it exists
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("proposal-comment-body")) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addProperty(cookie);
                    }
                }
            }
        } catch (NumberFormatException e) {
            _log.warn(String.format("Could not convert discussionId %s and threadId %s to longs (userId = %d)",
                    newMessage.getDiscussionId(), newMessage.getThreadId(), themeDisplay.getUserId()));
        }

        redirectToReferrer(request, response);
    }

    @SuppressWarnings("OverlyBroadThrowsClause")
    public void updateAnalyticsAndActivities(DiscussionCategoryGroup dcg, DiscussionMessage comment, long userId, ActionRequest request)
            throws SystemException, PortalException {
        // Update activity counter for user
        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
        indexer.reindex(userId);

        int commentCount = DiscussionCategoryGroupLocalServiceUtil.getUserMessages(userId);
        if (commentCount > 0) {
            int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(commentCount);
            AnalyticsUtil.publishEvent(request, userId, COMMENT_ANALYTICS_KEY + analyticsValue,
                    COMMENT_ANALYTICS_CATEGORY,
                    COMMENT_ANALYTICS_ACTION ,
                    COMMENT_ANALYTICS_LABEL,
                    analyticsValue);
        }
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanAddComment();
    }
}
