package org.xcolab.portlets.admintasks;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class Helper {
    private final static String REQUEST_PARAM_NAME = "com.liferay.portal.kernel.servlet.PortletServletRequest";
    private static final String COLLAB_URL_PARAMETER_PREFIX = "_collab_param";

    public static void sendMessage(Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(severity, message, message));
    }
    
    public static void sendError(String message) {
        sendMessage(FacesMessage.SEVERITY_ERROR, message);
    }
    
    public static void sendMessage(String message) {
        sendMessage(FacesMessage.SEVERITY_INFO, message);
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

    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
    }
}
