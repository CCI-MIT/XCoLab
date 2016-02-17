package org.xcolab.portlets.randomproposals;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletRequest;
public class Helper {

    public static ThemeDisplay getThemeDisplay(PortletRequest request) {
        return (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
    }



}
