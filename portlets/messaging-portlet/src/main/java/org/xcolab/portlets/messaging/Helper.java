package org.xcolab.portlets.messaging;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";
    private final static String REQUEST_PARAM_NAME = "com.liferay.portal.kernel.servlet.PortletServletRequest";

    public static String getPortletId() {
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getPortletDisplay().getRootPortletId();
        }
        return null;
    }

    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        if (map != null) {
            return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
        }
        return null;
    }

    private static Map getRequestMap() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        return ec.getRequestMap();
    }

    public static long getGroupId() {
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getScopeGroupId();
        }
        return -1;
    }

    public static String getPrimKey() {
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getPortletDisplay().getResourcePK();
        }
        return null;
    }

    public static boolean isUserLoggedIn() {

        return getUserInfoMap() != null;
    }

    // The user info is accessible using standard Portlet and JSF mechanisms
    public static Map getUserInfoMap() {
        Map requestMap = getRequestMap();
        Object obj = requestMap.get(PortletRequest.USER_INFO);

        if (obj != null && obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    public static String getLiferayCompanyId() {
        return (String) getLiferayUserInfo("liferay.company.id");
    }

    public static Object getLiferayUserInfo(String key) {
        return getUserInfoMap().get(key);
    }

    public static String getLiferayUserEmail() {
        return getLiferayUser().getEmailAddress();
    }

    // Since the email is not in the user info map, we need to use
    // a Liferay API to get that information.
    public static User getLiferayUser() {
        try {
            return UserLocalServiceUtil.getUserById(Long.parseLong(getLiferayUserId()));
        } catch (NumberFormatException | PortalException | SystemException e) {
            e.printStackTrace();
        }
        return null;
    }

    // The keys in the user info map are specific to Liferay
    public static String getLiferayUserId() {
        return (String) getLiferayUserInfo("liferay.user.id");
    }

    public static PermissionChecker getPermissionChecker() {
        return getThemeDisplay().getPermissionChecker();
    }

    public static String getPortletID() {
        Map map = getRequestMap();
        return (String) map.get(WebKeys.PORTLET_ID);
    }

    public static PortletPreferences getPortletPrefs() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        PortletRequest pReq = (PortletRequest) ec.getRequest();
        return pReq.getPreferences();
    }

    public static String getUrlParameterKey(String key) {
        return COLLAB_URL_PARAMETER_PREFIX + key;
    }

    public static Map<String, String> getUrlParametersMap() {
        Map<String, String> params = new HashMap<String, String>();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ((HttpServletRequestWrapper) context.getExternalContext()
                .getRequestMap().get(REQUEST_PARAM_NAME)).getRequest();

        Map<String, String[]> requestParams = request.getParameterMap();
        for (String key : requestParams.keySet()) {
            if (isUrlParameterKey(key)) {
                Object valueObj = requestParams.get(key);
                String value = null;
                if (valueObj.getClass().isArray() && ((Object[]) valueObj).length > 0) {
                    value = ((Object[]) valueObj)[0].toString();
                } else {
                    value = valueObj.toString();
                }
                params.put(removeCollabPrefixFromParameterKey(key), value);
            }
        }

        return params;
    }

    public static boolean isUrlParameterKey(String key) {
        return key.startsWith(COLLAB_URL_PARAMETER_PREFIX);
    }

    public static String removeCollabPrefixFromParameterKey(String key) {
        return key.substring(COLLAB_URL_PARAMETER_PREFIX.length());
    }

    public static String filterLineBreaks(String content) {
        return content.replaceAll("\n", " <br />\n");
    }

}