package org.xcolab.portlets.proposals.utils;

import java.util.Map;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.Router;

public class ProposalsFriendlyUrlMapper extends BaseFriendlyURLMapper {

    private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";
    
    @Override
    public String buildPath(LiferayPortletURL liferayPortletURL) {
        return null;
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

            params.put(getUrlParameterKey(key), new String[] { value });
            addParameter(params, key, value);
        }
        addParameter(params, "p_p_mode", "view_contest");
    }
    
    @Override
    public void populateParams(String friendlyURLPath, Map<String, String[]> parameterMap,
            Map<String, Object> requestContext) {
        populateParams(friendlyURLPath, parameterMap, requestContext);
        
    }
    
    private String getUrlParameterKey(String key) {
        return COLLAB_URL_PARAMETER_PREFIX + key;
    }

}
