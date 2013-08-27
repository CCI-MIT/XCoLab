package org.xcolab.portlets.contests;

import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

public class Helper {


    private static Map getRequestMap() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Map map = ec.getRequestMap();

        return map;
    }
    
    public static ThemeDisplay getThemeDisplay() {
        Map map = getRequestMap();
        if (map != null) {
            return (ThemeDisplay) map.get(WebKeys.THEME_DISPLAY);
        }
        return null;
    }

    public static String getPortletId() {
        ThemeDisplay td = getThemeDisplay();
        if (td != null) {
            return td.getPortletDisplay().getRootPortletId();
        }
        return null;
    }
}
