package org.xcolab.portlets.staffmembers;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Map;

public class Helper {
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
        Map map = ec.getRequestMap();

        return map;
    }
}