package org.climatecollaboratorium.utils;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.ext.portlet.model.PlanItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class Helper {
    public final static ThemeDisplay themeDisplay = getThemeDisplay();
    public final static String portletId = themeDisplay.getPortletDisplay().getRootPortletId();
    public final static long groupId = themeDisplay.getScopeGroupId();
    public final static String primKey = themeDisplay.getPortletDisplay().getResourcePK();

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
            e.printStackTrace();
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
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
        ExternalContext ec = fc.getExternalContext();
        Map map = ec.getRequestMap();
        
        return map;
    }
    
    public static PortletPreferences getPortletPrefs(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        PortletRequest pReq = (PortletRequest)ec.getRequest();
        PortletPreferences prefs = pReq.getPreferences();
        return prefs;
    }

    public static String getPlanURL(PlanItem p) {
        return "/web/guest/plans#planId=" + p.getPlanId();
    }
    

    public static void sendMessage(String summary, String detail, Severity severity) {
        FacesMessage msg = new FacesMessage();
        
        msg.setSummary(summary);
        msg.setSeverity(severity);
        msg.setDetail(detail);
        
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }
    
    public static void sendInfoMessage(String message) {
        sendMessage(message, null, FacesMessage.SEVERITY_INFO);
    }

    public static String getRootPortletId() {        
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getPortletDisplay().getRootPortletId();
        }
        return null;
    }
    
    public static String getPrimKey() {
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getPortletDisplay().getResourcePK();
        }
        return null;
    }
    
    
}
