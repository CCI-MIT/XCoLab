package org.climatecollaboratorium.plans.utils;

import java.util.Map;

import javax.portlet.PortletRequest;

import org.climatecollaboratorium.plans.Helper;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.Validator;

public class PlansFriendlyURLMapper extends BaseFriendlyURLMapper {
    private static final String _MAPPING = "plans";

    private static final String _PORTLET_ID = "plans_portlet";

    @Override
    public String getPortletId() {
        return _PORTLET_ID;
    }

    public String buildPath(LiferayPortletURL portletURL) {
        StringBuilder friendlyURLPathSB = new StringBuilder();
        String friendlyURLPath = null;

        String planId = portletURL.getParameter("planId");
        String contestId = portletURL.getParameter("contestId");


        if (portletURL.getLifecycle().equals(PortletRequest.RESOURCE_PHASE)) {
            friendlyURLPathSB.append("/plans");
            if (Validator.isNotNull(contestId)) {
                friendlyURLPathSB.append("contestId/");
                friendlyURLPathSB.append(contestId);

                portletURL.addParameterIncludedInPath("contestId");
            }
            if (Validator.isNotNull(planId)) {
                friendlyURLPathSB.append("planId/");
                friendlyURLPathSB.append(planId);
                portletURL.addParameterIncludedInPath("planId");
            }

            friendlyURLPath = friendlyURLPathSB.toString();
        }
        
        if (friendlyURLPath != null) {
            portletURL.addParameterIncludedInPath("p_p_id");
            portletURL.addParameterIncludedInPath("p_p_lifecycle");
            portletURL.addParameterIncludedInPath("p_p_cacheability");

            portletURL.addParameterIncludedInPath("struts_action");
        }

        return friendlyURLPath;
    }

    @Override
    public String getMapping() {
        return _MAPPING;
    }

    public void populateParams(String friendlyURLPath, Map<String, String[]> params) {

        String[] urlParts = friendlyURLPath.split("/");
        // ignore first 2 parts as url is formated like
        // /_MAPPING/here/are/parameters
        // so we need to throw away /_MAPPING/

        for (int i = 2; i < urlParts.length - 1; i += 2) {
            // take parameters from url treating them as key/value pairs
            String key = urlParts[i];
            String value = urlParts[i + 1];

            params.put(Helper.getUrlParameterKey(key), new String[] { value });
            addParam(params, key, value);
        }
    }

    public void populateParams(String friendlyURLPath, Map<String, String[]> parameterMap,
            Map<String, Object> requestContext) {
        populateParams(friendlyURLPath, parameterMap);
        
    }

}
