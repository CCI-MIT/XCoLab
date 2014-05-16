package org.xcolab.portlets.randomproposals;

import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
public class Helper {

    public static ThemeDisplay getThemeDisplay(PortletRequest request) {
        return (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
    }



}
