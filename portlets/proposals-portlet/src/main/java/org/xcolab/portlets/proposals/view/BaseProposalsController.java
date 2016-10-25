package org.xcolab.portlets.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liferay.portal.util.PortalUtil;

import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalsController {

    @Autowired
    protected ProposalsContext proposalsContext;

    public BaseProposalsController() {
        super();
    }

    protected void setSeoTexts(PortletRequest request,
            String pageTitle, String pageSubtitle, String pageDescription) {

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