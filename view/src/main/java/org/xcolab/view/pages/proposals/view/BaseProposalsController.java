package org.xcolab.view.pages.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseProposalsController {

    @Autowired
    protected ProposalsContext proposalsContext;

    public BaseProposalsController() {
        super();
    }

    protected void setSeoTexts(HttpServletRequest request,
            String pageTitle, String pageSubtitle, String pageDescription) {

        if (StringUtils.isNotBlank(pageTitle)) {
            PortletUtil.setPageTitle(pageTitle, request);

        }

        if (StringUtils.isNotBlank(pageDescription)) {
            PortletUtil.setPageDescription(pageDescription, request);
        }

        if (StringUtils.isNotBlank(pageSubtitle)) {
            PortletUtil.setPageSubtitle(pageSubtitle, request);
        }
    }

    protected void setBasePageAttributes(HttpServletRequest request, Model model) {
        model.addAttribute("_activePageLink", proposalsContext.getContestType(request).getPortletName());
    }
}