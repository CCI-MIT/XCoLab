package org.xcolab.portlets.proposals.view;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

@Component
public class BaseProposalsController {

    @Autowired
    protected ProposalsContext proposalsContext;

    public BaseProposalsController() {
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