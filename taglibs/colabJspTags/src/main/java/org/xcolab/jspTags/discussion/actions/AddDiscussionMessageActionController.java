package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.Cookie;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {
    
    private final static String COMMENT_ANALYTICS_KEY = "COMMENT_CONTEST_ENTRIES";
    private final static String COMMENT_ANALYTICS_CATEGORY = "User";
    private final static String COMMENT_ANALYTICS_ACTION = "Comment on contest entry";
    private final static String COMMENT_ANALYTICS_LABEL = "";

    @RequestMapping(params = "action=addDiscussionMessage")
    public void handleAction(ActionRequest request, ActionResponse response, NewMessageWrapper newMessage)
            throws IOException, PortalException, SystemException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.fetchDiscussionCategoryGroup(newMessage.getDiscussionId());

        checkPermissions(request, "User isn't allowed to add comment", newMessage.getDiscussionId(), 0L);
        long userId = themeDisplay.getUser().getUserId();
        long threadId = newMessage.getThreadId();
        if (threadId == 0) {
            threadId = dcg.getCommentsThread();
        }

        final String title = HtmlUtil.cleanAll(newMessage.getTitle());
        final String body = HtmlUtil.cleanSome(newMessage.getDescription());

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

        redirectToReferrer(request, response);
    }

    public void updateAnalyticsAndActivities(DiscussionCategoryGroup dcg, DiscussionMessage comment, long userId, ActionRequest request)
            throws SystemException, PortalException {
        // Update activity counter for user
        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
        indexer.reindex(userId);

        int supportedCount = DiscussionCategoryGroupLocalServiceUtil.getUserMessages(userId);
        if (supportedCount > 0) {
            int analyticsValue;
            if (supportedCount == 1) {
                analyticsValue = 1;
            } else if ( supportedCount < 5) {
                analyticsValue = 2;
            } else {
                analyticsValue = 3;
            }
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
