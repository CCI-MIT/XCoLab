package org.xcolab.view.taglibs.xcolab.jspTags.discussion.actions;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.client.RefreshingRestService;
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.view.taglibs.xcolab.jspTags.discussion.exceptions
        .DiscussionAuthorizationException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseDiscussionsActionController {

    private static final Logger _log = LoggerFactory.getLogger(BaseDiscussionsActionController.class);

    public void checkPermissions(HttpServletRequest request, String accessDeniedMessage,
            long additionalId)
            throws DiscussionAuthorizationException {

        DiscussionPermissions permissions = new DiscussionPermissions(request);

        try {
            if (!isUserAllowed(permissions, additionalId)) {
                throw new DiscussionAuthorizationException(accessDeniedMessage);
            }
        } catch (CommentNotFoundException e) {
            throw new DiscussionAuthorizationException(accessDeniedMessage);
        }
    }

    protected CommentClient getCommentClient(Long contestId) {
        CommentClient commentClient;
        if (contestId != null) {
            Contest contest = null;
            try {
                contest = ContestClientUtil.getContest(contestId);
            } catch (ContestNotFoundException ignored) {

            }
            if (contest != null && contest.getIsSharedContestInForeignColab()) {
                RestService commentsService = new RefreshingRestService(CoLabService.COMMENT,
                        ConfigurationAttributeKey.PARTNER_COLAB_NAMESPACE);

                commentClient = CommentClient.fromService(commentsService);
            } else {
                commentClient = CommentClientUtil.getClient();
            }
        } else {
            commentClient = CommentClientUtil.getClient();
        }
        return commentClient;
    }

    public void redirectToReferrer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        redirectToReferrer(request, response, null);
    }

    public void redirectToReferrer(HttpServletRequest request, HttpServletResponse response,
            Map<String, String> urlParametersToInclude) throws IOException {

        request.setAttribute("ACTION_REDIRECTING", true);

        String referrer = request.getHeader(HttpHeaders.REFERER);

        if (urlParametersToInclude != null && !urlParametersToInclude.isEmpty()) {
            try {
                //read current ones
                Map<String, String> targetParams = new HashMap<>();
                URI uri = new URI(referrer);
                for (NameValuePair nvp : URLEncodedUtils.parse(uri, "UTF-8")) {
                    targetParams.put(nvp.getName(), nvp.getValue());
                }
                //set new parameters & overwrite current ones
                for (Map.Entry<String, String> e : urlParametersToInclude.entrySet()) {
                    targetParams.put(e.getKey(), e.getValue());
                }

                //export URL
                List<NameValuePair> targetPairs = new LinkedList<>();
                for (Map.Entry<String, String> e : targetParams.entrySet()) {
                    targetPairs.add(new SimpleNameValuePair(e.getKey(), e.getValue()));
                }
                int splitIndex = referrer.indexOf("?");
                if (splitIndex < 0) {
                    splitIndex = referrer.length();
                }
                referrer = referrer.substring(0, splitIndex) + "?" + URLEncodedUtils
                        .format(targetPairs, "UTF-8");
            } catch (URISyntaxException e) {
                _log.warn("couldn't parse referrer URL: {}", referrer, e);
            }
        }

        response.sendRedirect(referrer);
    }

    public abstract boolean isUserAllowed(DiscussionPermissions permissions, long additionalId)
            throws CommentNotFoundException;

    private static class SimpleNameValuePair implements NameValuePair {

        String name, value;

        private SimpleNameValuePair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

}