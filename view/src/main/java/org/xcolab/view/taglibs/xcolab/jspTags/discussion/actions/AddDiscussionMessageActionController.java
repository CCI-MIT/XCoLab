package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.activity.ActivityClient;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.Comment;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.util.activities.enums.ContestActivityType;
import org.xcolab.util.activities.enums.DiscussionThreadActivityType;
import org.xcolab.util.activities.enums.ProposalActivityType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.wrappers.NewMessageWrapper;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsEventType;
import org.xcolab.view.util.googleanalytics.GoogleAnalyticsUtils;

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

    @Autowired
    private ActivityClient activityClient;

    @PostMapping("/discussions/addDiscussionMessage")
    public String handleAction(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "contestId", required = false) String contestId,
            NewMessageWrapper newMessage) throws DiscussionAuthorizationException {

        final String referer = request.getHeader(HttpHeaders.REFERER);
        final String redirectUri = LinkUtils.getSafeRedirectUri(referer);

        if (StringUtils.isBlank(newMessage.getDescription())) {
            //TODO i18n: use message key discussion.commmentstag.valuerequired
            AlertMessage.danger("Please enter your comment").flash(request);
            return "redirect:" + redirectUri + "#addCommentForm";
        }

        long userId = MemberAuthUtil.getuserId(request);

        try {
            long threadId = Long.parseLong(newMessage.getThreadId());
            CommentThread commentThread = ThreadClient.instance().getThread(threadId);

            DiscussionPermissions discussionPermissions = getDiscussionPermissions(request, commentThread);

            if (!discussionPermissions.getCanAddComment()) {
                throw new DiscussionAuthorizationException("User isn't allowed to add comment");
            }

            final String baseUri = PlatformAttributeKey.COLAB_URL.get();
            final String body = HtmlUtil.cleanSome(newMessage.getDescription(), baseUri);

            Comment comment = new Comment();
            comment.setContent(body);
            comment.setAuthorUserId(userId);
            comment.setThreadId(threadId);
            comment = CommentClient.instance().createComment(comment);

            updateAnalyticsAndActivities(commentThread, comment, userId, request);

            if (commentThread.getIsQuiet() != null && !commentThread.getIsQuiet()) {

                if (commentThread.getCategory() != null) {
                    activityClient.createActivityEntry(DiscussionThreadActivityType.COMMENT_ADDED,
                            userId, commentThread.getId(), comment.getId());
                } else {
                    final ProposalClient proposalClient = ProposalClientUtil.getClient();
                    final Proposal proposal = getProposal(proposalClient, commentThread);
                    if (proposal != null) {
                        //proposal
                        activityClient
                                .createActivityEntry(ProposalActivityType.COMMENT_ADDED, userId,
                                        proposal.getId(), comment.getId());
                    } else {
                        final Contest contest = getContest(commentThread);
                        if (contest != null) {
                            //contest
                            activityClient.createActivityEntry(ContestActivityType.COMMENT_ADDED,
                                    userId, contest.getId(), comment.getId());

                            GoogleAnalyticsUtils.pushEventAsync(GoogleAnalyticsEventType.COMMENT_CONTEST);
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
        } catch (ThreadNotFoundException ignored) {
        }

        return "redirect:" + redirectUri;
    }

    @GetMapping("/discussions/addDiscussionMessage")
    public String handleInvalidHttpMethod(HttpServletRequest request) {
        AlertMessage.warning("Warning: page reloaded before comment was added.")
                .flash(request);
        String referrer = request.getHeader(HttpHeaders.REFERER);
        if (StringUtils.isNotEmpty(referrer) && LinkUtils.isLocalUrl(referrer)
                //avoid circular redirect
                && !referrer.endsWith("addDiscussionMessage")) {
            return "redirect:" + referrer;
        }
        return "redirect:/discussions";
    }

    private Contest getContest(CommentThread commentThread) {
        try {
            return ContestClientUtil.getContestByThreadId(commentThread.getId());
        } catch (ContestNotFoundException e) {
            return null;
        }
    }

    public void updateAnalyticsAndActivities(CommentThread thread, Comment comment, long userId,
            HttpServletRequest request) {
        int commentCount = CommentClient.instance().countCommentsByAuthor(userId);
        if (commentCount > 0) {
            int analyticsValue = AnalyticsUtil.getAnalyticsValueForCount(commentCount);
            AnalyticsUtil.publishEvent(request, userId, COMMENT_ANALYTICS_KEY + analyticsValue,
                    COMMENT_ANALYTICS_CATEGORY, COMMENT_ANALYTICS_ACTION, COMMENT_ANALYTICS_LABEL,
                    analyticsValue);
        }
    }
}
