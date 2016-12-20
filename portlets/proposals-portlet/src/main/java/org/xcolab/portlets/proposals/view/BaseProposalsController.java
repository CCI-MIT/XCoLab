package org.xcolab.portlets.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;

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

        HttpServletRequest httpRequest = PortletUtil.getHttpServletRequest(request);

        if (StringUtils.isNotBlank(pageTitle)) {
            PortletUtil.setPageTitle(pageTitle, httpRequest);

        }

        if (StringUtils.isNotBlank(pageDescription)) {
            PortletUtil.setPageDescription(pageDescription, httpRequest);
        }

        if (StringUtils.isNotBlank(pageSubtitle)) {
            PortletUtil.setPageSubtitle(pageSubtitle, httpRequest);
        }
    }
}