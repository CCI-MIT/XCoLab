package org.xcolab.portlets.admintasks;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

public class Helper {

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
            return ec.getRequestMap();
        }

        return null;
    }

    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
    }
}
