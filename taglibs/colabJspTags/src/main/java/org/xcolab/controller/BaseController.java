package org.xcolab.controller;

import org.apache.commons.lang3.StringUtils;

import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;


public class BaseController {

    public BaseController() {
        super();
    }

    protected void setSeoTexts(PortletRequest request, String pageTitle,
            String pageSubtitle, String pageDescription) {

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);

        if (StringUtils.isNotBlank(pageTitle)) {
            PortalUtil.setPageTitle(pageTitle, httpRequest);
        }

        if (StringUtils.isNotBlank(pageDescription)) {
            PortalUtil.setPageDescription(pageDescription, httpRequest);
        }

        if (StringUtils.isNotBlank(pageSubtitle)) {
            PortalUtil.setPageSubtitle(pageSubtitle, httpRequest);
        }
    }

}