package org.xcolab.view.pages.proposals.view.proposal;

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

    protected void setSeoTexts(HttpServletRequest request, String pageDescription) {

        if (StringUtils.isNotBlank(pageDescription)) {
            request.setAttribute(MetaKeys.DESCRIPTION.getAttributeName(), pageDescription);
        }
    }

    protected void setBasePageAttributes(HttpServletRequest request, Model model) {
        model.addAttribute("_activePageLink",
                proposalsContext.getContestType(request).getIdentifier());
    }
}
