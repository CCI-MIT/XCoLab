package org.xcolab.jspTags.discussion.actions;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.comment.exceptions.CommentNotFoundException;
import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.jspTags.discussion.DiscussionPermissions;
import org.xcolab.jspTags.discussion.exceptions.DiscussionAuthorizationException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

public abstract class BaseDiscussionsActionController {

    private static final Logger _log = LoggerFactory.getLogger(BaseDiscussionsActionController.class);

    public void checkPermissions(ActionRequest request, String accessDeniedMessage,
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

    public void redirectToReferrer(ActionRequest request, ActionResponse response)
            throws IOException {
        redirectToReferrer(request, response, null);
    }

    public void redirectToReferrer(ActionRequest request, ActionResponse response,
            Map<String, String> urlParametersToInclude) throws IOException {

        request.setAttribute("ACTION_REDIRECTING", true);

        HttpServletRequest httpRequest = PortletUtil.getHttpServletRequest(request);
        String referrer = httpRequest.getHeader("referer");

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
                _log.warn("couldn't parse referrer URL: " + referrer, e);
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