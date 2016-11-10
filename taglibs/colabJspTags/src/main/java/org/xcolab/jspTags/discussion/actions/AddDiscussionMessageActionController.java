package org.xcolab.jspTags.discussion.actions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.liferay.SharedColabUtil;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.Cookie;

@Controller
@RequestMapping("view")
public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {

    private final static Log _log = LogFactoryUtil
            .getLog(AddDiscussionMessageActionController.class);

    private final static String COMMENT_ANALYTICS_KEY = "COMMENT_CONTEST_ENTRIES";
    private final static String COMMENT_ANALYTICS_CATEGORY = "User";
    private final static String COMMENT_ANALYTICS_ACTION = "Comment on contest entry";
    private final static String COMMENT_ANALYTICS_LABEL = "";

    @RequestMapping(params = "action=addDiscussionMessage")
    public void handleAction(ActionRequest request, ActionResponse response,
            @RequestParam(value = "contestId", required = false) String contestId,
            NewMessageWrapper newMessage)
            throws IOException, PortalException, SystemException, DiscussionAuthorizationException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        try {


            final CommentClient commentClient;
            final ThreadClient threadClient;
            final ActivitiesClient activityClient;
            final ProposalClient proposalClient;


            if (contestId != null && !contestId.equals("")) {
                Long contestIdLong = Long.parseLong(contestId);

                Contest contest = ContestClientUtil.getContest(contestIdLong);
                if (contest.getIsSharedContestInForeignColab()) {
                    RestService activitiesService = new RefreshingRestService("activities-service",
                            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                    activityClient = ActivitiesClient.fromService(activitiesService);
                    RestService commentsService = new RefreshingRestService("comment-service",
                            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                    commentClient = CommentClient.fromService(commentsService);
                    threadClient = ThreadClient.fromService(commentsService);
                    RestService proposalsService = new RefreshingRestService("proposals-service",
                            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                    proposalClient = ProposalClient.fromService(proposalsService);
                } else {
                    threadClient = ThreadClientUtil.getClient();
                    commentClient = CommentClientUtil.getClient();
                    activityClient = ActivitiesClientUtil.getClient();
                    proposalClient = ProposalClientUtil.getClient();
                }
            } else {
                threadClient = ThreadClientUtil.getClient();
                commentClient = CommentClientUtil.getClient();
                activityClient = ActivitiesClientUtil.getClient();
                proposalClient = ProposalClientUtil.getClient();
            }


            long threadId = Long.parseLong(newMessage.getThreadId());

            checkPermissions(request, "User isn't allowed to add comment", 0L);
            long userId = themeDisplay.getUser().getUserId();

            // Since linebreaks are escaped by HtmlUtil
            String body = newMessage.getDescription().replaceAll("\\r\\n|\\r|\\n", "</br>");
            //final String body = HtmlUtil.cleanSome(newMessage.getDescription(), LinkUtils
            // .getBaseUri(request));
            Comment comment = new Comment();
            comment.setContent(body);
            comment.setAuthorId(userId);
            comment.setThreadId(threadId);
            comment = commentClient.createComment(comment);
            CommentThread commentThread = threadClient.getThread(threadId);

            updateAnalyticsAndActivities(commentThread, comment, userId, request);

            if (!commentThread.getIsQuiet()) {

                if (commentThread.getCategory() == null) {
                    final Long proposalIdForThread = threadClient
                            .getProposalIdForThread(commentThread.getThreadId());
                    if (proposalIdForThread != null && proposalIdForThread != 0L) {


                        ActivityEntryHelper.createActivityEntry(activityClient, userId,
                                commentThread.getThreadId(),
                                comment.getCommentId() + "",
                                ActivityProvidersType.DiscussionAddProposalCommentActivityEntry
                                        .getType());
                        try {
                            Contest contest = proposalClient
                                    .getCurrentContestForProposal(proposalIdForThread);
                            SharedColabUtil.checkTriggerForAutoUserCreationInContest(
                                    contest.getContestPK(), userId);
                        } catch (ContestNotFoundException ignored) {

                        }
                    }
                } else {
                    ActivityEntryHelper.createActivityEntry(activityClient, userId,
                            commentThread.getCategory().getCategoryId(),
                            comment.getCommentId() + "",
                            ActivityProvidersType.DiscussionAddCommentActivityEntry.getType());
                }
            }
            if (ConfigurationAttributeKey.FILTER_PROFANITY.get()) {
                try {
                    FilteredEntry filteredEntry = FilteringClient
                            .getFilteredEntryByUuid(newMessage.getUuid());
                    filteredEntry.setSourceId(comment.getCommentId());
                    filteredEntry.setAuthorId(userId);
                    FilteringClient.updateFilteredEntry(filteredEntry);
                } catch (FilteredEntryNotFoundException ignored) {
                }
            }

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
        } catch (ContestNotFoundException e1) {
            _log.warn("Could not find contest ");
        } catch (NumberFormatException e) {
            _log.warn(String.format(
                    "Could not convert discussionId %s and threadId %s to longs (userId = %d)",
                    newMessage.getDiscussionId(), newMessage.getThreadId(),
                    themeDisplay.getUserId()));
        } catch (ThreadNotFoundException ignored) {
        }

        redirectToReferrer(request, response);
    }

    @SuppressWarnings("OverlyBroadThrowsClause")
    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId,
            ActionRequest request)
            throws SystemException, PortalException {
        // Update activity counter for user
        Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);
        indexer.reindex(userId);

        int commentCount = CommentClientUtil.countCommentsByAuthor(userId);
        if (commentCount > 0) {
            int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(commentCount);
            AnalyticsUtil.publishEvent(request, userId, COMMENT_ANALYTICS_KEY + analyticsValue,
                    COMMENT_ANALYTICS_CATEGORY,
                    COMMENT_ANALYTICS_ACTION,
                    COMMENT_ANALYTICS_LABEL,
                    analyticsValue);
        }
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanAddComment();
    }
}
