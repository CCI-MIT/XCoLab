package org.xcolab.controller;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;


public class BaseController {

    public BaseController() {
        super();
    }

    protected void setSeoTexts(PortletRequest request, String pageTitle, String pageSubtitle, String pageDescription)
            throws PortalException, SystemException {

        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(request);

        if (StringUtils.isNotBlank(pageTitle))
            PortalUtil.setPageTitle(pageTitle, httpRequest);

        if (StringUtils.isNotBlank(pageDescription))
            PortalUtil.setPageDescription(pageDescription, httpRequest);

        if (StringUtils.isNotBlank(pageSubtitle))
            PortalUtil.setPageSubtitle(pageSubtitle, httpRequest);
    }

}