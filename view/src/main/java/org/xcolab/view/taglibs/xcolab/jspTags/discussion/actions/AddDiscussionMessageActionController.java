package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
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
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.activities.enums.ContestActivityType;
import org.xcolab.commons.activities.enums.DiscussionThreadActivityType;
import org.xcolab.commons.activities.enums.ProposalActivityType;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddDiscussionMessageActionController extends BaseDiscussionsActionController {

    private static final Logger _log =
            LoggerFactory.getLogger(AddDiscussionMessageActionController.class);

    private static final String COMMENT_ANALYTICS_KEY = "COMMENT_CONTEST_ENTRIES";
    private static final String COMMENT_ANALYTICS_CATEGORY = "User";
    private static final String COMMENT_ANALYTICS_ACTION = "Comment on contest entry";
    private static final String COMMENT_ANALYTICS_LABEL = "";


    @PostMapping("/discussions/addDiscussionMessage")
    public void handleAction(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "contestId", required = false) String contestId,
            NewMessageWrapper newMessage) throws IOException, DiscussionAuthorizationException {

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
                    ServiceNamespace serviceNamespace = ServiceNamespace.instance(
                            ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                    activityClient = ActivitiesClient.fromNamespace(serviceNamespace);
                    commentClient = CommentClient.fromService(serviceNamespace);
                    threadClient = ThreadClient.fromService(serviceNamespace);
                    proposalClient = ProposalClient.fromNamespace(serviceNamespace);
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

            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            final String body = HtmlUtil.cleanSome(newMessage.getDescription(), baseUri);

            Comment comment = new Comment();
            comment.setContent(body);
            comment.setAuthorId(memberId);
            comment.setThreadId(threadId);
            comment = commentClient.createComment(comment);
            CommentThread commentThread = threadClient.getThread(threadId);

            updateAnalyticsAndActivities(commentThread, comment, memberId, request);

            if (commentThread.getIsQuiet() != null && !commentThread.getIsQuiet()) {

                if (commentThread.getCategory() != null) {
                    activityClient.createActivityEntry(DiscussionThreadActivityType.COMMENT_ADDED,
                            memberId, commentThread.getThreadId(), comment.getCommentId());
                } else {
                    final Proposal proposal = getProposal(proposalClient, commentThread);
                    if (proposal != null) {
                        //proposal
                        activityClient.createActivityEntry(ProposalActivityType.COMMENT_ADDED,
                                memberId, proposal.getProposalId(), comment.getCommentId());
                    } else {
                        final Contest contest = getContest(commentThread);
                        if (contest != null) {
                            //contest
                            activityClient.createActivityEntry(ContestActivityType.COMMENT_ADDED,
                                    memberId, contest.getContestPK(), comment.getCommentId());

                            GoogleAnalyticsUtils.pushEventAsync(
                                    GoogleAnalyticsEventType.COMMENT_CONTEST);
                        }
                    }
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

        String redirectUrl = request.getHeader(HttpHeaders.REFERER);
        response.sendRedirect(redirectUrl);
    }

    private Contest getContest(CommentThread commentThread) {
        try {
            return ContestClientUtil.getContestByThreadId(commentThread.getThreadId());
        } catch (ContestNotFoundException e) {
            return null;
        }
    }

    private Proposal getProposal(ProposalClient proposalClient, CommentThread commentThread) {
        try {
            return proposalClient.getProposalByThreadId(commentThread.getThreadId());
        } catch (ProposalNotFoundException e) {
            return null;
        }
    }

    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId,
            HttpServletRequest request) {
        int commentCount = CommentClientUtil.countCommentsByAuthor(userId);
        if (commentCount > 0) {
            int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(commentCount);
            AnalyticsUtil.publishEvent(request, userId, COMMENT_ANALYTICS_KEY + analyticsValue,
                    COMMENT_ANALYTICS_CATEGORY, COMMENT_ANALYTICS_ACTION, COMMENT_ANALYTICS_LABEL,
                    analyticsValue);
        }
    }

    @Override
    public boolean isUserAllowed(DiscussionPermissions permissions, long additionalId) {
        return permissions.getCanAddComment();
    }
}
