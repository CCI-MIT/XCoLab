package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import org.xcolab.entity.utils.analytics.AnalyticsUtil;

import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {

    private final static Logger _log = LoggerFactory
            .getLogger(AddDiscussionMessageActionController.class);

    private final static String COMMENT_ANALYTICS_KEY = "COMMENT_CONTEST_ENTRIES";
    private final static String COMMENT_ANALYTICS_CATEGORY = "User";
    private final static String COMMENT_ANALYTICS_ACTION = "Comment on contest entry";
    private final static String COMMENT_ANALYTICS_LABEL = "";


    @PostMapping("/discussions/addDiscussionMessage")
    public void handleAction(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "contestId", required = false) String contestId,
            NewMessageWrapper newMessage)
            throws IOException, DiscussionAuthorizationException {

        long memberId = MemberAuthUtil.getMemberId(request);


        try {
            final CommentClient commentClient;
            final ThreadClient threadClient;
            final ActivitiesClient activityClient;
            final ProposalClient proposalClient;


            if (contestId != null && !contestId.isEmpty() && !contestId.equals("0")) {
                Long contestIdLong = Long.parseLong(contestId);

                Contest contest = ContestClientUtil.getContest(contestIdLong);
                if (contest.getIsSharedContestInForeignColab()) {
                    RestService activitiesService = new RefreshingRestService(CoLabService.ACTIVITY,
                            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                    activityClient = ActivitiesClient.fromService(activitiesService);
                    RestService commentsService = new RefreshingRestService(CoLabService.COMMENT,
                            ConfigurationAttributeKey.PARTNER_COLAB_LOCATION,
                            ConfigurationAttributeKey.PARTNER_COLAB_PORT);

                    commentClient = CommentClient.fromService(commentsService);
                    threadClient = ThreadClient.fromService(commentsService);
                    RestService proposalsService = new RefreshingRestService(CoLabService.PROPOSAL,
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

            // Since linebreaks are escaped by HtmlUtil
            String body = newMessage.getDescription().replaceAll("\\r\\n|\\r|\\n", "</br>");
            //final String body = HtmlUtil.cleanSome(newMessage.getDescription(), LinkUtils
            // .getBaseUri(request));
            Comment comment = new Comment();
            comment.setContent(body);
            comment.setAuthorId(memberId);
            comment.setThreadId(threadId);
            comment = commentClient.createComment(comment);
            CommentThread commentThread = threadClient.getThread(threadId);

            updateAnalyticsAndActivities(commentThread, comment, memberId, request);

            if (commentThread.getIsQuiet() != null && !commentThread.getIsQuiet()) {

                if (commentThread.getCategory() == null) {
                    final Long proposalIdForThread = threadClient
                            .getProposalIdForThread(commentThread.getThreadId());
                    if (proposalIdForThread != null && proposalIdForThread != 0L) {


                        ActivityEntryHelper.createActivityEntry(activityClient, memberId,
                                commentThread.getThreadId(),
                                comment.getCommentId() + "",
                                ActivityProvidersType.DiscussionAddProposalCommentActivityEntry
                                        .getType());
                        try {
                            Contest contest = proposalClient
                                    .getCurrentContestForProposal(proposalIdForThread);
                            SharedColabUtil.checkTriggerForAutoUserCreationInContest(
                                    contest.getContestPK(), memberId);
                        } catch (ContestNotFoundException ignored) {

                        }
                    }
                } else {
                    ActivityEntryHelper.createActivityEntry(activityClient, memberId,
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
                    filteredEntry.setAuthorId(memberId);
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
                        response.addCookie(cookie);
                    }
                }
            }
        } catch (ContestNotFoundException e1) {
            _log.warn("Could not find contest ");
        } catch (NumberFormatException e) {
            _log.warn(String.format(
                    "Could not convert discussionId %s and threadId %s to longs (userId = %d)",
                    newMessage.getDiscussionId(), newMessage.getThreadId(), memberId));
        } catch (ThreadNotFoundException ignored) {
        }

        redirectToReferrer(request, response);
    }

    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId,
            HttpServletRequest request) {
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
