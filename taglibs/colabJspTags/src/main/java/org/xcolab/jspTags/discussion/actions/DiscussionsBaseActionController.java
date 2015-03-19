package org.xcolab.jspTags.discussion.actions;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.xcolab.jspTags.discussion.DiscussionPermission;
import org.xcolab.jspTags.discussion.exceptions.DiscussionsException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class DiscussionsBaseActionController {

    public void checkPermissions(ActionRequest request, String accessDeniedMessage, long discussionId)
            throws DiscussionsException, PortalException, SystemException {

        DiscussionCategoryGroup dcg = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(discussionId);
        DiscussionPermission permissions = new DiscussionPermission(request, dcg);

        if (!isUserAllowed(permissions)) {
            throw new DiscussionsException(accessDeniedMessage);
        }
    }

    public void redirectToReferer(ActionRequest request, ActionResponse response) throws IOException {
        redirectToReferer(request, response, null);
    }

    public void redirectToReferer(ActionRequest request, ActionResponse response, Map<String, String> urlParametersToInclude) throws IOException {

        request.setAttribute("ACTION_REDIRECTING", true);

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);
        String referer = httpRequest.getHeader("referer");

        if (urlParametersToInclude != null && urlParametersToInclude.size() > 0) {
            try {
                //read current ones
                Map<String, String> targetParams = new HashMap<>();
                URI uri = new URI(referer);
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
                int splitIndex = referer.indexOf("?");
                if (splitIndex < 0) {
                    splitIndex = referer.length();
                }
                referer = referer.substring(0, splitIndex) + "?" + URLEncodedUtils.format(targetPairs, "UTF-8");
            } catch (URISyntaxException e) {
                System.out.println("couldn't parse referer URL: " + referer);
                e.printStackTrace();
            }
        }

        response.sendRedirect(referer);
    }

    public abstract boolean isUserAllowed(DiscussionPermission permissions);

    private class SimpleNameValuePair implements NameValuePair {
        String name, value;

        private SimpleNameValuePair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

}