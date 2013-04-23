package org.xcolab.portlets.feeds;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class Helper {
    private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";

    //The user info is accessible using standard Portlet and JSF mechanisms
    public static Map getUserInfoMap(){
        Map requestMap = getRequestMap();
        Object obj = requestMap.get(PortletRequest.USER_INFO);
        


        if ( obj != null && obj instanceof Map){
            return (Map)obj;
        }
        return null;
        
    }
    

    
    public static boolean isUserLoggedIn() {
        
        return getUserInfoMap() != null;
    }

    public static Object getLiferayUserInfo(String key) {
        return getUserInfoMap().get(key);
    }

    //The keys in the user info map are specific to Liferay
    public static String getLiferayUserId(){
        return (String)getLiferayUserInfo("liferay.user.id");
    }

    public static String getLiferayCompanyId(){
        return (String)getLiferayUserInfo("liferay.company.id");
    }

    public static String getLiferayUserEmail(){
        return getLiferayUser().getEmailAddress();
    }

    //Since the email is not in the user info map, we need to use
    //a Liferay API to get that information.
    public static User getLiferayUser(){
        try {
            return UserLocalServiceUtil.getUserById(Long.parseLong(getLiferayUserId()));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
    }
    
    public static PermissionChecker getPermissionChecker() {
        return getThemeDisplay().getPermissionChecker();
    }
    
    public static String getPortletID() {
        Map map = getRequestMap();
        return (String) map.get(WebKeys.PORTLET_ID);
    }
    
    private static Map getRequestMap() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null) {
            ExternalContext ec = fc.getExternalContext();
            Map map = ec.getRequestMap();
            return map;
        }

        return null;
    }
    
    public static PortletPreferences getPortletPrefs(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        PortletRequest pReq = (PortletRequest)ec.getRequest();
        PortletPreferences prefs = pReq.getPreferences();
        return prefs;
    }

    public static boolean isUserAdmin(long id) throws SystemException, PortalException {
        Role r = RoleLocalServiceUtil.getRole(getThemeDisplay().getCompanyId(),"Administrator");
        return RoleLocalServiceUtil.hasUserRole(id,r.getRoleId());

    }
    
    private final static String REQUEST_PARAM_NAME = "com.liferay.portal.kernel.servlet.PortletServletRequest";
    
    public static boolean isUrlParameterKey(String key) {
        return key.startsWith(COLLAB_URL_PARAMETER_PREFIX);
    }

    public static String removeCollabPrefixFromParameterKey(String key) {
        return key.substring(COLLAB_URL_PARAMETER_PREFIX.length());
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
                }
                else {
                    value = valueObj.toString();
                }
                params.put(removeCollabPrefixFromParameterKey(key), value);
            }
        }

        return params;
    }

    public static String getUrlParameterKey(String key) {
        return COLLAB_URL_PARAMETER_PREFIX + key;
    }

}
