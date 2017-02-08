package org.xcolab.view.pages.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.util.MetaKeys;

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

        //TODO: customize page titles for proposals
//        if (StringUtils.isNotBlank(pageTitle)) {
//            PortletUtil.setPageTitle(pageTitle, request);
//        }

        if (StringUtils.isNotBlank(pageDescription)) {
            request.setAttribute(MetaKeys.DESCRIPTION.getAttributeName(), pageDescription);
        }

//        TODO: subtitles?
//        if (StringUtils.isNotBlank(pageSubtitle)) {
//            PortletUtil.setPageSubtitle(pageSubtitle, request);
//        }
    }

    protected void setBasePageAttributes(HttpServletRequest request, Model model) {
        model.addAttribute("_activePageLink", proposalsContext.getContestType(request).getPortletName());
    }
}