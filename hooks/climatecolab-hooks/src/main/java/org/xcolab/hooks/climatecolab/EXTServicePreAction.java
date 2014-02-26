/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.hooks.climatecolab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class EXTServicePreAction extends Action {
    private static final String COLLABORATORIUM_THEME_NAME = "climatecolab-theme";
    private static final String THEME_TIMESTAMP_ATTRIBUTE = "THEME_TIMESTAMP";
    private static final Log _log = LogFactoryUtil.getLog(EXTServicePreAction.class);

    public void run(HttpServletRequest req, HttpServletResponse res) throws ActionException {

        
        ThemeDisplay themeDisplay = (ThemeDisplay) req.getAttribute(WebKeys.THEME_DISPLAY);
        Map<String, Object> vmVariables = (Map<String, Object>) req.getAttribute(WebKeys.VM_VARIABLES);
        if (vmVariables == null) {
            vmVariables = new HashMap<String, Object>();
        }

        List<Theme> themes = ThemeLocalServiceUtil.getThemes(themeDisplay.getCompanyId());

        String themeTimestamp = "";

        for (Theme theme : themes) {
            if (theme.getName().equals(COLLABORATORIUM_THEME_NAME)) {
                themeTimestamp = String.valueOf(theme.getTimestamp());
            }
        }

        String contestIdStr = req.getParameter("_collab_paramcontestId");
        if (contestIdStr != null) {
            try {
                Contest contest = ContestLocalServiceUtil.getContest(Long.parseLong(contestIdStr));
                vmVariables.put("collab_contest", contest);
            } catch (NumberFormatException e) {
                _log.error("An exception has been thrown when trying to parse contest id " + contestIdStr, e);
            } catch (PortalException e) {
                _log.error("An exception has been thrown when loading contest with id " + contestIdStr, e);
            } catch (SystemException e) {
                _log.error("An exception has been thrown when loading contest with id " + contestIdStr, e);
            }
        }

        String planIdStr = req.getParameter("_collab_paramplanId");
        if (planIdStr != null) {
            try {
                PlanItem plan = PlanItemLocalServiceUtil.getPlan(Long.parseLong(planIdStr));
                vmVariables.put("collab_plan", plan);
            } catch (NumberFormatException e) {
                _log.error("An exception has been thrown when trying to parse contest id " + contestIdStr, e);
            } catch (PortalException e) {
                _log.error("An exception has been thrown when loading contest with id " + contestIdStr, e);
            } catch (SystemException e) {
                _log.error("An exception has been thrown when loading contest with id " + contestIdStr, e);
            }
        }

        // -- SSO --
        try{
            String facebookAuthRedirectURL = FacebookConnectUtil.getRedirectURL(themeDisplay.getCompanyId());
            facebookAuthRedirectURL = HttpUtil.addParameter(facebookAuthRedirectURL, "redirect", HttpUtil.encodeURL(themeDisplay.getURLCurrent().toString()));
            String facebookAuthURL = FacebookConnectUtil.getAuthURL(themeDisplay.getCompanyId());
            facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "client_id", FacebookConnectUtil.getAppId(themeDisplay.getCompanyId()));
            facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "redirect_uri", facebookAuthRedirectURL);
            facebookAuthURL = HttpUtil.addParameter(facebookAuthURL, "scope", "email");
            vmVariables.put("facebookAuthURL", facebookAuthURL);
        } catch (Exception e){
            e.printStackTrace();
        }
        // -- /SSO --


        vmVariables.put("themeTimestamp", themeTimestamp);


        req.setAttribute(WebKeys.VM_VARIABLES, vmVariables);
        req.setAttribute(THEME_TIMESTAMP_ATTRIBUTE, themeTimestamp);
    }
}
